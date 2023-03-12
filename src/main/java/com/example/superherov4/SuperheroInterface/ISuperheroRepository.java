package com.example.superherov4.SuperheroInterface;

import com.example.superherov4.dto.CityDTO;
import com.example.superherov4.dto.SuperheroSuperpowerDTO;
import com.example.superherov4.dto.SuperpowerCountDTO;
import com.example.superherov4.model.Superhero;

import java.util.List;

public interface ISuperheroRepository {
    default Superhero getSuperhero(String name) {
        return null;
    }

    default List<Superhero> getSuperheroList() {
        return null;
    }

    default List<SuperpowerCountDTO> getSuperpowerCount() {
        return null;
    }

    default SuperpowerCountDTO getSuperpowerCountHero(String name) {
        return null;
    }

    default List<SuperheroSuperpowerDTO> getHeroesSuperpower() {
        return null;
    }

    default SuperheroSuperpowerDTO getHeroSuperpower(String name) {
        return null;
    }

    default List<CityDTO> getCity(String name) {
        return null;
    }

    default List<CityDTO> getAllHeroesInCity(){
        return null;
    }
}
