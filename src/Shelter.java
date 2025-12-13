public class Shelter {
    // Attributes
    private String name;
    private String location;
    private double rating;
    // Constructors
    public Shelter(String name, String location, double rating) {
        this.name = name;
        this.location = location;
        this.rating = rating;
    }
    // ---- Getters and Setters -----
    // for name:
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    // for location:
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    // for rating:
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    // ---- Methods ----
    public void printInfo() {
        System.out.println("Shelter: \"" + name + "\"" + ", Location: " + location +
                ", Rating: " + rating);
    }
}

