package com.example.superherov4.model;

public class Superhero {
    private int id;
    private String heroName;
    private String realName;
    private String creationYear;
    private String heroPower;

    public Superhero(int id, String heroName, String realName, String creationYear, String heroPower) {
        this.id = id;
        this.heroName = heroName;
        this.realName = realName;
        this.creationYear = creationYear;
        this.heroPower = heroPower;
    }

    public String getHeroName() {
        return heroName;
    }

    public String getRealName() {
        return realName;
    }

    public String getCreationYear() {
        return creationYear;
    }

    public Superhero(String heroName, String realName, String creationYear) {
        this.heroName = heroName;
        this.realName = realName;
        this.creationYear = creationYear;
    }
}
