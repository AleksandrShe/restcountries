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
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int currenciesId;
    private String code;
    private String name;
    private String symbol;

    public Currency() {
    }
}
