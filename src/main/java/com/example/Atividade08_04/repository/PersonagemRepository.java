package com.example.Atividade08_04.repository;

import com.example.Atividade08_04.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
}