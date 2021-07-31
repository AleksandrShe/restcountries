package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 *
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
    private String population;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public List<String> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<String> latlng) {
        this.latlng = latlng;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

    public List<RegionalBloc> getRegionalBlocs() {
        return regionalBlocs;
    }

    public void setRegionalBlocs(List<RegionalBloc> regionalBlocs) {
        this.regionalBlocs = regionalBlocs;
    }
}