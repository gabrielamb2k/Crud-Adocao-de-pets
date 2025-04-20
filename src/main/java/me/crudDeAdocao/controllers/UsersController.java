package me.crudDeAdocao.controllers;

import lombok.AllArgsConstructor;
import me.crudDeAdocao.controllers.request.UserRequestDTO;
import me.crudDeAdocao.controllers.response.UserResponseDTO;
import me.crudDeAdocao.mapper.UserMapper;
import me.crudDeAdocao.model.Users;
import me.crudDeAdocao.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO requestDTO){
        Users newUser = usersService.createUser(UserMapper.toUser(requestDTO));
        return ResponseEntity.ok(UserMapper.toUserResponseDto(newUser));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UserResponseDTO>> list(){
        return ResponseEntity.ok(usersService.listUsers()
                .stream()
                .map(UserMapper::toUserResponseDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<UserResponseDTO> listId(@PathVariable Long id){
        Optional<Users> optUsers = usersService.listUsersId(id);
        return usersService.listUsersId(id)
                .map(users -> ResponseEntity.ok(UserMapper.toUserResponseDto(users)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteId(@PathVariable Long id){
        Optional<Users> optUsers = usersService.listUsersId(id);
        if(optUsers.isPresent()){
            usersService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> update(@RequestBody UserRequestDTO requestDTO, @PathVariable Long id){

        return usersService.update(id, UserMapper.toUser(requestDTO))
                .map(users -> ResponseEntity.ok(UserMapper.toUserResponseDto(users)))
                .orElse(ResponseEntity.notFound().build());
    }
}
