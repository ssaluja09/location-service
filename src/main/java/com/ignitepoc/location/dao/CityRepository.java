package com.ignitepoc.location.dao;

import com.ignitepoc.location.model.City;
import com.ignitepoc.location.model.CityKey;
import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.Query;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.springframework.stereotype.Repository;

import javax.cache.Cache;
import java.util.List;

@Repository
@RepositoryConfig(cacheName = "City")
public interface CityRepository extends IgniteRepository<City, CityKey> {

    public List<Cache.Entry<CityKey, City>> findByPopulationGreaterThanEqualOrderByPopulationDesc(int population);

    @Query("SELECT city.name, MAX(city.population), country.name, country.GovernmentForm FROM country " +
            "JOIN city ON city.countrycode = country.code " +
            "GROUP BY city.name, country.name, country.GovernmentForm, city.population " +
            "ORDER BY city.population DESC LIMIT ?")
    public List<List<?>> findMostPopulatedCities(int limit);

    @Query("SELECT * FROM City where id = ?")
    public  Cache.Entry<CityKey, City> findById(int id);

}

