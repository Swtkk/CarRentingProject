import java.io.Serializable;

public class Truck extends Vehicle implements Serializable {
    private int payload;//ladownosc
    private int axles; //lczba osi

    public Truck(int id, String type, String brand, String model, double price, int payload, int axles){
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.payload = payload;
        this.axles = axles;
    }

    public int getPayload() {
        return payload;
    }
    public void setPayload(int payload) {
        this.payload = payload;
    }
    public int getAxles() {
        return axles;
    }
    public void setAxles(int axles) {
        this.axles = axles;
    }

    @Override
    public String toString() {
        return  "\nId: " + id +
                "\nRodzaj pojazdu: Ciezarowy" +
                "\nTyp: " + type +
                "\nMarka: " + brand +
                "\nModel: " + model +
                "\nCena za dzien: " + price +
                "\nDostepnosc: " + availability +
                "\nLadownosc: " + payload +
                "\nIlosc osi: " + axles +
                "\n";
    }
}
