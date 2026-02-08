package com.petadoption.service;

import com.petadoption.exception.InvalidInputException;
import com.petadoption.exception.ResourceNotFoundException;
import com.petadoption.model.Pet;
import com.petadoption.repository.IPetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class PetService {
    private final IPetRepository petRepository;

    public PetService(IPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public void registerPet(Pet pet) throws InvalidInputException {
        if (pet.getAge() < 0) {
            throw new InvalidInputException("Age cannot be negative.");
        }
        if (pet.getName() == null || pet.getName().isEmpty()) {
            throw new InvalidInputException("Name cannot be empty.");
        }
        petRepository.addPet(pet);
    }

    public Pet getPetByName(String name) {
        Pet pet = petRepository.findByName(name);
        if (pet == null) {
            throw new ResourceNotFoundException("Pet with name " + name + " not found.");
        }
        return pet;
    }

    public List<Pet> filterPets(Predicate<Pet> criteria) {
        return petRepository.getAllPets().stream()
                .filter(criteria)
                .collect(Collectors.toList());
    }
    
    public List<Pet> getAllPets() {
        return petRepository.getAllPets();
    }
}
