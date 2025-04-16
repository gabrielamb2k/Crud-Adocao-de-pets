package me.crudDeAdocao.service;

import lombok.AllArgsConstructor;
import me.crudDeAdocao.model.Pets;
import me.crudDeAdocao.repository.PetsRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PetsService {

    private final PetsRepository petsRepository;

    public Pets createPet(Pets newPet){
        return petsRepository.save(newPet);
    }

    public List<Pets> petsList(){
        return petsRepository.findAll();
    }

    public Optional<Pets> petsId(Long id){
        return petsRepository.findById(id);
    }

    public void delete(Long id){
        petsRepository.deleteById(id);
    }

    public Optional<Pets> update(Long id, Pets updatePet){
        Optional<Pets> optPet = petsRepository.findById(id);
        if(optPet.isPresent()){
            Pets pet = optPet.get();

            pet.setId(updatePet.getId());
            pet.setName(updatePet.getName());
            pet.setTipo(updatePet.getTipo());
            pet.setAge(updatePet.getAge());
            pet.setDescription(updatePet.getDescription());
            pet.setUsers(updatePet.getUsers());

            petsRepository.save(pet);

            return Optional.of(pet);
        }
        return Optional.empty();
    }


}
