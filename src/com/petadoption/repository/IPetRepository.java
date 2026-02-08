package com.petadoption.repository;

import com.petadoption.model.Pet;
import java.util.List;

public interface IPetRepository {
    void addPet(Pet pet);
    List<Pet> getAvailablePets();
    Pet findByName(String name);
    void sortByAge();
    List<Pet> getAllPets();

    // Default method (Language Feature)
    default void logRepositoryAccess() {
        System.out.println("[Log] Accessing Pet Repository...");
    }

    // Static method (Language Feature)
    static void printRepositoryInfo() {
        System.out.println("[Info] Pet Repository Interface v1.0");
    }
}
