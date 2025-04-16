package me.crudDeAdocao.controllers.response;

import lombok.Builder;
import me.crudDeAdocao.enums.Tipo;

@Builder
public record PetResponseDTO(
         Long id,
         String name,
         Tipo tipo,
        Integer age,
       String description,
       String userName
){}
