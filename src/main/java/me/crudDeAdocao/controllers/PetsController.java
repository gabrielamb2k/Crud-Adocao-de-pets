package me.crudDeAdocao.controllers;


import lombok.AllArgsConstructor;
import me.crudDeAdocao.controllers.request.PetRequestDTO;
import me.crudDeAdocao.controllers.response.PetResponseDTO;
import me.crudDeAdocao.mapper.PetsMapper;
import me.crudDeAdocao.model.Pets;
import me.crudDeAdocao.model.Users;
import me.crudDeAdocao.repository.UserRepository;
import me.crudDeAdocao.service.PetsService;
import me.crudDeAdocao.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
@AllArgsConstructor
public class PetsController {

    private final PetsService petsService;
    private final UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<PetResponseDTO> createPet(@RequestBody PetRequestDTO requestDTO){
        Users user = userRepository.findById(requestDTO.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        Pets newPet = petsService.createPet(PetsMapper.toPet(requestDTO, user));
        return ResponseEntity.ok(PetsMapper.toPetResponse(newPet));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PetResponseDTO>> petList(){
        return ResponseEntity.ok(petsService.petsList().stream()
                .map(PetsMapper::toPetResponse)
                .toList());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<PetResponseDTO> petsId(@PathVariable Long id){
        return petsService.petsId(id)
                .map(pet -> ResponseEntity.ok(PetsMapper.toPetResponse(pet)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<Pets> optPet = petsService.petsId(id);
        if(optPet.isPresent()){
            petsService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PetResponseDTO> updatePet(@PathVariable Long id, @RequestBody PetRequestDTO requestDTO){
        Users users = userRepository.findById(requestDTO.userId())
                .orElse(null);
        return petsService.update(id, PetsMapper.toPet(requestDTO, users))
                .map(pet -> ResponseEntity.ok(PetsMapper.toPetResponse(pet)))
                .orElse(ResponseEntity.notFound().build());
    }


}
