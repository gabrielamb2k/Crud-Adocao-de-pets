package me.crudDeAdocao.controllers.request;

import me.crudDeAdocao.controllers.response.PetResponseDTO;
import me.crudDeAdocao.model.Pets;

import java.util.List;

public record UserRequestDTO(
        Long id,
        String name,
        String email,
        List<Long> petsList
) {
}
