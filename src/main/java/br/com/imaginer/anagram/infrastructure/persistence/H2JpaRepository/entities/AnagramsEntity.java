package br.com.imaginer.anagram.infrastructure.persistence.H2JpaRepository.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "anagrams")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnagramsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "anagram", nullable = false)
    private String anagram;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anagram_id", referencedColumnName = "id", nullable = false)
    private AnagramEntity anagramEntity;
}
