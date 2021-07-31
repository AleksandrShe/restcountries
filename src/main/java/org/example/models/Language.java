package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

/**
 *
 * @author Aleksandr
 */
@Data
@AllArgsConstructor
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int languagesId;
    private String iso639_1;
    private String iso639_2;
    private String name;
    private String nativeName;

    public Language() {
    }
}