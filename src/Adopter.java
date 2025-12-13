public class Adopter {
    // Attributes
    private String name;
    private int age;
    private Pet pet;
    // Constructors
    public Adopter(String name, int age, Pet pet) {
        this.name = name;
        this.age = age;
        this.pet = pet;
    }
    // ---- Getters and Setters -----
    // for name:
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // for age:
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    // for Pet name
    public Pet getPet() {
        return pet;
    }
    public void setPet(Pet pet) {
        this.pet = pet;
    }
    // ---- Methods ----
    public void printInfo() {
        System.out.println("Name: " + name + ", Age: " + age + ", Adopted pet: " + pet.getName());
    }
}
