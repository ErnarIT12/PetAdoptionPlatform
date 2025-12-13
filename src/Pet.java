public class Pet {
    // Attributes
    private String name;
    private String type;
    private int age;
    private boolean adopted;
    private Shelter shelter;
    // Constructors
    public Pet(String name, String type, int age, boolean adopted, Shelter shelter) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.adopted = adopted;
        this.shelter = shelter;
    }
    // ---- Getters and Setters -----
    // for name:
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // for type:
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    // for age:
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    // for adopted:
    public boolean getAdopted() {
        return adopted;
    }
    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    //for Shelters
    public Shelter getShelter() {
        return shelter;
    }
    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }
    // ---- Methods ----
    public void printInfo() {
        String status;
        if(adopted) {
            status = "Already adopted";
            System.out.println("Name: " + name + ", Type: " + type +
                    ", Age: " + age + ", Status: " + status);
        } else {
            status = "Looking for a family";
            System.out.println("Name: " + name + ", Type: " + type +
                    ", Age: " + age + ", Status: " + status + ", Shelter: " + "\"" + shelter.getName() + "\"");
        }
    }
}
