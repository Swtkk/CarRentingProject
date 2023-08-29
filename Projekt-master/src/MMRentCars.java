import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class MMRentCars implements Serializable {

    public ArrayList<Vehicle> vehicles = new ArrayList<>();
    public ArrayList<Customer> customers = new ArrayList<>();
    public ArrayList<Rental> rentals = new ArrayList<>();

    public void addRental(int id, Customer customer, Vehicle vehicle, LocalDate rentDate, LocalDate returnDate, int daysPaid){
        Rental newRental = new Rental(id, customer, vehicle, rentDate, returnDate, daysPaid);
        rentals.add(newRental);
    }

    public void addCustomer(int id, String name, String surname, String phoneNumber ){
        Customer customer = new Customer(id, name, surname, phoneNumber);
        customers.add(customer);
    }
    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void addCar(int id, String type, String brand, String model, double price, int seats,boolean acc){
        Car car = new Car(id, type, brand, model, price, seats, acc);
        vehicles.add(car);
    }
    public void addMotorcycle(int id, String type, String brand, String model, double price, String engineType, String typeOfDrive) {
        Motorcycle motorcycle = new Motorcycle(id, type, brand, model, price, engineType, typeOfDrive);
        vehicles.add(motorcycle);
    }

    public void addTruck(int id, String type, String brand, String model, double price, int payload, int axles) {
        Truck truck = new Truck(id, type, brand, model, price, payload, axles);
        vehicles.add(truck);
    }


    public void delCustomer(int id){
        int i = 0;
        Customer customerToDel = null;
        for (Customer customer: customers){
            if(customer.getId()==id){
                customerToDel = customer;
                i++;
            }
        }
        if (i==0)
            System.out.println("Brak klientow o podanym id!");
        else {
            customers.remove(customerToDel);
            System.out.println("Pomyslnie usunieto klienta");
        }
    }

    public void delVehicle(int id){
        int i = 0;
        Vehicle vehicleToDel = null;
        for (Vehicle vehicle: vehicles){
            if((vehicle.getId()==id)){
                vehicleToDel = vehicle;
                i++;
            }
        }
        if (i==0)
            System.out.println("Brak pojazdow o podanym id!");
        else {
            vehicles.remove(vehicleToDel);
            System.out.println("Pomyslnie usunieto pojazd");
        }
    }

    public void delRental(int id){
        int i = 0;
        Rental rentalToDel = null;
        for (Rental rental: rentals){
            if((rental.getId()==id)){
                rentalToDel = rental;
              i++;
            }
        }
        if (i==0)
            System.out.println("Brak wypozyczen o podanym id!");
        else {
            rentalToDel.getCustomer().delRentAmount();
            rentalToDel.getVehicle().setAvailability(true);
            rentals.remove(rentalToDel);
            System.out.println("Pomyslnie usunieto wypozyczenie!");
        }
    }


    public String findCustomersById(int id){
        StringBuilder customerList = new StringBuilder();
        for(Customer customer:customers){
            if (customer.getId()==id)
                customerList.append(customer).append("\n");
        }
        if(customerList.isEmpty()){
            return "Brak klienta o podanym id!";
        }
        return customerList.toString();
    }

    public Customer getCustomer(int id){
        for(Customer customer:customers){
            if (customer.getId()==id){
                return customer;
            }
        }
        return null;
    }


    public Vehicle getVehicle(int id){
        for(Vehicle vehicle:vehicles){
            if (vehicle.getId()==id){
                return vehicle;
            }
        }
        return null;
    }
    public String findCustomersByName(String name){
        StringBuilder customerList = new StringBuilder();
        for(Customer customer:customers){
            if (customer.getName().equals(name))
                customerList.append(customer).append("\n");
        }
        if(customerList.isEmpty()){
            return "Brak klienta o podanym imieniu!";
        }
        return customerList.toString();
    }
    public String findCustomersBySurname(String surname){
        StringBuilder customerList = new StringBuilder();
        for(Customer customer:customers){
            if (customer.getSurname().equals(surname))
                customerList.append(customer).append("\n");
        }
        if(customerList.isEmpty()){
            return "Brak klienta o podanym nazwisku!";
        }
        return customerList.toString();
    }
    public String findCustomersByPhoneNumber(String phoneNumber){
        StringBuilder customerList = new StringBuilder();
        for(Customer customer:customers){
            if (customer.getPhoneNumber().equals(phoneNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3")))
                customerList.append(customer).append("\n");
        }
        if(customerList.isEmpty()){
            return "Brak klienta o podanym numerze telefonu!";
        }
        return customerList.toString();
    }



    public String findVehicleById(int id){
        StringBuilder vehicleList = new StringBuilder();
        for(Vehicle vehicle:vehicles){
            if (vehicle.getId()==id)
                vehicleList.append(vehicle).append("\n");
        }
        if(vehicleList.isEmpty()){
            return "Brak klienta o id: " + id;
        }
        return vehicleList.toString();
    }
    public String findVehicleByTypeOfVehicle(String typeOfVehicle){
        StringBuilder vehicleList = new StringBuilder();
        for(Vehicle vehicle:vehicles){
            if (vehicle.getClass().getSimpleName().equals(typeOfVehicle))
                vehicleList.append(vehicle).append("\n");
        }
        if(vehicleList.isEmpty()){
            return "Brak pojazdu o typie: " + typeOfVehicle;
        }
        return vehicleList.toString();
    }
    public String findVehicleByType(String type){
        StringBuilder vehicleList = new StringBuilder();
        for(Vehicle vehicle:vehicles){
            if (vehicle.getType().equals(type))
                vehicleList.append(vehicle).append("\n");
        }
        if(vehicleList.isEmpty()){
            return "Brak pojazdu o typie: " + type;
        }
        return vehicleList.toString();
    }
    public String findVehicleByBrand(String Brand){
        StringBuilder vehicleList = new StringBuilder();
        for(Vehicle vehicle:vehicles){
            if (vehicle.getBrand().equals(Brand))
                vehicleList.append(vehicle).append("\n");
        }
        if(vehicleList.isEmpty()){
            return "Brak pojazdu o marce: " + Brand;
        }
        return vehicleList.toString();
    }
    public String findVehicleByModel(String Model){
        StringBuilder vehicleList = new StringBuilder();
        for(Vehicle vehicle:vehicles){
            if (vehicle.getModel().equals(Model))
                vehicleList.append(vehicle).append("\n");
        }
        if(vehicleList.isEmpty()){
            return "Brak pojazdu o marce: " + Model;
        }
        return vehicleList.toString();
    }
    public String findVehicleByPriceCheaper(double price){
        StringBuilder vehicleList = new StringBuilder();
        for(Vehicle vehicle:vehicles){
            if (vehicle.getPrice()<price)
                vehicleList.append(vehicle).append("\n");
        }
        if(vehicleList.isEmpty()){
            return "Brak pojazdu o cenie nizszej niz : " + price;
        }
        return vehicleList.toString();
    }
    public String findVehicleByPriceMoreExpensive(double price){
        StringBuilder vehicleList = new StringBuilder();
        for(Vehicle vehicle:vehicles){
            if (vehicle.getPrice()>price)
                vehicleList.append(vehicle).append("\n");
        }
        if(vehicleList.isEmpty()){
            return "Brak pojazdu o cenie wyzszej niz : " + price;
        }
        return vehicleList.toString();
    }
    public String findVehicleByAvailability(boolean availability){
        StringBuilder vehicleList = new StringBuilder();
        for(Vehicle vehicle:vehicles){
            if (vehicle.isAvailability()==availability)
                vehicleList.append(vehicle).append("\n");
        }
        if(vehicleList.isEmpty()){
            return "Brak takich pojazdow.";
        }
        return vehicleList.toString();
    }

}
