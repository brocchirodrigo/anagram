package br.com.imaginer.anagram.infrastructure.persistence.H2JpaRepository.repositories;

import br.com.imaginer.anagram.infrastructure.persistence.H2JpaRepository.entities.AnagramsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnagramsJpaRepository extends JpaRepository<AnagramsEntity, Long> {
}
