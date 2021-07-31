package org.example.fetcher;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Country;

import java.io.IOException;
import java.net.URL;

/**
 * Get countries from external resource.
 *
 * @author Aleksandr
 */
public class CountriesFetcher {

    private static final String EXTERNAL_RESOURCE = "https://restcountries.eu/rest/v2/all";
    private final ObjectMapper mapper = new ObjectMapper();


    /** Set mapper properties */
    public void init(){
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Get countries from external resource.
     */
    public Country[] fetch() throws IOException {
        URL url = new URL(EXTERNAL_RESOURCE);
        return mapper.readValue(url, Country[].class);
    }



}
