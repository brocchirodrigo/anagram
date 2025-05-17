package br.com.imaginer.anagram.infrastructure.persistence.repositories;

import br.com.imaginer.anagram.infrastructure.persistence.entities.AnagramEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnagramJpaRepository extends JpaRepository<AnagramEntity, Long> {
}
