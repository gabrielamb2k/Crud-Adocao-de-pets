package me.crudDeAdocao.service;

import lombok.AllArgsConstructor;
import me.crudDeAdocao.model.Pets;
import me.crudDeAdocao.model.Users;
import me.crudDeAdocao.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersService {

    private final UserRepository userRepository;

    public Users createUser(Users users){
        return userRepository.save(users);
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


    public Optional<Users> update(Long id, Users updateUsers){
        Optional<Users> optUsers = userRepository.findById(id);
        if(optUsers.isPresent()){

            Users users = optUsers.get();

            users.setId(updateUsers.getId());
            users.setName(updateUsers.getName());
            users.setEmail(updateUsers.getEmail());
            if(updateUsers.getPetsList() != null){
                users.getPetsList().addAll(updateUsers.getPetsList());
            }

            userRepository.save(users);

            return Optional.of(users);
        }
        return Optional.empty();
    }

}
