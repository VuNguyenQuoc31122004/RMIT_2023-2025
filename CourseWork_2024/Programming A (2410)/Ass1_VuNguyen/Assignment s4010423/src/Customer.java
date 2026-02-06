import java.io.Serializable;

public class Customer implements Serializable {
 
    private int id;
    private String name;
    private String skiingLevel; 


    public Customer(int id, String name, String skiingLevel) {
        this.id = id;
        this.name = name;
        this.skiingLevel = skiingLevel;
    }


    public int getId() {
        return id;
    }

   
    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

 
    public void setName(String name) {
        this.name = name;
    }

  
    public String getSkiingLevel() {
        return skiingLevel;
    }


    public void setSkiingLevel(String skiingLevel) {
        this.skiingLevel = skiingLevel;
    }


    @Override
    public String toString() {
        return String.format("Customer Details:\n - ID: %d\n - Name: %s\n - Skiing Level: %s",
                             id, name, skiingLevel);
    }
}
