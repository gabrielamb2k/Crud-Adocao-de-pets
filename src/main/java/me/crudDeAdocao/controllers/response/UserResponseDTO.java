package me.crudDeAdocao.controllers.response;

import lombok.Builder;

import java.util.List;

@Builder
public record UserResponseDTO(
        Long id,
        String name,
        String email,
        List<PetResponseDTO> petResponseDTOList
) {
}
