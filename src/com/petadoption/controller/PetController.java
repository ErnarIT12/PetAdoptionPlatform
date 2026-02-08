package com.petadoption.controller;

import com.petadoption.exception.InvalidInputException;
import com.petadoption.model.Pet;
import com.petadoption.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@Tag(name = "Pet API", description = "Operations for managing pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    @Operation(summary = "Get all pets", description = "Retrieve a list of all pets in the system")
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{name}")
    @Operation(summary = "Get pet by name", description = "Retrieve a specific pet by its name")
    public ResponseEntity<Pet> getPetByName(@PathVariable String name) {
        return ResponseEntity.ok(petService.getPetByName(name));
    }

    @PostMapping
    @Operation(summary = "Register a new pet", description = "Add a new pet to the system")
    public ResponseEntity<String> registerPet(@RequestBody Pet pet) {
        try {
            petService.registerPet(pet);
            return ResponseEntity.ok("Pet registered successfully");
        } catch (InvalidInputException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
