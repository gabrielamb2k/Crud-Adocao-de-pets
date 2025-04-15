package me.crudDeAdocao.model;

import jakarta.persistence.*;
import me.crudDeAdocao.enums.Tipo;

@Entity
@Table(name="pets")
public class Pets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private Integer age;

    private String description;

    @ManyToOne
    @JoinColumn(name ="users_id")
    private User users;
}
