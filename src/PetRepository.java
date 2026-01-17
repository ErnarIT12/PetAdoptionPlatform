import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetRepository implements IPetRepository {
    private List<Pet> pets = new ArrayList<>();
    private final DatabaseManager dbManager;

    public PetRepository(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public void addPet(Pet pet) {
        if (findByName(pet.getName()) != null) {
            System.out.println("[Skip] Питомец " + pet.getName() + " уже есть в системе.");
            return;
        }
        pets.add(pet);

        // Добавляем shelter_id в запрос
        String sql = "INSERT INTO pets (name, type, age, adopted, shelter_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pet.getName());
            pstmt.setString(2, pet.getType());
            pstmt.setInt(3, pet.getAge());
            pstmt.setBoolean(4, pet.getAdopted());

            if (pet.getShelter() != null) {
                pstmt.setInt(5, pet.getShelter().getId());
            } else {
                pstmt.setNull(5, java.sql.Types.INTEGER);
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
        pets.sort((p1, p2) -> p1.getAge() - p2.getAge());
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> dbPets = new ArrayList<>();
        String sql = "SELECT * FROM pets"; // Запрос к базе

        try (Connection conn = dbManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                dbPets.add(new Dog(
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("age"),
                        rs.getBoolean("adopted"),
                        null
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error while reading from the database. " + e.getMessage());
        }
        return dbPets;
    }
}