package com.ignitepoc.location.controller;

import com.ignitepoc.location.model.CityDTO;
import com.ignitepoc.location.model.CountryDTO;
import com.ignitepoc.location.service.WorldDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorldDatabaseController {

    @Autowired
    WorldDatabaseService worldDatabaseService;

    @GetMapping("/api/countries")
    public List<CountryDTO> getCountriesByPopulation(@RequestParam (value = "population", required = true) int population) {
        return worldDatabaseService.getCountriesByPopulation(population);
    }

    @GetMapping("/api/cities")
    public List<CityDTO> getCitiesByPopulation(@RequestParam (value = "population", required = true) int population) {
        return worldDatabaseService.getCitiesByPopulation(population);
    }

    @GetMapping("/api/cities/mostPopulated")
    public List<List<?>> getMostPopulatedCities(@RequestParam (value = "limit", required = false) Integer limit) {
        return worldDatabaseService.getMostPopulatedCities(limit);
    }

    @PutMapping("/api/cities/{id}")
    public CityDTO updateCityPopulation(@PathVariable Integer id, @RequestBody CityDTO cityDTO) {
        return worldDatabaseService.updateCityPopulation(id, cityDTO.getPopulation());
    }

}
