package me.crudDeAdocao.controllers.request;

import me.crudDeAdocao.enums.Tipo;

 public record PetRequestDTO(
         Long id,
        String name,
        Tipo tipo,
        Integer age,
        String description,
        Long userId
) {}
