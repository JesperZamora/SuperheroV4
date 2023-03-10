package com.example.superherov4.repository;

import com.example.superherov4.dto.CityDTO;
import com.example.superherov4.dto.SuperheroSuperpowerDTO;
import com.example.superherov4.dto.SuperpowerCountDTO;
import com.example.superherov4.model.Superhero;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SuperheroRepository {
    @Value("${spring.datasource.url}")
    String db_url;
    @Value("${spring.datasource.username}")
    String u_id;
    @Value("${spring.datasource.password}")
    String pwd;


    public Superhero getSuperhero(String name) {
        Superhero superhero = null;
        try (Connection con = DriverManager.getConnection(db_url, u_id, pwd)) {
            String SQL = "SELECT * FROM superhero WHERE hero_name = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int superhero_id = rs.getInt("superhero_id");
                String heroName = rs.getString("hero_name");
                String realName = rs.getString("real_name");
                String creationYear = rs.getString("creation_year");
                int city_id = rs.getInt("city_id");
                superhero = new Superhero(superhero_id, heroName, realName, creationYear, city_id);
            }

            return superhero;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public List<Superhero> getSuperheroList() {
        List<Superhero> superheroes = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, u_id, pwd)) {
            String SQL = "SELECT * FROM superhero";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                int superhero_id = rs.getInt("superhero_id");
                String heroName = rs.getString("hero_name");
                String realName = rs.getString("real_name");
                String creationYear = rs.getString("creation_year");
                int city_id = rs.getInt("city_id");
                superheroes.add(new Superhero(superhero_id, heroName, realName, creationYear, city_id));
            }
            return superheroes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public List<SuperpowerCountDTO> getSuperpowerCount(){
        List<SuperpowerCountDTO> superpowerCount = new ArrayList<>();

        try(Connection con = DriverManager.getConnection(db_url,u_id,pwd)){
            String SQL = "SELECT superhero.superhero_id, hero_name, real_name, COUNT(superhero_id) AS count FROM superhero JOIN superheropower USING(superhero_id) GROUP BY superhero_id;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next()) {
                String heroName = rs.getString("hero_name");
                String realName = rs.getString("real_name");
                int count = rs.getInt("count");

                superpowerCount.add(new SuperpowerCountDTO(heroName,realName,count));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return superpowerCount;
    }

    public SuperpowerCountDTO getSuperpowerCountHero(String name) {
        try (Connection con = DriverManager.getConnection(db_url,u_id,pwd)) {
            SuperpowerCountDTO superpowerCount = null;
            String SQL = "SELECT superheropower.superhero_id, hero_name, real_name, COUNT(superheropower.superhero_id) AS count FROM superhero JOIN superheropower WHERE superheropower.superhero_id = superhero.superhero_id AND hero_name =?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String heroName = rs.getString("hero_name");
                String realName = rs.getString("real_name");
                int count = rs.getInt("count");

                superpowerCount = new SuperpowerCountDTO(heroName,realName,count);
            }

            return superpowerCount;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<SuperheroSuperpowerDTO> getHeroesSuperpower(){
        List<SuperheroSuperpowerDTO> heroSuperpower = new ArrayList<>();

        try(Connection con = DriverManager.getConnection(db_url,u_id,pwd)) {
            String SQL = "SELECT superhero.superhero_id, hero_name, real_name, superpower FROM superhero JOIN superpower JOIN superheropower ON superpower.superpower_id = superheropower.superpower_id AND superhero.superhero_id = superheropower.superhero_id;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            String currentHeroName = "";
            SuperheroSuperpowerDTO superpowerDTO = null;

            while (rs.next()) {
                String heroName = rs.getString("hero_name");
                String realName = rs.getString("real_name");
                String superpower = rs.getString("superpower");

                if(heroName.equals(currentHeroName)) {
                    superpowerDTO.addSuperpower(superpower);
                } else {
                    superpowerDTO = new SuperheroSuperpowerDTO(heroName,realName, new ArrayList<>(List.of(superpower)));
                    heroSuperpower.add(superpowerDTO);
                    currentHeroName = heroName;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return heroSuperpower;
    }

    public SuperheroSuperpowerDTO getHeroSuperpower(String name) {
        SuperheroSuperpowerDTO superheroSuperpower = null;
        try(Connection con = DriverManager.getConnection(db_url,u_id,pwd)){
            String SQL = "SELECT superhero.superhero_id, hero_name, real_name, superpower FROM superhero JOIN superpower JOIN superheropower ON superpower.superpower_id = superheropower.superpower_id AND superhero.superhero_id = superheropower.superhero_id AND hero_name = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            String currentHeroName = "";
            while (rs.next()) {
                String heroName = rs.getString("hero_name");
                String realName = rs.getString("real_name");
                String superpower = rs.getString("superpower");

                if(heroName.equals(currentHeroName)) {
                    superheroSuperpower.addSuperpower(superpower);
                } else {
                    superheroSuperpower = new SuperheroSuperpowerDTO(heroName,realName,new ArrayList<>(List.of(superpower)));
                    currentHeroName = heroName;
                }
            }

            return superheroSuperpower;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //TODO - correct it tomorrow
    public List<CityDTO> getCity(String name){
        List<CityDTO> cityList = new ArrayList<>();

        try(Connection con = DriverManager.getConnection(db_url,u_id,pwd)){
            String SQL = "SELECT city_id, city, hero_name FROM city JOIN superhero USING(city_id);";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next()){
                int cityId = rs.getInt("city_id");
                String city = rs.getString("city");
                String heroName = rs.getString("hero_name");
                cityList.add(new CityDTO(cityId,city,(List.of(heroName))));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cityList;
    }

}
