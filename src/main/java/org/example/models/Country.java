package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Aleksandr
 */
@Data
@AllArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int population;
    private String flag;

    @ElementCollection
    @Column(name = "topLevelDomain")
    private List<String> topLevelDomain;

    @ElementCollection
    private List<String> latlng;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "currencies_currenciesId")
    private List<Currency> currencies;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "languages_languagesId")
    private List<Language> languages;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "translations_translationsId")
    private List<Translation> translations;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "regionalBlocs_regionalBlocsId")
    private List<RegionalBloc> regionalBlocs;

    public Country() {
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", population='" + population + '\'' +
                ", flag='" + flag + '\'' +
                ", topLevelDomain=" + topLevelDomain +
                ", latlng=" + latlng +
                ", currencies=" + currencies +
                ", languages=" + languages +
                ", translations=" + translations +
                ", regionalBlocs=" + regionalBlocs +
                '}';
    }
}