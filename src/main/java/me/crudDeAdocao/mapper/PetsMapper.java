package me.crudDeAdocao.mapper;

import lombok.experimental.UtilityClass;
import me.crudDeAdocao.controllers.request.PetRequestDTO;
import me.crudDeAdocao.controllers.response.PetResponseDTO;
import me.crudDeAdocao.enums.Tipo;
import me.crudDeAdocao.model.Pets;
import me.crudDeAdocao.model.Users;

@UtilityClass
public class PetsMapper {


    public static Pets toPet(PetRequestDTO request ,Users user) {


        return Pets.builder()
                .id(request.id())
                .name(request.name())
                .tipo(Tipo.valueOf(String.valueOf(request.tipo())))
                .age(request.age())
                .description(request.description())
                .users(user)
                .build();
    }

    public static PetResponseDTO toPetResponse(Pets pet) {


        return PetResponseDTO.builder()
                .id(pet.getId())
                .name(pet.getName())
                .tipo(pet.getTipo())
                .age(pet.getAge())
                .description(pet.getDescription())
                .build();
    }

}
