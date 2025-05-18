package br.com.imaginer.anagram.domain.model;

import br.com.imaginer.anagram.domain.model.exceptions.AnagramException;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class AnagramModel {
    private String word;
    private Set<AnagramsModel> anagrams = new HashSet<>();

    public AnagramModel(String word) {
        this.word = word;
    }

    public void addAnagram(String value) {
        AnagramsModel entry = new AnagramsModel(value);
        if (!anagrams.add(entry)) {
            throw new AnagramException("Duplicate anagram: " + value);
        }
    }
}
