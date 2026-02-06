import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class ResortGUI extends JFrame implements ActionListener {
    private JTabbedPane tabbedPane;
    private JPanel accommodationsTab, customersTab, travelPackagesTab;
    private JTextArea accommodationTextArea, customerTextArea, travelPackageTextArea;
    private ArrayList<Accommodation> accommodations;
    private ArrayList<Customer> customers;
    private ArrayList<TravelPackage> travelPackages;

    public ResortGUI() {
        accommodations = new ArrayList<>();
        customers = new ArrayList<>();
        travelPackages = new ArrayList<>();

        initializeData();

        setTitle("Mt Buller Winter Resort");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();

        accommodationsTab = new JPanel(new BorderLayout());
        customersTab = new JPanel(new BorderLayout());
        travelPackagesTab = new JPanel(new BorderLayout());

        customerTextArea = new JTextArea(20, 40);
        accommodationTextArea = new JTextArea(20, 40);
        travelPackageTextArea = new JTextArea(20, 40);

        setupAccommodationsTab();
        setupCustomersTab();
        setupTravelPackagesTab();

        tabbedPane.addTab("Accommodations", accommodationsTab);
        tabbedPane.addTab("Customers", customersTab);
        tabbedPane.addTab("Travel Packages", travelPackagesTab);

        add(tabbedPane);

        setVisible(true);
    }

    private void setupAccommodationsTab() {
        JPanel topPanel = new JPanel();
        JButton displayAllButton = new JButton("Display All Accommodations");
        displayAllButton.addActionListener(e -> displayAccommodations());
        JButton displayAvailableButton = new JButton("Display Available Accommodations");
        displayAvailableButton.addActionListener(e -> displayAvailableAccommodations());

        topPanel.add(displayAllButton);
        topPanel.add(displayAvailableButton);

        JPanel fileOperationsPanel = createFileOperationsPanel();

        accommodationsTab.add(topPanel, BorderLayout.NORTH);
        accommodationsTab.add(new JScrollPane(accommodationTextArea), BorderLayout.CENTER);
        accommodationsTab.add(fileOperationsPanel, BorderLayout.SOUTH);
    }

    private void setupCustomersTab() {
        JPanel topPanel = new JPanel();
        JButton addCustomerButton = new JButton("Add Customer");
        addCustomerButton.addActionListener(e -> addCustomer());
        JButton listCustomersButton = new JButton("List Customers");
        listCustomersButton.addActionListener(e -> listCustomers());

        topPanel.add(addCustomerButton);
        topPanel.add(listCustomersButton);

        JPanel fileOperationsPanel = createFileOperationsPanel();

        customersTab.add(topPanel, BorderLayout.NORTH);
        customersTab.add(new JScrollPane(customerTextArea), BorderLayout.CENTER);
        customersTab.add(fileOperationsPanel, BorderLayout.SOUTH);
    }

    private void setupTravelPackagesTab() {
        JPanel topPanel = new JPanel();
        JButton createPackageButton = new JButton("Create Package");
        createPackageButton.addActionListener(e -> createPackage());
        JButton listPackagesButton = new JButton("List Packages");
        listPackagesButton.addActionListener(e -> listPackages());
        JButton addLiftPassButton = new JButton("Add Lift Pass to Package");
        addLiftPassButton.addActionListener(e -> addLiftPassToPackage());
        JButton addLessonsButton = new JButton("Add Lessons to Package");
        addLessonsButton.addActionListener(e -> addLessonsToPackage());

        topPanel.add(createPackageButton);
        topPanel.add(listPackagesButton);
        topPanel.add(addLiftPassButton);
        topPanel.add(addLessonsButton);

        JPanel fileOperationsPanel = createFileOperationsPanel();

        travelPackagesTab.add(topPanel, BorderLayout.NORTH);
        travelPackagesTab.add(new JScrollPane(travelPackageTextArea), BorderLayout.CENTER);
        travelPackagesTab.add(fileOperationsPanel, BorderLayout.SOUTH);
    }

    private JPanel createFileOperationsPanel() {
        JPanel panel = new JPanel();
        JButton savePackagesButton = new JButton("Save Packages to File");
        savePackagesButton.addActionListener(e -> savePackagesToFile());
        JButton readPackagesButton = new JButton("Read Packages from File");
        readPackagesButton.addActionListener(e -> readPackagesFromFile());

        panel.add(savePackagesButton);
        panel.add(readPackagesButton);
        return panel;
    }

    private void displayAccommodations() {
        accommodationTextArea.setText("");
        for (Accommodation accommodation : accommodations) {
            accommodationTextArea.append(accommodation + "\n");
        }
    }

    private void displayAvailableAccommodations() {
        accommodationTextArea.setText("");
        for (Accommodation accommodation : accommodations) {
            if (accommodation.isAvailable()) {
                accommodationTextArea.append(accommodation + "\n");
            }
        }
    }

    private void addCustomer() {
        JTextField nameField = new JTextField(10);
        JComboBox<String> skiingLevelComboBox = new JComboBox<>(new String[]{"beginner", "intermediate", "expert"});

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Customer Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Skiing Level:"));
        panel.add(skiingLevelComboBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Customer", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) return;

        String name = nameField.getText();
        String skiingLevel = (String) skiingLevelComboBox.getSelectedItem();

        int id = generateCustomerId();

        Customer customer = new Customer(id, name, skiingLevel);
        customers.add(customer);
        JOptionPane.showMessageDialog(this, "Customer added successfully with ID: " + id);
    }

    private int generateCustomerId() {
        int id = 100;
        for (Customer customer : customers) {
            if (customer.getId() >= id) {
                id = customer.getId() + 1;
            }
        }
        return id;
    }

    private void listCustomers() {
        customerTextArea.setText("");
        for (Customer customer : customers) {
            customerTextArea.append(customer + "\n");
        }
    }

    private void createPackage() {
        JComboBox<CustomerItem> customerComboBox = new JComboBox<>();
        for (Customer customer : customers) {
            customerComboBox.addItem(new CustomerItem(customer.getId(), customer.getName()));
        }

        JComboBox<AccommodationItem> accommodationComboBox = new JComboBox<>();
        for (Accommodation accommodation : accommodations) {
            if (accommodation.isAvailable()) {
                accommodationComboBox.addItem(new AccommodationItem(accommodation.getId(), accommodation.getType()));
            }
        }

        JTextField startDateField = new JTextField(10);
        JTextField durationField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Customer:"));
        panel.add(customerComboBox);
        panel.add(new JLabel("Accommodation:"));
        panel.add(accommodationComboBox);
        panel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        panel.add(startDateField);
        panel.add(new JLabel("Duration (in days):"));
        panel.add(durationField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Create Package", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) return;

        try {
            CustomerItem selectedCustomer = (CustomerItem) customerComboBox.getSelectedItem();
            AccommodationItem selectedAccommodation = (AccommodationItem) accommodationComboBox.getSelectedItem();
            String startDate = startDateField.getText();
            int duration = Integer.parseInt(durationField.getText());

            Customer customer = findCustomerById(selectedCustomer.getId());
            Accommodation accommodation = findAccommodationById(selectedAccommodation.getId());

            int packageId = generatePackageId();

            if (customer != null && accommodation != null && accommodation.isAvailable()) {
                TravelPackage travelPackage = new TravelPackage(packageId, customer.getId(), accommodation, startDate, duration);
                travelPackages.add(travelPackage);
                accommodation.markAsUnavailable();
                JOptionPane.showMessageDialog(this, "Package created successfully with ID: " + packageId);
            } else {
                JOptionPane.showMessageDialog(this, "Error creating package. Check customer ID, accommodation ID, and availability.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for duration.");
        }
    }

    private int generatePackageId() {
        int id = 200;
        for (TravelPackage travelPackage : travelPackages) {
            if (travelPackage.getPackageID() >= id) {
                id = travelPackage.getPackageID() + 1;
            }
        }
        return id;
    }

    private void listPackages() {
        travelPackageTextArea.setText("");
        for (TravelPackage travelPackage : travelPackages) {
            travelPackageTextArea.append(travelPackage + "\n");
        }
    }

    private void addLiftPassToPackage() {
        JComboBox<TravelPackageItem> packageComboBox = new JComboBox<>();
        for (TravelPackage travelPackage : travelPackages) {
            packageComboBox.addItem(new TravelPackageItem(travelPackage.getPackageID()));
        }

        JTextField daysField = new JTextField(10);
        JTextField pricePerDayField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Select Package:"));
        panel.add(packageComboBox);
        panel.add(new JLabel("Number of Days for Lift Pass:"));
        panel.add(daysField);
        panel.add(new JLabel("Price per Day for Lift Pass:"));
        panel.add(pricePerDayField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Lift Pass to Package", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) return;

        try {
            TravelPackageItem selectedPackage = (TravelPackageItem) packageComboBox.getSelectedItem();
            int days = Integer.parseInt(daysField.getText());
            double pricePerDay = Double.parseDouble(pricePerDayField.getText());

            TravelPackage travelPackage = findPackageById(selectedPackage.getId());
            if (travelPackage != null) {
                travelPackage.addLiftPass(days, pricePerDay);
                JOptionPane.showMessageDialog(this, "Lift pass added to package.");
            } else {
                JOptionPane.showMessageDialog(this, "Package not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for days or price.");
        }
    }

    private void addLessonsToPackage() {
        JComboBox<TravelPackageItem> packageComboBox = new JComboBox<>();
        for (TravelPackage travelPackage : travelPackages) {
            packageComboBox.addItem(new TravelPackageItem(travelPackage.getPackageID()));
        }

        JTextField lessonsField = new JTextField(10);
        JTextField pricePerLessonField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Select Package:"));
        panel.add(packageComboBox);
        panel.add(new JLabel("Number of Lessons:"));
        panel.add(lessonsField);
        panel.add(new JLabel("Price per Lesson:"));
        panel.add(pricePerLessonField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Lessons to Package", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) return;

        try {
            TravelPackageItem selectedPackage = (TravelPackageItem) packageComboBox.getSelectedItem();
            int lessons = Integer.parseInt(lessonsField.getText());
            double pricePerLesson = Double.parseDouble(pricePerLessonField.getText());

            TravelPackage travelPackage = findPackageById(selectedPackage.getId());
            if (travelPackage != null) {
                travelPackage.addLessons(lessons, pricePerLesson);
                JOptionPane.showMessageDialog(this, "Lessons added to package.");
            } else {
                JOptionPane.showMessageDialog(this, "Package not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for lessons or price.");
        }
    }

    private void savePackagesToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("packages.dat"))) {
            out.writeObject(travelPackages);
            JOptionPane.showMessageDialog(this, "Packages saved to file.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving packages to file: " + e.getMessage());
        }
    }

    private void readPackagesFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("packages.dat"))) {
            travelPackages = (ArrayList<TravelPackage>) in.readObject();
            JOptionPane.showMessageDialog(this, "Packages read from file.");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error reading packages from file: " + e.getMessage());
        }
    }

    private Customer findCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    private Accommodation findAccommodationById(int id) {
        for (Accommodation accommodation : accommodations) {
            if (accommodation.getId() == id) {
                return accommodation;
            }
        }
        return null;
    }

    private TravelPackage findPackageById(int id) {
        for (TravelPackage travelPackage : travelPackages) {
            if (travelPackage.getPackageID() == id) {
                return travelPackage;
            }
        }
        return null;
    }

    private void initializeData() {
     
        accommodations.add(new Accommodation(1, "Hotel Luxury", 200.00, true));
        accommodations.add(new Accommodation(2, "Ski Lodge", 120.00, true));
        accommodations.add(new Accommodation(3, "Mountain Cabin", 150.00, false));
        accommodations.add(new Accommodation(4, "Resort Condo", 180.00, true));
        accommodations.add(new Accommodation(5, "Snow Chalet", 250.00, true));
        accommodations.add(new Accommodation(6, "Winter Bungalow", 130.00, false));
        accommodations.add(new Accommodation(7, "Alpine Suite", 300.00, true));
        accommodations.add(new Accommodation(8, "Parkside Villa", 220.00, true));
        accommodations.add(new Accommodation(9, "Forest Retreat", 110.00, true));
        accommodations.add(new Accommodation(10, "Summit Room", 160.00, false));

        // Initialize customers
        customers.add(new Customer(101, "John Doe", "Intermediate"));
        customers.add(new Customer(102, "Jane Smith", "Beginner"));
        customers.add(new Customer(103, "Alice Johnson", "Expert"));
    }

    private String showInputDialog(String message) {
        return JOptionPane.showInputDialog(this, message);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ResortGUI());
    }

    private class CustomerItem {
        private int id;
        private String name;

        public CustomerItem(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return id + " - " + name;
        }
    }

    private class AccommodationItem {
        private int id;
        private String type;

        public AccommodationItem(int id, String type) {
            this.id = id;
            this.type = type;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return id + " - " + type;
        }
    }

    private class TravelPackageItem {
        private int id;

        public TravelPackageItem(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return String.valueOf(id);
        }
    }
}
