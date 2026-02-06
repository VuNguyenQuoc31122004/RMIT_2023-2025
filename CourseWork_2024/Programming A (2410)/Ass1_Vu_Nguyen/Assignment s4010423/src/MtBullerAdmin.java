import java.util.Scanner;

public class MtBullerAdmin {
    private static MtBullerResort resort = new MtBullerResort();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //Populating the Accomodation database
        resort.addAccommodation(new Accommodation(1, "Hotel Luxury", 200.00, true));
        resort.addAccommodation(new Accommodation(2, "Ski Lodge", 120.00, true));
        resort.addAccommodation(new Accommodation(3, "Mountain Cabin", 150.00, false));
        resort.addAccommodation(new Accommodation(4, "Resort Condo", 180.00, true));
        resort.addAccommodation(new Accommodation(5, "Snow Chalet", 250.00, true));
        resort.addAccommodation(new Accommodation(6, "Winter Bungalow", 130.00, false));
        resort.addAccommodation(new Accommodation(7, "Alpine Suite", 300.00, true));
        resort.addAccommodation(new Accommodation(8, "Parkside Villa", 220.00, true));
        resort.addAccommodation(new Accommodation(9, "Forest Retreat", 110.00, true));
        resort.addAccommodation(new Accommodation(10, "Summit Room", 160.00, false));

//Populating the Customer database
        resort.addCustomer(new Customer(101, "John Doe", "Intermediate"));
        resort.addCustomer(new Customer(102, "Jane Smith", "Beginner"));
        resort.addCustomer(new Customer(103, "Alice Johnson", "Expert"));


        // Control variable for the main program loop
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                int option = Integer.parseInt(scanner.nextLine()); 
                switch (option) {
                    case 1:
                        resort.displayAccommodations();
                        break;
                    case 2:
                        resort.displayAvailableAccommodations();
                        break;
                    case 3:
                        addCustomer();
                        break;
                    case 4:
                        resort.listCustomers();
                        break;
                    case 5:
                        createPackage();
                        break;
                    case 6:
                        resort.listPackages();
                        break;
                    case 7:
                        addLiftPassToPackage();
                        break;
                    case 8:
                        addLessonsToPackage();
                        break;
                    case 9:
                        resort.saveDataToFile();
                        break;
                    case 10:
                        resort.loadDataFromFile();
                        break;
                    case 11:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please enter a number between 1 and 11.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        scanner.close();
        System.out.println("Exiting program.");
    }

    //Display Text Menu
    private static void displayMenu() {
        System.out.println("\n*** Mt Buller Resort Management System ***");
        System.out.println("1: Display all accommodations");
        System.out.println("2: Display available accommodations");
        System.out.println("3: Add customer");
        System.out.println("4: List customers");
        System.out.println("5: Create package");
        System.out.println("6: List packages");
        System.out.println("7: Add lift pass to package");
        System.out.println("8: Add lessons to package");
        System.out.println("9: Save packages to file");
        System.out.println("10: Read packages from file");
        System.out.println("11: Quit");
        System.out.print("Select an option: ");
    }

    // Method to add a new customer
    private static void addCustomer() {
        try {
            System.out.print("Enter customer ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter customer name: ");
            String name = scanner.nextLine();
            System.out.print("Enter skiing level (beginner, intermediate, expert): ");
            String level = scanner.nextLine();
            Customer newCustomer = new Customer(id, name, level);
            resort.addCustomer(newCustomer);
            System.out.println("Customer added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid customer ID.");
        }
    }

    // Method to create a new travel package
    private static void createPackage() {
        try {
            System.out.print("Enter package ID: ");
            int packageId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter customer ID: ");
            int customerId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter accommodation ID: ");
            int accommodationId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter start date (YYYY-MM-DD): ");
            String startDate = scanner.nextLine();
            System.out.print("Enter duration in days: ");
            int duration = Integer.parseInt(scanner.nextLine());
            resort.createPackage(packageId, customerId, accommodationId, startDate, duration);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please ensure all numeric inputs are valid.");
        }
    }

    // Method to add a lift pass to an existing package
    private static void addLiftPassToPackage() {
        try {
            System.out.print("Enter package ID for which you want to add a lift pass: ");
            int packageId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter number of days for the lift pass: ");
            int days = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter price per day for the lift pass: ");
            double pricePerDay = Double.parseDouble(scanner.nextLine());
            resort.addLiftPassToPackage(packageId, days, pricePerDay);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter correct numeric values.");
        }
    }

    // Method to add lessons to an existing package
    private static void addLessonsToPackage() {
        try {
            System.out.print("Enter package ID for which you want to add lessons: ");
            int packageId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter number of lessons: ");
            int lessons = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter price per lesson: ");
            double pricePerLesson = Double.parseDouble(scanner.nextLine());
            resort.addLessonsToPackage(packageId, lessons, pricePerLesson);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter correct numeric values.");
        }
    }
}
