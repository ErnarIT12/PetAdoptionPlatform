import java.util.List;

public interface IPetRepository {
    void addPet(Pet pet);
    List<Pet> getAvailablePets();
    Pet findByName(String name);
    void sortByAge();
    List<Pet> getAllPets();
}