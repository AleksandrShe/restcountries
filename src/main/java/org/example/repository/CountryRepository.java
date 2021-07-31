package org.example.repository;

import org.example.models.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Aleksandr
 */
public interface CountryRepository extends CrudRepository<Country, Long> {

    Country findByNameIgnoreCase(String name);

    Country findByTopLevelDomain(@Param("topLevelDomain") String topLevelDomain);

    List<Country> findByTopLevelDomainLike(@Param("topLevelDomain") String topLevelDomain);
}
