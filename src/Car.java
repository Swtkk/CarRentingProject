import java.io.Serializable;

public class Car extends Vehicle implements Serializable {
    private int seats;
    private boolean acc;

    public Car(int id, String type, String brand, String model, double price, int seats, boolean acc){
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.seats = seats;
        this.acc = acc;
    }

    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }
    public boolean isAcc() {
        return acc;
    }
    public void setAcc(boolean acc) {
        this.acc = acc;
    }

    @Override
    public String toString() {
        return  "Id: " + id +
                "\nRodzaj pojazdu: Samochod osobowy" +
                "\nTyp: " + type +
                "\nMarka: " + brand +
                "\nModel: " + model +
                "\nCena za dzien: " + price +
                "\nDostepnosc: " + availability +
                "\nIlosc miejsc: " + seats +
                "\nKlimatyzacja: " + acc +
                "\n";
    }
}
