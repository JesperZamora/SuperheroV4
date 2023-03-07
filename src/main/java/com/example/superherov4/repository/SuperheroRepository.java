package com.example.superherov4.repository;

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

    public List<Superhero> getSuperheroList(){
        List<Superhero> superheroes = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(db_url,u_id,pwd)){
            String SQL = "SELECT * FROM superhero";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next()){
                String heroName = rs.getString("hero_name");
                String realName = rs.getString("real_name");
                String creationYear = rs.getString("creation_year");
                superheroes.add(new Superhero(heroName, realName, creationYear));
            }
            return superheroes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public Superhero getSuperhero(String name){
        Superhero superhero = null;
        try(Connection con = DriverManager.getConnection(db_url,u_id,pwd)){
            String SQL = "SELECT * FROM superhero WHERE hero_name = ? OR real_name = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1,name);
            pstmt.setString(2,name);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                superhero = new Superhero(rs.getString(2), rs.getString(3),rs.getString(4));
            }

            return  superhero;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
