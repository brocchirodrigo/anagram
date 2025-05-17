package br.com.imaginer.anagram.infrastructure.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "anagram")
public class AnagramEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "word")
    private String word;

    @ElementCollection
    @CollectionTable(name = "anagrams", joinColumns = @JoinColumn(name = "anagram_id"))
    @Column(name = "anagram")
    private List<String> anagrams = new ArrayList<>();
}
