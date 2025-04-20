package me.crudDeAdocao.controllers.response;

import lombok.Builder;
import me.crudDeAdocao.enums.Tipo;
import me.crudDeAdocao.model.Users;

@Builder
public record PetResponseDTO(
         Long id,
         String name,
         Tipo tipo,
        Integer age,
       String description
){}
