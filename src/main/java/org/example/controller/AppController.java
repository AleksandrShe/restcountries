package org.example.controller;

import org.example.models.Country;
import org.example.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Контроллер запросов.
 *
 * @author Aleksandr
 */
@Controller
@ComponentScan("org.example.repository")
public class AppController {

    @Autowired
    private CountryRepository countryRepository;

    @Value("${welcome.message}")
    private String message;

    @GetMapping("/index")
    public String main(Model model) {
        model.addAttribute("message", message);

        return "index";
    }

    @PostMapping("country")
    public String byName(@NotBlank @RequestParam String country, Model model) {
        Country found =countryRepository.findByNameIgnoreCase(country);

        model.addAttribute("key", country);
        model.addAttribute("country", found);
        return "country";
    }

    /**
     * Поиск по домену(уникальный результат).
     */
    @PostMapping("domain")
    public String byFullDomain(@NotBlank @RequestParam String domain, Model model) {
        Country found = countryRepository.findByTopLevelDomain(domain);

        model.addAttribute("key", domain);
        model.addAttribute("country", found);
        return "country";
    }

    /**
     * Поиск по домену(по вхождению).
     */
    @PostMapping("domainLike")
    public String byDomain(@NotBlank @RequestParam String domain, Model model) {
        //Нужно сделать ключ, например %r% для поиска домена по вхождению 'r'.
        String key = "%".concat(domain).concat("%");
        List<Country> countries = countryRepository.findByTopLevelDomainLike(key);

        model.addAttribute("key", domain);
        model.addAttribute("countries", countries);
        return "countries";
    }

    @GetMapping("delete")
    public String delete(Model model) {
        countryRepository.deleteAll();
        return "index";
    }


}