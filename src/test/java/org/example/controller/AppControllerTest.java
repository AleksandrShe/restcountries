package org.example.controller;


import org.example.models.*;
import org.example.repository.CountryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;


import static org.hamcrest.Matchers.equalTo;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AppController.class)
public class AppControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testByName() throws Exception {

        Country russia = new Country();
        russia.setId(186);
        russia.setName("Russian Federation");
        russia.setPopulation("146599183");
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


        ResultActions resultActions = mockMvc.perform(get("/country")
                .param("country", "Russian Federation"))
                .andExpect(status().isOk())
                .andExpect(view().name("country"))
                .andExpect(model().attribute("country", equalTo(russia)))
                .andExpect(content().string(containsString("Country{name='Russian Federation', population='146599183', flag='https://restcountries.eu/data/rus.svg', topLevelDomain=[.ru], latlng=[60.0, 100.0], currencies=[Currency(currenciesId=207, code=RUB, name=Russian ruble, symbol=₽)], languages=[Language(languagesId=269, iso639_1=ru, iso639_2=rus, name=Russian, nativeName=Русский)], translations=[Translation(translationsId=186, de=Russland, es=Rusia, fr=Russie, ja=ロシア連邦, it=Russia, br=Rússia, pt=Rússia, nl=Rusland, hr=Rusija, fa=روسیه)], regionalBlocs=[RegionalBloc(regionalBlocsId=148, acronym=EEU, name=Eurasian Economic Union, otherAcronyms=[EAEU], otherNames=[])]}")));
    }

}