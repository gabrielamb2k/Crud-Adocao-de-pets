package me.crudDeAdocao.repository;

import me.crudDeAdocao.model.Pets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetsRepository extends JpaRepository<Pets, Long>{

    Optional<Pets> findById(Long id);
}
