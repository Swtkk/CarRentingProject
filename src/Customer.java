import java.io.Serializable;
public class Customer implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String  phoneNumber;
    private int rentAmount = 0;

    @Override
    public String toString() {
        return  "\nId: " + id +
                "\nImię: " + name +
                "\nNazwisko: " + surname +
                "\nNumer telefonu: " + phoneNumber +
                "\nLiczba wypozyczen: " + rentAmount +
                "\n";
    }

    public void addRentAmount() {this.rentAmount += 1;}
    public void delRentAmount() {this.rentAmount -= 1;}

    public int getRentAmount() { return rentAmount; }

    public void setRentAmount(int rentAmount) { this.rentAmount = rentAmount; }

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
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Customer (int id, String name, String surname, String phoneNumber){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3");
    }
    public Customer (Customer customer){
        this.rentAmount = customer.rentAmount;
        this.id = customer.id;
        this.name = customer.name;
        this.surname = customer.surname;
        this.phoneNumber = customer.phoneNumber;
    }
}
