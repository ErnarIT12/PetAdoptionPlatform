package com.petadoption.repository;

import com.petadoption.model.Cat;
import com.petadoption.model.Dog;
import com.petadoption.model.Pet;
import com.petadoption.model.Shelter;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class PetRepository implements IPetRepository {
    private final DataSource dataSource;
    private List<Pet> pets = new ArrayList<>(); // In-memory cache (hybrid approach as per requirements)

    public PetRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        // Load initial data from DB to memory
        this.pets.addAll(getAllPets());
    }

    @Override
    public void addPet(Pet pet) {
        if (findByName(pet.getName()) != null) {
            System.out.println("[Skip] Pet " + pet.getName() + " is already in the system.");
            return;
        }
        pets.add(pet);

        String sql = "INSERT INTO pets (name, type, age, adopted, shelter_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pet.getName());
            pstmt.setString(2, pet.getType());
            pstmt.setInt(3, pet.getAge());
            pstmt.setBoolean(4, pet.getAdopted());

            if (pet.getShelter() != null) {
                pstmt.setInt(5, pet.getShelter().getId());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }

            pstmt.executeUpdate();
            System.out.println("[Database] The pet has been successfully linked to the shelter.");
        } catch (SQLException e) {
            System.out.println("[Database Error] " + e.getMessage());
        }
    }

    @Override
    public List<Pet> getAvailablePets() {
        List<Pet> result = new ArrayList<>();
        for (Pet pet : pets) {
            if (!pet.getAdopted()) {
                result.add(pet);
            }
        }
        return result;
    }

    @Override
    public Pet findByName(String name) {
        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(name)) {
                return pet;
            }
        }
        return null;
    }

    @Override
    public void sortByAge() {
        pets.sort(Comparator.comparingInt(Pet::getAge));
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> dbPets = new ArrayList<>();
        String sql = "SELECT p.name, p.type, p.age, p.adopted, s.name as shelter_name, s.location, s.rating " +
                     "FROM pets p LEFT JOIN shelters s ON p.shelter_id = s.id";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Shelter shelter = null;
                String shelterName = rs.getString("shelter_name");
                if (shelterName != null) {
                     shelter = new Shelter(
                        shelterName,
                        rs.getString("location"),
                        rs.getDouble("rating")
                    );
                }

                String petType = rs.getString("type");
                Pet pet;
                if ("Cat".equalsIgnoreCase(petType)) {
                    pet = new Cat(
                            rs.getString("name"),
                            petType,
                            rs.getInt("age"),
                            rs.getBoolean("adopted"),
                            shelter
                    );
                } else {
                    pet = new Dog(
                            rs.getString("name"),
                            petType,
                            rs.getInt("age"),
                            rs.getBoolean("adopted"),
                            shelter
                    );
                }
                dbPets.add(pet);
            }
        } catch (SQLException e) {
            System.out.println("Error while reading from the database. " + e.getMessage());
        }
        return dbPets;
    }
}
