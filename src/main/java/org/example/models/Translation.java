package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

/**
 *
 * @author Aleksandr
 */
@Data
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int translationsId;
    private String de;
    private String es;
    private String fr;
    private String ja;
    private String it;
    private String br;
    private String pt;
    private String nl;
    private String hr;
    private String fa;

    public Translation() {
    }
}