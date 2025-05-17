package br.com.imaginer.anagram.infrastructure.persistence.repositories;

import br.com.imaginer.anagram.infrastructure.persistence.entities.AnagramsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnagramsJpaRepository extends JpaRepository<AnagramsEntity, Long> {
}
