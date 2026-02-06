import java.io.Serializable;

public class Accommodation implements Serializable{

    private int id;
    private String type; 
    private double price;
    private boolean isAvailable; 

   
    public Accommodation(int id, String type, double price, boolean isAvailable) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    
    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }

   
    public String getType() {
        return type;
    }

    
    public void setType(String type) {
        this.type = type;
    }

   
    public double getPrice() {
        return price;
    }

    
    public void setPrice(double price) {
        this.price = price;
    }

    
    public boolean isAvailable() {
        return isAvailable;
    }

    
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    
    public void markAsUnavailable() {
        isAvailable = false;
    }

    
    public void markAsAvailable() {
        isAvailable = true;
    }

    
    @Override
    public String toString() {
        return String.format("Accommodation Details:\n - ID: %d\n - Type: %s\n - Price per Night: $%.2f\n - Available: %s",
                             id, type, price, isAvailable ? "Yes" : "No");
    }
}
