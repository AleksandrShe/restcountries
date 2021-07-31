package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Aleksandr
 */
@Data
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RegionalBloc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int regionalBlocsId;
    private String acronym;
    private String name;

    @ElementCollection
    private List<String> otherAcronyms;

    @ElementCollection
    private List<String> otherNames;

    public RegionalBloc() {
    }
}