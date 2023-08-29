import java.io.Serializable;

 abstract class Vehicle implements Serializable {

    protected int id;
    protected String brand;
    protected String model;
    protected double price;
    protected boolean availability = true;
    protected String type;

    public int getId() {
         return id;
     }
    public void setId(int id) {
         this.id = id;
     }
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public boolean isAvailability() {
        return availability;
    }
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


     @Override
     public String toString() {
         return "Vehicle{" +
                 "id=" + id +
                 ", brand='" + brand + '\'' +
                 ", model='" + model + '\'' +
                 ", price=" + price +
                 ", availability=" + availability +
                 ", type='" + type + '\'' +
                 '}';
     }
 }
