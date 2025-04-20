package me.crudDeAdocao.mapper;

import lombok.experimental.UtilityClass;
import me.crudDeAdocao.controllers.request.UserRequestDTO;
import me.crudDeAdocao.controllers.response.PetResponseDTO;
import me.crudDeAdocao.controllers.response.UserResponseDTO;
import me.crudDeAdocao.model.Pets;
import me.crudDeAdocao.model.Users;

import java.util.List;

@UtilityClass
public class UserMapper {

    public static Users toUser(UserRequestDTO requestDTO){
        List<Pets> pets = requestDTO.petsList().stream()
                .map(petsId -> Pets.builder().id(petsId).build())
                .toList();

        return Users.builder()
                .id(requestDTO.id())
                .name(requestDTO.name())
                .email(requestDTO.email())
                .petsList(pets)
                .build();
    }

    public static UserResponseDTO toUserResponseDto(Users users){

        List<PetResponseDTO> pets = users.getPetsList()
                .stream()
                .map(PetsMapper::toPetResponse)
                .toList();

        return UserResponseDTO.builder()
                .id(users.getId())
                .name(users.getName())
                .email(users.getEmail())
                .petResponseDTOList(pets)
                .build();
    }
}
