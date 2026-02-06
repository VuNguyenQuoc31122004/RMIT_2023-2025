import java.util.ArrayList;
import java.io.*;

public class MtBullerResort {
    // Lists to manage the different entities within the resort
    private ArrayList<Accommodation> accommodations = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<TravelPackage> travelPackages = new ArrayList<>();

    // Methods to manage Accommodations
    public void addAccommodation(Accommodation accommodation) {
        accommodations.add(accommodation);
    }

    //Function for displaying Accomodation
    public void displayAccommodations() {
        for (Accommodation accommodation : accommodations) {
            System.out.println(accommodation);
        }
    }

    //Function for displaying all available Accomodation
    public void displayAvailableAccommodations() {
        for (Accommodation accommodation : accommodations) {
            if (accommodation.isAvailable()) {
                System.out.println(accommodation);
            }
        }
    }

    // Function for adding customers
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Function for viewing all customers in the list
    public void listCustomers() {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    // Function for creating travel packacge
    public void createPackage(int packageID, int customerID, int accommodationID, 
                              String startDate, int duration) {
        Customer customer = findCustomerById(customerID);
        Accommodation accommodation = findAccommodationById(accommodationID);
        if (customer != null && accommodation != null && accommodation.isAvailable()) {
            TravelPackage newPackage = new TravelPackage(packageID, customerID, accommodation, startDate, duration);
            travelPackages.add(newPackage);
            accommodation.markAsUnavailable(); // Mark the accommodation as unavailable
            System.out.println("Package created successfully: " + newPackage);
        } else {
            System.out.println("Error creating package: Check customer ID, accommodation ID and availability.");
        }
    }
    // Function for listing packages
    public void listPackages() {
        for (TravelPackage travelPackage : travelPackages) {
            System.out.println(travelPackage);
        }
    }

    //function to find customer base on ID
    private Customer findCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }
    //function to find accomodation base on ID
    private Accommodation findAccommodationById(int id) {
        for (Accommodation accommodation : accommodations) {
            if (accommodation.getId() == id) {
                return accommodation;
            }
        }
        return null;
    }

    // Saves all resort data
    public void saveDataToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("resortData.bin"))) {
            out.writeObject(accommodations);
            out.writeObject(customers);
            out.writeObject(travelPackages);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

     // Loads all resort data from a file
    public void loadDataFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("resortData.bin"))) {
            accommodations = (ArrayList<Accommodation>) in.readObject();
            customers = (ArrayList<Customer>) in.readObject();
            travelPackages = (ArrayList<TravelPackage>) in.readObject();
            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    // Adds a lift pass to an existing travel package
    public void addLiftPassToPackage(int packageId, int days, double pricePerDay) {
        TravelPackage tp = findPackageById(packageId);
        if (tp != null) {
            tp.addLiftPass(days, pricePerDay);
            System.out.println("Lift pass successfully added to package.");
        } else {
            System.out.println("No package found with ID " + packageId);
        }
    }

    
    public void addLessonsToPackage(int packageId, int lessons, double pricePerLesson) {
        TravelPackage tp = findPackageById(packageId);
        if (tp != null) {
            tp.addLessons(lessons, pricePerLesson);
            System.out.println("Lessons successfully added to package.");
        } else {
            System.out.println("No package found with ID " + packageId);
        }
    }

    
    private TravelPackage findPackageById(int packageId) {
        for (TravelPackage tp : travelPackages) {
            if (tp.getPackageID() == packageId) {
                return tp;
            }
        }
        return null;
    }
}
