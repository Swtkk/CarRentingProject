import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

public class Rental implements Serializable {
    private int id;
    private int daysPaid;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private long overdueDays = setsActualOverdueDays(); //zaległy termin zwrotu
    private Customer customer;
    private Vehicle vehicle;

    public long setsActualOverdueDays(){
        if (returnDate!= null && LocalDate.now().isAfter(returnDate)){
            return ChronoUnit.DAYS.between(LocalDate.now(), returnDate);
        }
        return 0;
    }

    public Rental(int id, Customer customer, Vehicle vehicle, LocalDate rentDate, LocalDate returnDate, int daysPaid){
        this.id = id;
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentDate= rentDate;
        this.returnDate = returnDate;
        this.daysPaid = daysPaid;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDaysPaid() {
        return daysPaid;
    }

    public void setDaysPaid(int daysPaid) {
        this.daysPaid = daysPaid;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public long getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(long overdueDays) {
        this.overdueDays = overdueDays;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "\nRENTAL ID." + id + "\n"+
                "daysPaid=" + daysPaid +
                ", rentDate=" + rentDate +
                ", returnDate=" + returnDate +
                ", overdueDays=" + overdueDays +
                "\n||Customer: id." + customer.getId() + ",  " + customer.getName() + " " + customer.getSurname() + ", nr: " + customer.getPhoneNumber() + "||" +
                "\n||Vehicle: id." + vehicle.getId() +  ", " + vehicle.getBrand() + ", " + vehicle.getModel() + ", " + vehicle.getType() + "||";
    }
}
