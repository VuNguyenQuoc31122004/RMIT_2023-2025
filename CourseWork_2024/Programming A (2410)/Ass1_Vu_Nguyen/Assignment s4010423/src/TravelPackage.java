public class TravelPackage {

    private int packageID;
    private int customerID;
    private Accommodation accommodation; 
    private String startDate; 
    private int duration; 
    private double liftPassCost; 
    private double lessonCost;

    
    public TravelPackage(int packageID, int customerID, Accommodation accommodation,
                         String startDate, int duration) {
        this.packageID = packageID;
        this.customerID = customerID;
        this.accommodation = accommodation;
        this.startDate = startDate;
        this.duration = duration;
        this.liftPassCost = 0; 
        this.lessonCost = 0; 
    }

    
    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getLiftPassCost() {
        return liftPassCost;
    }

    public void setLiftPassCost(double liftPassCost) {
        this.liftPassCost = liftPassCost;
    }

    public double getLessonCost() {
        return lessonCost;
    }

    public void setLessonCost(double lessonCost) {
        this.lessonCost = lessonCost;
    }

   
    public void addLiftPass(int days, double pricePerDay) {
        this.liftPassCost = days * pricePerDay;
    }

    public void addLessons(int numLessons, double pricePerLesson) {
        this.lessonCost = numLessons * pricePerLesson;
    }

    
    public double calculateTotalCost() {
        return accommodation.getPrice() * duration + liftPassCost + lessonCost;
    }

    
    @Override
public String toString() {
        return String.format("Travel Package Details:\n" +
                             " - Package ID: %d\n" +
                             " - Customer ID: %d\n" +
                             " - Accommodation: %s\n" +
                             " - Start Date: %s\n" +
                             " - Duration: %d days\n" +
                             " - Lift Pass Cost: $%.2f\n" +
                             " - Lesson Cost: $%.2f\n" +
                             " - Total Cost: $%.2f",
                             packageID, customerID, accommodation, startDate, duration, liftPassCost, lessonCost, calculateTotalCost());
    }
}
