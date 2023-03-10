package com.example.superherov4.controller;

import com.example.superherov4.dto.CityDTO;
import com.example.superherov4.dto.SuperheroSuperpowerDTO;
import com.example.superherov4.dto.SuperpowerCountDTO;
import com.example.superherov4.model.Superhero;
import com.example.superherov4.repository.SuperheroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/superhero")
@Controller
public class SuperheroController {
    private SuperheroRepository repository;

    public SuperheroController(SuperheroRepository repository){
        this.repository = repository;
    }

    @GetMapping("/{name}") //request 1.0 - one hero
    public ResponseEntity<Superhero> getSuperhero(@PathVariable String name){
        Superhero superhero = repository.getSuperhero(name);
        return new ResponseEntity<>(superhero,HttpStatus.OK);
    }


    @GetMapping() //request 1.1 - all heroes
    public ResponseEntity<List<Superhero>> getSuperheros() {
        List<Superhero> superheroList = repository.getSuperheroList();
        return new ResponseEntity<>(superheroList, HttpStatus.OK);
    }

    @GetMapping("/superpower/count") //request 2.0 - all heroes with their superpower count
    public ResponseEntity<List<SuperpowerCountDTO>> getAllHeroesWithPowerCount(){
        List<SuperpowerCountDTO> superpowerCountList = repository.getSuperpowerCount();
        return new ResponseEntity<>(superpowerCountList, HttpStatus.OK);
    }

    @GetMapping("/superpower/count/{name}") //request 2.1 - one hero with superpower count
    public ResponseEntity<SuperpowerCountDTO> getAllHeroesWithPowerCount(@PathVariable String name){
        SuperpowerCountDTO superpowerCount = repository.getSuperpowerCountHero(name);
        return new ResponseEntity<>(superpowerCount, HttpStatus.OK);
    }

    @GetMapping("/superpower") //request 3.0 - all heroes with their superpowers
    public ResponseEntity<List<SuperheroSuperpowerDTO>> getAllHeroesWithPower(){
        List<SuperheroSuperpowerDTO> superheroSuperpower = repository.getHeroesSuperpower();
        return new ResponseEntity<>(superheroSuperpower, HttpStatus.OK);
    }

    @GetMapping("/superpower/{name}") //request 3.1 - one hero with superpowers
    public ResponseEntity<SuperheroSuperpowerDTO> getOneHeroWithPower(@PathVariable String name){
        SuperheroSuperpowerDTO superheroSuperpower = repository.getHeroSuperpower(name);
        return new ResponseEntity<>(superheroSuperpower, HttpStatus.OK);
    }


    @GetMapping("/city/{name}")
    public ResponseEntity<List<CityDTO>> getCity(@PathVariable String name) {
        List<CityDTO> cities = repository.getCity(name);
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}
