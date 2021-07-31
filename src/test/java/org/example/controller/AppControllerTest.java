package org.example.controller;


import org.example.models.*;
import org.example.repository.CountryRepository;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Aleksandr
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AppController.class)
public class AppControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryRepository countryRepository;

    private static final String EXPECTED_STRING = "Country{name=&#39;Russian Federation&#39;," +
            " population=&#39;146599183&#39;, flag=&#39;https://restcountries.eu/data/rus.svg&#39;, topLevelDomain=[.ru]," +
            " latlng=[60.0, 100.0], currencies=[Currency(currenciesId=207, code=RUB, name=Russian ruble, symbol=₽)], " +
            "languages=[Language(languagesId=269, iso639_1=ru, iso639_2=rus, name=Russian, nativeName=Русский)], " +
            "translations=[Translation(translationsId=186, de=Russland, es=Rusia, fr=Russie, ja=ロシア連邦, it=Russia, " +
            "br=Rússia, pt=Rússia, nl=Rusland, hr=Rusija, fa=روسیه)], regionalBlocs=[RegionalBloc(regionalBlocsId=148, " +
            "acronym=EEU, name=Eurasian Economic Union, otherAcronyms=[EAEU], otherNames=[])]}";

    /**
     * Test for finding country by name.
     */
    @Test
    public void testByName() throws Exception {

        Country russia = provideCountry();
        given(countryRepository.findByNameIgnoreCase("Russian Federation")).willReturn(russia);

        mockMvc.perform(post("/country")
                .param("country", "Russian Federation"))
                .andExpect(status().isOk())
                .andExpect(view().name("country"))
                .andExpect(model().attribute("country", equalTo(russia)))
                .andExpect(content().string(CoreMatchers.containsString(EXPECTED_STRING)));
    }

    /**
     * Test for finding domain by name.
     */
    @Test
    public void testByDomain() throws Exception {

        Country russia = provideCountry();
        given(countryRepository.findByTopLevelDomain(".ru")).willReturn(russia);

        mockMvc.perform(post("/domain")
                .param("domain", ".ru"))
                .andExpect(status().isOk())
                .andExpect(view().name("country"))
                .andExpect(model().attribute("country", equalTo(russia)))
                .andExpect(content().string(CoreMatchers.containsString(EXPECTED_STRING)));
    }

    /**
     * Test for finding domain using "like" in query.
     * "Russian Federation" is one which contains "r" in topLevelDomain.
     */
    @Test
    public void testByDomainLike() throws Exception {

        Country russia = provideCountry();
        List<Country> countries = List.of(russia);
        given(countryRepository.findByTopLevelDomainLike("%r%")).willReturn(countries);

        mockMvc.perform(post("/domainLike")
                .param("domain", "r"))
                .andExpect(status().isOk())
                .andExpect(view().name("countries"))
                .andExpect(model().attribute("countries", Matchers.contains(russia)))
                .andExpect(content().string(CoreMatchers.containsString(EXPECTED_STRING)));
    }

    /**
     * This test deletes all entries from DB.
     * Then, looks for "Russian Federation" and doesn't find it.
     */
    @Test
    public void testDelete() throws Exception {

        mockMvc.perform(get("/delete"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

        mockMvc.perform(post("/country")
                .param("country", "Russian Federation"))
                .andExpect(status().isOk())
                .andExpect(view().name("country"))
                .andExpect(model().attribute("country", equalTo(null)))
                .andExpect(content().string(CoreMatchers.containsString("Country/domain \"<span>Russian Federation</span>\" not found")));

    }


    /**
     * Provide Counry object.
     */
    private Country provideCountry() {
        Country russia = new Country();
        russia.setId(186);
        russia.setName("Russian Federation");
        russia.setPopulation(146599183);
        russia.setFlag("https://restcountries.eu/data/rus.svg");
        russia.setTopLevelDomain(List.of(".ru"));
        russia.setLatlng(List.of("60.0, 100.0"));
        russia.setCurrencies(List.of(new Currency(207, "RUB", "Russian ruble", "₽")));
        russia.setLanguages(List.of(new Language(269, "ru", "rus", "Russian",
                "Русский")));
        russia.setTranslations(List.of(new Translation(186, "Russland", "Rusia", "Russie",
                "ロシア連邦", "Russia", "Rússia", "Rússia", "Rusland", "Rusija", "روسیه")));
        russia.setRegionalBlocs(List.of(new RegionalBloc(148, "EEU",
                "Eurasian Economic Union", List.of("EAEU"), List.of())));

        return russia;
    }

}