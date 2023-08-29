import java.io.Serializable;

public class Motorcycle extends Vehicle implements Serializable {
    private String engineType; //Two-stroke, Four-stroke
    private String typeOfDrive; //Chain Drive, Belt Drive, Shaft Drive

    public Motorcycle(int id, String type, String brand, String model, double price, String engineType, String typeOfDrive){
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.engineType = engineType;
        this.typeOfDrive = typeOfDrive;
    }

    public String getEngineType() {
        return engineType;
    }
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }
    public String getTypeOfDrive() {
        return typeOfDrive;
    }
    public void setTypeOfDrive(String typeOfDrive) {
        this.typeOfDrive = typeOfDrive;
    }


    @Override
    public String toString() {
        return "Id: "+ id +
                "\nRodzaj pojazdu: Motocykl" +
                "\nTyp: " + type +
                "\nMarka: " + brand +
                "\nModel: " + model +
                "\nCena za dzien: " + price +
                "\nDostepnosc: " + availability +
                "\nRodzaj silnika: " + engineType +
                "\nRodzaj napedu: " + typeOfDrive +
                "\n";
    }
}
