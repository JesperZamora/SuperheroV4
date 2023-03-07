package com.example.superherov4.controller;

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

    @GetMapping("/superherolist")
    public ResponseEntity<List<Superhero>> getSuperheros() {
        List<Superhero> superheroList = repository.getSuperheroList();
        return new ResponseEntity<>(superheroList, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Superhero> getSuperhero(@PathVariable String name){
        Superhero superhero = repository.getSuperhero(name);
        return new ResponseEntity<>(superhero,HttpStatus.OK);
    }
}
