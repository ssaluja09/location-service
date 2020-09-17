package com.ignitepoc.location.service;

import com.ignitepoc.location.dao.CityRepository;
import com.ignitepoc.location.dao.CountryRepository;
import com.ignitepoc.location.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorldDatabaseService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;

    public List<CountryDTO> getCountriesByPopulation(int population) {
        List<CountryDTO> countries = new ArrayList<>();
        for (Cache.Entry<String, Country> entry :
                countryRepository.findByPopulationGreaterThanEqualOrderByPopulationDesc(population)) {

            countries.add(new CountryDTO(entry.getKey(), entry.getValue()));
        }
        return countries;
    }

    public List<CityDTO> getCitiesByPopulation(int population) {
        List<CityDTO> cities = new ArrayList<>();
        for (Cache.Entry<CityKey, City> entry :
                cityRepository.findByPopulationGreaterThanEqualOrderByPopulationDesc(population)) {

            cities.add(new CityDTO(entry.getKey(), entry.getValue()));
        }
        return cities;
    }

    public List<List<?>> getMostPopulatedCities(Integer limit) {
        List<List<?>> cities = cityRepository.findMostPopulatedCities(limit == null ? 5 : limit);
        return cities;
    }

    public CityDTO updateCityPopulation(int cityId, int population) {

        Cache.Entry<CityKey, City> entry = cityRepository.findById(cityId);
        entry.getValue().setPopulation(population);

        cityRepository.save(entry.getKey(), entry.getValue());

        return new CityDTO(entry.getKey(), entry.getValue());

    }


}
