package me.crudDeAdocao.service;

import lombok.AllArgsConstructor;
import me.crudDeAdocao.model.Pets;
import me.crudDeAdocao.model.Users;
import me.crudDeAdocao.repository.PetsRepository;
import me.crudDeAdocao.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsersService {

    private final UserRepository userRepository;
    private final PetsRepository petsRepository;

    public Users createUser(Users users) {
        // Primeiro salva o usuário sem os pets
        List<Pets> tempPets = new ArrayList<>(users.getPetsList());
        users.setPetsList(new ArrayList<>());
        Users savedUser = userRepository.save(users);

        // Depois associa os pets ao usuário já salvo
        savedUser.setPetsList(tempPets);
        associePets(savedUser);

        return userRepository.save(savedUser);
    }

    public List<Users> listUsers(){
        return userRepository.findAll();
    }

    public Optional<Users> listUsersId(Long id){
        return userRepository.findById(id);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }


    @Transactional
    public Optional<Users> update(Long id, Users updateData) {
        Optional<Users> optUser = userRepository.findById(id);

        if (optUser.isPresent()) {
            Users existingUser = optUser.get();

            // Atualiza os dados básicos
            existingUser.setName(updateData.getName());
            existingUser.setEmail(updateData.getEmail());

            // Limpa a lista atual de pets
            existingUser.getPetsList().clear();

            // Copia os IDs dos pets do updateData para o existingUser
            if (updateData.getPetsList() != null) {
                existingUser.setPetsList(new ArrayList<>());

                // Busca os pets do banco de dados pelos IDs
                List<Pets> pets = petsRepository.findAllById(updateData.getPetsList().stream()
                        .map(Pets::getId)
                        .collect(Collectors.toList()));

                // Associa cada pet ao usuário existente
                for (Pets pet : pets) {
                    pet.setUsers(existingUser);
                    existingUser.getPetsList().add(pet);
                }
            }

            // Salva o usuário existente com os novos pets
            return Optional.of(userRepository.save(existingUser));
        }

        return Optional.empty();
    }

    public void associePets(Users users) {
        if (users.getPetsList() == null || users.getPetsList().isEmpty()) {
            users.setPetsList(new ArrayList<>());
            return;
        }

        List<Pets> pets = petsRepository.findAllById(users.getPetsList().stream()
                .map(Pets::getId)
                .collect(Collectors.toList()));

        // Limpa a lista atual de pets do usuário
        users.setPetsList(new ArrayList<>());

        // Associa cada pet ao usuário (relação bidirecional)
        for (Pets pet : pets) {
            pet.setUsers(users);          // Define o usuário no pet
            users.getPetsList().add(pet); // Adiciona o pet à lista do usuário
        }

        // Remova a chamada para petsRepository.saveAll(pets);
    }

}
