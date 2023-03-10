package com.example.superherov4.dto;

import java.util.List;

public class CityDTO {
    private String city;
    private List<String> heroList;
    public CityDTO(String city, List <String> heroList) {
        this.city = city;
        this.heroList = heroList;
    }

    public String getCity() {
        return city;
    }

    public List<String> getCityList() {
        return heroList;
    }

    public void addSuperhero(String name){
        heroList.add(name);
    }
}
