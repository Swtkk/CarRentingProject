import java.io.*;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        MMRentCars save = new MMRentCars();//save całej bazy wypożyczalni
        IdSetter customersId = new IdSetter();//nadaje id dla klientow i  odpowiada za nadawanie kolejnych id po odczycie
        IdSetter vehiclesId = new IdSetter();//nadaje id dla pojazdow i odpowiada za nadawanie kolejnych id po odczycie
        IdSetter rentalsId = new IdSetter();//nadaje id dla wypożyczen i odpowiada za nadawanie kolejnych id po odczycie
        LocalDate currentDate = LocalDate.now();

        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        do {
            System.out.println("\\||||||||||||||||MENU|||||||||||||||||||||\n" +
                    "***KLIENCI***\n" +
                    " 1. Dodaj klienta\n" +  // zrobione
                    " 2. Usuń klienta\n" + // do zrobienia
                    " 3. Wyszukaj klientów\n" +  // Zrobione Marcin 31.05
                    " 4. Wyświetl wszystkich klientów\n" +  //zrobione
                    "\n***POJAZDY***\n" +
                    " 5. Dodaj pojazd\n" +  //zrobione
                    " 6. Usuń pojazd\n" +  // do zrobienia
                    " 7. Wyszukaj pojazd\n" +  //zrobione
                    " 8. Wyświetl wszystkie pojazdy\n" +  //zrobione
                    "\n***WYPOŻYCZENIA***\n" +
                    " 9. Dodaj wypożyczenie pojazdu\n" +   // zrobione, ale do przeanalizowania
                    "10. Zwroc pojazd\n" +  // Zrobione, ale do przeanalizowania
                    "11. Wyświetl wypożyczenia przeterminowane\n" +
                    "12. Przeglądaj wypożyczenia pojazdow\n" +  // zrobione
                    "\n***OPERACJE NA PLIKACH***\n" +
                    "13. Zapisz do pliku\n" +
                    "14. Odczytaj z pliku\n\n" +
                    "15. Wyjście");
            try {
                int options = sc.nextInt();
                switch (options) {
                    case 1 -> {
                        int id = customersId.setNextId();
                        String name, surname, phoneNumber;
                        boolean on = true;
                        do {
                            System.out.println("\nPodaj imię:");
                            name = sc.next();
                            Pattern patternName = Pattern.compile("[A-Z][a-z]{1,20}");
                            Matcher matcherName = patternName.matcher(name);
                            if (!matcherName.matches())
                                System.out.println("Imie musi skladac sie z samych liter i zaczynac sie od wielkiej litery!\n");
                            else on = false;
                        } while (on);
                        on = true;
                        do {
                            System.out.println("\nPodaj nazwisko:");
                            surname = sc.next();
                            Pattern patternSurn = Pattern.compile("[A-Z][a-z]{1,30}");
                            Matcher matcherSurn = patternSurn.matcher(surname);
                            if (!matcherSurn.matches())
                                System.out.println("Nazwisko musi skladac sie z samych liter i zaczynac sie od wielkiej litery!\n");
                            else on = false;
                        } while (on);
                        on = true;

                        do {
                            System.out.println("\nPodaj numer telefonu:");
                            phoneNumber = sc.next();
                            Pattern patternPhon = Pattern.compile("\\d{9}");
                            Matcher matcherPhon = patternPhon.matcher(phoneNumber);
                            if (!matcherPhon.matches())
                                System.out.println("Numer musi skladac sie z samych 9 cyfr!\n");
                            else on = false;
                        } while (on);

                        save.addCustomer(id, name, surname, phoneNumber);
                        System.out.println("Klient zostal dodany poprawnie!");
                        System.out.println("\nAby wrocic do menu wprowadz dowolna wartosc.");
                        String hold = sc.next();
                    } //dodaj klienta
                    case 2 -> {

                        System.out.println("Podaj id klienta, którego chcesz usunąć z bazy:\n");

                        try {
                            int id = sc.nextInt();
                            save.delCustomer(id);
                        } catch (InputMismatchException e) {
                            System.out.println("Podano błędną wartość!");
                        }
                        System.out.println("\nAby wrocic do menu wprowadz dowolna wartosc.");
                        String hold = sc.next();
                    } //usun klienta
                    case 3 -> {

                        boolean exitCustomer = false;
                        do {
                            System.out.println("\nWyszukaj klienta:\n" +
                                    "1.Po ID\n" +
                                    "2.Po imieniu\n" +
                                    "3.Po nazwisku\n" +
                                    "5.Wroc do poprzedniego menu");
                            try {
                                int optionsCustomer = sc.nextInt();
                                switch (optionsCustomer) {
                                    case 1 -> {
                                        System.out.println("Podaj id:\n");
                                        try {
                                            int id = sc.nextInt();
                                            System.out.println(save.findCustomersById(id));
                                        } catch (InputMismatchException e) {
                                            System.out.println("Podano błędną wartość!");
                                        }
                                        System.out.println("\nAby wrocic do menu wyszukiwania klientow wprowadz dowolna wartosc.");
                                        String hold = sc.next();
                                    }
                                    case 2 -> {
                                        boolean on = true;
                                        do {
                                            System.out.println("Podaj imię\n");
                                            String name = sc.next();
                                            Pattern patternName = Pattern.compile("[A-Z][a-z]{1,20}");
                                            Matcher matcherName = patternName.matcher(name);
                                            if (!matcherName.matches())
                                                System.out.println("Imie musi rozpoczynac sie od wielkiej litery i skladac sie tylko z liter!\n" +
                                                        "Sprobuj jeszcze raz.");
                                            else {
                                                System.out.println(save.findCustomersByName(name));
                                                on = false;
                                            }
                                        } while (on);
                                        System.out.println("\nAby wrocic do menu wyszukiwania klientow wprowadz dowolna wartosc.");
                                        String hold = sc.next();
                                    }
                                    case 3 -> {
                                        boolean on = true;
                                        do {
                                            System.out.println("Podaj nazwisko:\n");
                                            String surname = sc.next();
                                            Pattern patternName = Pattern.compile("[A-Z][a-z]{1,20}");
                                            Matcher matcherName = patternName.matcher(surname);
                                            if (!matcherName.matches())
                                                System.out.println("Nazwisko musi rozpoczynac sie od wielkiej litery i skladac sie tylko z liter!\n" +
                                                        "Sprobuj jeszcze raz.");
                                            else {
                                                System.out.println(save.findCustomersBySurname(surname));
                                                on = false;
                                            }
                                        } while (on);
                                    }
                                    case 5 -> {
                                        exitCustomer = true;
                                    }
                                    default -> System.out.println("Podana opcja nie istnieje!\n");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Podano błędną wartość!");
                                exitCustomer = true;
                            }

                        } while (!exitCustomer);
                    } //wyszukaj klienta
                    case 4 -> {
                        System.out.println("Lista klientow MMRentCars:");
                        for (Customer customer : save.customers) {
                            System.out.println(customer.toString());
                        }
                        System.out.println("\nAby wrocic do menu wprowadz dowolna wartosc.");
                        String hold = sc.next();
                    } //wyswietl klienow
                    case 5 -> {
                        try {
                            int veh;
                            System.out.println("Dodaj pojazd:\n" +
                                    "1. Samochód osobowy\n" +
                                    "2. Motocykl\n" +
                                    "3. Ciężarówka\n" +
                                    "4. Wroc do menu\n");
                            veh = sc.nextInt();
                            if (veh == 1) {
                                int id = vehiclesId.setNextId();
                                String type, brand, model;
                                type = "Samochód osobowy";
                                Double price;
                                boolean acc;
                                int seats;

                                System.out.println("Wybraleś samochód osobowy");
                                System.out.println("Podaj marke:");
                                brand = sc.next();
                                System.out.println("\nPodaj model:");
                                model = sc.next();
                                System.out.println("\nPodaj cene za dobe:");
                                price = sc.nextDouble();
                                System.out.println("\nPodaj liczbę miejsc w aucie:");
                                seats = sc.nextInt();
                                System.out.println("\nCzy auto posiada klimatyzacje?" +
                                        "\n1.Tak" +
                                        "\n2.Nie");
                                int k = sc.nextInt();
                                if (k == 1) {
                                    acc = true;
                                } else {
                                    acc = false;
                                }
                                save.addCar(id, type, brand, model, price, seats, acc);
                                System.out.println("Pojazd zostal dodany poprawnie!");

                            } else if (veh == 2) {
                                int id = vehiclesId.setNextId();
                                String type, brand, model, engineType, typeOfDrive;
                                type = "Motocykl";
                                Double price;
                                boolean availability;

                                System.out.println("Wybrałeś motocykl");
                                System.out.println("\nPodaj marke:");
                                brand = sc.next();
                                System.out.println("\nPodaj model:");
                                model = sc.next();
                                System.out.println("\nPodaj cene:");
                                price = sc.nextDouble();

                                System.out.println("\nPodaj typ silnika:" +
                                        "\n1. 2 suw" +
                                        "\n2. 4 suw");
                                int i = sc.nextInt();
                                if (i == 1) {
                                    engineType = "2 suw";
                                } else if (i == 2) {
                                    engineType = "4 suw";
                                } else engineType = "?";
                                System.out.println("\nPodaj typ napędu:" +
                                        "\n1. Łańcuch" +
                                        "\n2. Pasek" +
                                        "\n3. Wał");
                                int k = sc.nextInt();
                                if (k == 1) {
                                    typeOfDrive = "Łańcuch";
                                } else if (k == 2) {
                                    typeOfDrive = "Pasek";
                                } else if (k == 3) {
                                    typeOfDrive = "Wał";
                                } else typeOfDrive = "?";
                                save.addMotorcycle(id, type, brand, model, price, engineType, typeOfDrive);
                                System.out.println("Pojazd zostal dodany poprawnie!");


                            } else if (veh == 3) {
                                int id = vehiclesId.setNextId();
                                String type, brand, model;
                                double price;
                                boolean availability;
                                int payload, axles;
                                type = "Ciezarowka";

                                System.out.println("Wybrałeś ciężarówke");
                                System.out.println("\nPodaj marke:");
                                brand = sc.next();
                                System.out.println("\nPodaj model:");
                                model = sc.next();
                                System.out.println("\nPodaj cene:");
                                price = sc.nextDouble();

                                System.out.println("\nPodaj ładowność:");
                                payload = sc.nextInt();
                                System.out.println("\nPodaj liczbe osi:");
                                axles = sc.nextInt();
                                save.addTruck(id, type, brand, model, price, payload, axles);
                                System.out.println("Pojazd zostal dodany poprawnie!");
                            } else if (veh == 4) {
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Podano błędną wartość! Spróbuj jeszcze raz");
                        }

                        System.out.println("\nAby wrocic do menu wprowadz dowolna wartosc.");
                        String hold = sc.next();
                    } //dodaj pojazd
                    case 6 -> {
                        System.out.println("Podaj id pojazdu, ktory chcesz usunac:\n");

                        try {
                            int id = sc.nextInt();
                            save.delVehicle(id);
                        } catch (InputMismatchException e) {
                            System.out.println("Podano błędną wartość!");
                        }

                        System.out.println("\nAby wrocic do menu wprowadz dowolna wartosc.");
                        String hold = sc.next();
                    } //usun pojazd
                    case 7 -> {
                        try {
                            boolean exitVehicle = false;
                            do {
                                System.out.println("\n1.Po ID\n" +
                                        "2.Po rodzaju pojazdu (osobowy/motocykl/ciezarowka)\n" +
                                        "3.Po marce\n" +
                                        "4.Po modelu\n" +
                                        "5.Po cenie\n" +
                                        "6.Po dostepnosci\n" +
                                        "7.Wroc do poprzedniego menu\n");
                                int optionsVehicles = sc.nextInt();
                                switch (optionsVehicles) {
                                    case 1 -> {
                                        System.out.println("Podaj id:\n");
                                        int id = sc.nextInt();
                                        System.out.println(save.findVehicleById(id));
                                    }
                                    case 2 -> {

                                        System.out.println("1.Samochod osobowy:\n" +
                                                "2.Motocykl\n" +
                                                "3.Ciezarowka\n");
                                        int type = sc.nextInt();
                                        if (type == 1) {
                                            System.out.println(save.findVehicleByTypeOfVehicle("Car"));
                                        }
                                        if (type == 2) {
                                            System.out.println(save.findVehicleByTypeOfVehicle("Motorcycle"));
                                        }
                                        if (type == 3) {
                                            System.out.println(save.findVehicleByTypeOfVehicle("Truck"));
                                        } else System.out.println("");

                                    }
                                    case 3 -> {
                                        System.out.println("Podaj marke:\n");
                                        String brand = sc.next();
                                        System.out.println(save.findVehicleByBrand(brand));
                                    }
                                    case 4 -> {
                                        System.out.println("Podaj model:\n");
                                        String model = sc.next();
                                        System.out.println(save.findVehicleByModel(model));
                                    }
                                    case 5 -> {
                                        System.out.println("Podaj kwote:\n");
                                        double price = sc.nextDouble();
                                        System.out.println("Chcesz wyszukac tansze czy drozsze pojazdy?\n" +
                                                "1.Tansze\n" +
                                                "2.Drozsze\n");
                                        int dec = sc.nextInt();
                                        if (dec == 1) {
                                            System.out.println(save.findVehicleByPriceCheaper(price));
                                        }
                                        if (dec == 2) {
                                            System.out.println(save.findVehicleByPriceMoreExpensive(price));
                                        } else System.out.println("\n");

                                    }
                                    case 6 -> {
                                        boolean availability;
                                        System.out.println("Wybierz, ktore chesz wyszukac:\n" +
                                                "1.Dostepne\n" +
                                                "2.Niedostepne\n");
                                        int dec = sc.nextInt();
                                        if (dec == 1) {
                                            availability = true;
                                            System.out.println(save.findVehicleByAvailability(availability));
                                        }
                                        if (dec == 2) {
                                            availability = false;
                                            System.out.println(save.findVehicleByAvailability(availability));
                                        }

                                    }
                                    case 7 -> {
                                        exitVehicle = true;
                                    }
                                    default -> System.out.println("Podana opcja nie istnieje!\n");
                                }
                            } while (!exitVehicle);
                        } catch (InputMismatchException e) {
                            System.out.println("Podano błędną wartość!");
                            break;
                        }

                    } //wyszukaj pojazd
                    case 8 -> {
                        System.out.println("Lista pojazdow MMRentCars:\n");
                        for (Vehicle vehicle : save.vehicles) {
                            System.out.println(vehicle.toString() + "\n");
                        }
                        System.out.println("\nAby wrocic do menu wprowadz dowolna wartosc.");
                        String hold = sc.next();
                    } //wyswietl wszystkie pojazdy
                    case 9 -> {
                        Customer rentCustomer = null;
                        Vehicle rentVehicle = null;
                        int rentId = rentalsId.setNextId();
                        int daysPaid = 0;
                        LocalDate rentDate = currentDate;
                        LocalDate returnDate = currentDate;

                        boolean onAdding = true;
                        do {
                            int i = 0;
                            System.out.println("\nOpcje\n" +
                                    "1. Przypisz klienta\n" +
                                    "2. Przypisz pojazd\n" +
                                    "3. Dodaj termin zwrotu\n" +
                                    "4. Zatwierdz wypozyczenie\n" +
                                    "9. Wroc do menu");
                            int whatDo = sc.nextInt();
                            switch (whatDo) {
                                case 1 -> {
                                    try {
                                        System.out.println("Czy klient istnieje w bazie?\n" +
                                                "1.Tak, znam id\n" +
                                                "2.Tak, wyszukaj klienta\n" +
                                                "3.Nie, dodaj klienta do bazy ");
                                        int isInBase = sc.nextInt();
                                        switch (isInBase) {
                                            case 1 -> {

                                                System.out.println("Podaj id:\n");
                                                int id = sc.nextInt();
                                                rentCustomer = save.getCustomer(id);
                                                if (rentCustomer == null) {
                                                    System.out.println("Brak klienta o podanym id!");
                                                } else {
                                                    System.out.println("Pomyslnie przypisano klienta!");
                                                }
                                            } //klient w bazie zna id
                                            case 2 -> {
                                                boolean exitCustomer = false;
                                                boolean exitnow = false;
                                                do {
                                                    System.out.println("\nWyszukaj klienta:\n" +
                                                            "1.Po ID\n" +
                                                            "2.Po imieniu\n" +
                                                            "3.Po nazwisku\n" +
                                                            "5.Wroc");
                                                    int optionsCustomer = sc.nextInt();
                                                    switch (optionsCustomer) {
                                                        case 1 -> {
                                                            System.out.println("Podaj id:\n");
                                                            int id = sc.nextInt();
                                                            System.out.println(save.findCustomersById(id));
                                                            System.out.println("Podaj id do przypisania:");
                                                            int idAssign = sc.nextInt();
                                                            rentCustomer = save.getCustomer(idAssign);
                                                            if (rentCustomer == null) {
                                                                System.out.println("Brak klienta o podanym id!");
                                                            } else {
                                                                System.out.println("Pomyslnie przypisano klienta!\n");
                                                                exitnow = true;
                                                            }
                                                        } //szukaj po id
                                                        case 2 -> {
                                                            boolean on = true;
                                                            do {
                                                                System.out.println("Podaj imię\n");
                                                                String name = sc.next();
                                                                Pattern patternName = Pattern.compile("[A-Z][a-z]{1,20}");
                                                                Matcher matcherName = patternName.matcher(name);
                                                                if (!matcherName.matches())
                                                                    System.out.println("Imie musi rozpoczynac sie od wielkiej litery i skladac sie tylko z liter!\n" +
                                                                            "Sprobuj jeszcze raz.");
                                                                else {
                                                                    System.out.println(save.findCustomersByName(name));
                                                                    on = false;
                                                                }
                                                            } while (on);

                                                            System.out.println("Podaj id do przypisania:");
                                                            int idAssign = sc.nextInt();
                                                            rentCustomer = save.getCustomer(idAssign);
                                                            if (rentCustomer == null) {
                                                                System.out.println("Brak klienta o podanym id!");
                                                            } else {
                                                                System.out.println("Pomyslnie przypisano klienta!\n");
                                                                exitnow = true;
                                                            }

                                                        } //szukaj po imieniu
                                                        case 3 -> {
                                                            boolean on = true;
                                                            do {
                                                                System.out.println("Podaj nazwisko:\n");
                                                                String surname = sc.next();
                                                                Pattern patternName = Pattern.compile("[A-Z][a-z]{1,20}");
                                                                Matcher matcherName = patternName.matcher(surname);
                                                                if (!matcherName.matches())
                                                                    System.out.println("Nazwisko musi rozpoczynac sie od wielkiej litery i skladac sie tylko z liter!\n" +
                                                                            "Sprobuj jeszcze raz.");
                                                                else {
                                                                    System.out.println(save.findCustomersBySurname(surname));
                                                                    on = false;
                                                                }
                                                            } while (on);

                                                            System.out.println("Podaj id do przypisania:");
                                                            int idAssign = sc.nextInt();
                                                            rentCustomer = save.getCustomer(idAssign);
                                                            if (rentCustomer == null) {
                                                                System.out.println("Brak klienta o podanym id!");
                                                            } else {
                                                                System.out.println("Pomyslnie przypisano klienta!\n");
                                                                exitnow = true;
                                                            }
                                                        } //szukaj po nazwisku

                                                        case 5 -> {
                                                            exitCustomer = true;
                                                        }
                                                    }
                                                    if (exitnow == true) break;
                                                } while (exitCustomer);

                                            } //klient w bazie wyszukaj
                                            case 3 -> {
                                                int id = customersId.setNextId();
                                                String name, surname, phoneNumber;
                                                boolean on = true;
                                                do {
                                                    System.out.println("\nPodaj imię:");
                                                    name = sc.next();
                                                    Pattern patternName = Pattern.compile("[A-Z][a-z]{1,20}");
                                                    Matcher matcherName = patternName.matcher(name);
                                                    if (!matcherName.matches())
                                                        System.out.println("Imie musi skladac sie z samych liter i zaczynac sie od wielkiej litery!\n");
                                                    else on = false;
                                                } while (on);
                                                on = true;
                                                do {
                                                    System.out.println("\nPodaj nazwisko:");
                                                    surname = sc.next();
                                                    Pattern patternSurn = Pattern.compile("[A-Z][a-z]{1,30}");
                                                    Matcher matcherSurn = patternSurn.matcher(surname);
                                                    if (!matcherSurn.matches())
                                                        System.out.println("Nazwisko musi skladac sie z samych liter i zaczynac sie od wielkiej litery!\n");
                                                    else on = false;
                                                } while (on);
                                                on = true;

                                                do {
                                                    System.out.println("\nPodaj numer telefonu:");
                                                    phoneNumber = sc.next();
                                                    Pattern patternPhon = Pattern.compile("\\d{9}");
                                                    Matcher matcherPhon = patternPhon.matcher(phoneNumber);
                                                    if (!matcherPhon.matches())
                                                        System.out.println("Numer musi skladac sie z samych 9 cyfr!\n");
                                                    else on = false;
                                                } while (on);

                                                Customer customer = new Customer(id, name, surname, phoneNumber);
                                                save.addCustomer(customer);
                                                rentCustomer = customer;
                                                System.out.println("Pomyslnie przypisano klienta!");
                                            } //nowy klient
                                            default -> {
                                                System.out.println("Brak podanej opcji!");
                                                ;
                                            }
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Podano błędną wartość!");
                                    }

                                } //przypisz klienta
                                case 2 -> {
                                    try {
                                        if (rentVehicle == null) {
                                            System.out.println("Czy znasz id pojazdu?\n" +
                                                    "1.Tak, znam id\n" +
                                                    "2.Nie, wyszukaj pojazd");
                                            int whichVehicle = sc.nextInt();
                                            switch (whichVehicle) {
                                                case 1 -> {
                                                    System.out.println("Podaj id:\n");
                                                    int id = sc.nextInt();
                                                    rentVehicle = save.getVehicle(id);
                                                    if (rentVehicle == null) {
                                                        System.out.println("Brak pojazdu o podanym id!");
                                                    } else if (!rentVehicle.isAvailability()) {
                                                        System.out.println("Wybrany pojazd jest niedostępny!");
                                                        rentVehicle = null;
                                                    } else {
                                                        System.out.println("Pomyslnie przypisano pojazd!");
                                                        i++;
                                                        rentVehicle.setAvailability(false);
                                                    }
                                                } //przypisz pojazd znam id
                                                case 2 -> {


                                                    boolean exitVehicle = false;
                                                    boolean exitnow = false;
                                                    do {
                                                        System.out.println("\n1.Po ID\n" +
                                                                "2.Po rodzaju pojazdu (osobowy/motocykl/ciezarowka)\n" +
                                                                "3.Po marce\n" +
                                                                "4.Po modelu\n" +
                                                                "5.Po cenie\n" +
                                                                "6.Po dostepnosci\n");
                                                        int optionsVehicles = sc.nextInt();
                                                        switch (optionsVehicles) {
                                                            case 1 -> {
                                                                System.out.println("Podaj id:\n");
                                                                int id = sc.nextInt();
                                                                System.out.println(save.findVehicleById(id));

                                                                System.out.println("Podaj id pojazdu do przypisania:\n");
                                                                int idVeh = sc.nextInt();
                                                                rentVehicle = save.getVehicle(idVeh);
                                                                if (rentVehicle == null) {
                                                                    System.out.println("Brak pojazdu o podanym id!");
                                                                } else if (!rentVehicle.isAvailability()) {
                                                                    System.out.println("Wybrany pojazd jest niedostępny!");
                                                                    rentVehicle = null;
                                                                } else {
                                                                    System.out.println("Pomyslnie przypisano pojazd!");
                                                                    rentVehicle.setAvailability(false);
                                                                    exitnow = true;
                                                                }
                                                            } //id
                                                            case 2 -> {
                                                                System.out.println("1.Samochod osobowy:\n" +
                                                                        "2.Motocykl\n" +
                                                                        "3.Ciezarowka\n");
                                                                int type = sc.nextInt();
                                                                if (type == 1) {
                                                                    System.out.println(save.findVehicleByTypeOfVehicle("Car"));
                                                                }
                                                                if (type == 2) {
                                                                    System.out.println(save.findVehicleByTypeOfVehicle("Motorcycle"));
                                                                }
                                                                if (type == 3) {
                                                                    System.out.println(save.findVehicleByTypeOfVehicle("Truck"));
                                                                } else System.out.println("");

                                                                System.out.println("Podaj id pojazdu do przypisania:\n");
                                                                int idVeh = sc.nextInt();
                                                                rentVehicle = save.getVehicle(idVeh);
                                                                if (rentVehicle == null) {
                                                                    System.out.println("Brak pojazdu o podanym id!");
                                                                } else if (!rentVehicle.isAvailability()) {
                                                                    System.out.println("Wybrany pojazd jest niedostępny!");
                                                                    rentVehicle = null;
                                                                } else {
                                                                    System.out.println("Pomyslnie przypisano pojazd!");
                                                                    rentVehicle.setAvailability(false);
                                                                    exitnow = true;
                                                                }

                                                            } //rodzaj
                                                            case 3 -> {
                                                                System.out.println("Podaj marke:\n");
                                                                String brand = sc.next();
                                                                System.out.println(save.findVehicleByBrand(brand));
                                                                System.out.println("Podaj id pojazdu do przypisania:\n");
                                                                int idVeh = sc.nextInt();
                                                                rentVehicle = save.getVehicle(idVeh);
                                                                if (rentVehicle == null) {
                                                                    System.out.println("Brak pojazdu o podanym id!");
                                                                } else if (!rentVehicle.isAvailability()) {
                                                                    System.out.println("Wybrany pojazd jest niedostępny!");
                                                                    rentVehicle = null;
                                                                } else {
                                                                    System.out.println("Pomyslnie przypisano pojazd!");
                                                                    rentVehicle.setAvailability(false);
                                                                    exitnow = true;
                                                                }

                                                            } //marka
                                                            case 4 -> {
                                                                System.out.println("Podaj model:\n");
                                                                String model = sc.next();
                                                                System.out.println(save.findVehicleByModel(model));

                                                                System.out.println("Podaj id pojazdu do przypisania:\n");
                                                                int idVeh = sc.nextInt();
                                                                rentVehicle = save.getVehicle(idVeh);
                                                                if (rentVehicle == null) {
                                                                    System.out.println("Brak pojazdu o podanym id!");
                                                                } else if (!rentVehicle.isAvailability()) {
                                                                    System.out.println("Wybrany pojazd jest niedostępny!");
                                                                    rentVehicle = null;
                                                                } else {
                                                                    System.out.println("Pomyslnie przypisano pojazd!");
                                                                    rentVehicle.setAvailability(false);
                                                                    exitnow = true;
                                                                }

                                                            } //model
                                                            case 5 -> {
                                                                System.out.println("Podaj kwote:\n");
                                                                double price = sc.nextDouble();
                                                                System.out.println("Chcesz wyszukac tansze czy drozsze pojazdy?\n" +
                                                                        "1.Tansze\n" +
                                                                        "2.Drozsze\n");
                                                                int dec = sc.nextInt();
                                                                if (dec == 1) {
                                                                    System.out.println(save.findVehicleByPriceCheaper(price));
                                                                }
                                                                if (dec == 2) {
                                                                    System.out.println(save.findVehicleByPriceMoreExpensive(price));
                                                                } else System.out.println("\n");

                                                                System.out.println("Podaj id pojazdu do przypisania:\n");
                                                                int idVeh = sc.nextInt();
                                                                rentVehicle = save.getVehicle(idVeh);
                                                                if (rentVehicle == null) {
                                                                    System.out.println("Brak pojazdu o podanym id!");
                                                                } else if (!rentVehicle.isAvailability()) {
                                                                    System.out.println("Wybrany pojazd jest niedostępny!");
                                                                    rentVehicle = null;
                                                                } else {
                                                                    System.out.println("Pomyslnie przypisano pojazd!");
                                                                    rentVehicle.setAvailability(false);
                                                                    exitnow = true;
                                                                }

                                                            } //cena
                                                            case 6 -> {
                                                                boolean availability;
                                                                System.out.println("Pojazdy dostępne:");
                                                                System.out.println(save.findVehicleByAvailability(true));

                                                                System.out.println("Podaj id pojazdu do przypisania:\n");
                                                                int idVeh = sc.nextInt();
                                                                rentVehicle = save.getVehicle(idVeh);
                                                                if (rentVehicle == null) {
                                                                    System.out.println("Brak pojazdu o podanym id!");
                                                                } else if (!rentVehicle.isAvailability()) {
                                                                    System.out.println("Wybrany pojazd jest niedostępny!");
                                                                    rentVehicle = null;
                                                                } else {
                                                                    System.out.println("Pomyslnie przypisano pojazd!");
                                                                    rentVehicle.setAvailability(false);
                                                                    exitnow = true;
                                                                }

                                                            } //dostepnosc
                                                            default -> System.out.println("Podana opcja nie istnieje!\n");
                                                        }
                                                        if (exitnow = true) break;
                                                    } while (!exitVehicle);


                                                } //przypisz pojazd wyszukaj pojazd
                                                default -> {
                                                    System.out.println("Wybrano błędną opcję!");
                                                }
                                            }
                                        } else System.out.println("Pojazd został już przypisany");

                                    } catch (InputMismatchException e) {
                                        System.out.println("Podano błędną wartość!");
                                    }
                                } //przypisz pojazd
                                case 3 -> {
                                    try {
                                        System.out.println("Podaj ilość dni na ile chcesz wypożyczyć pojazd.");
                                        int days = sc.nextInt();
                                        returnDate = currentDate.plusDays(days);
                                        daysPaid = days;

                                        System.out.println("Data dzisiejsza: " + rentDate + "\n" +
                                                "Data zwrotu: " + returnDate + "\n" +
                                                "Liczba dni wynajmu: " + daysPaid);
                                    } catch (InputMismatchException e) {
                                        System.out.println("Podano błędną wartość!");
                                    }

                                } //dodaj termin zwrotu
                                case 4 -> {
                                    try {
                                        System.out.println("Aby potwierdzić wpisz 7.");
                                        int dec = sc.nextInt();
                                        if (dec != 7) {
                                            System.out.println("Powrót do menu.");
                                        }
                                        if (dec == 7) {
                                            if (rentCustomer != null && rentVehicle != null && daysPaid > 0) {
                                                save.addRental(rentId, rentCustomer, rentVehicle, rentDate, returnDate, daysPaid);
                                                System.out.println("Pomyślnie dodano wypozyczenie!");
                                                rentCustomer.addRentAmount();
                                                rentVehicle.setAvailability(false);
                                                onAdding = false;
                                            } else
                                                System.out.println("Aby potwierdzić wypozyczenie musisz przypisać pojazd oraz klienta,\n" +
                                                        "a także podać długość wypozyczenia!");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Podano błędną wartość!");
                                    }

                                } //potwierdzenie
                                case 9 -> {
                                    if (rentVehicle != null) {
                                        rentVehicle.setAvailability(true);
                                        rentVehicle = null;
                                    }
                                    if (rentCustomer != null) {
                                        rentCustomer = null;
                                    }
                                    System.out.println("Powrót do menu.");
                                    onAdding = false;
                                }//powrot do menu
                                default -> {
                                    System.out.println("Brak podanej opcji!");
                                }
                            }
                        }while (onAdding) ;

                    } //dodaj wypożyczenie
                    case 10 -> {
                            try {
                                System.out.println("Czy pamiętasz nr id wypozyczenia?\n" +
                                        "1. Tak\n" +
                                        "2. Nie");
                                int dec = sc.nextInt();
                                if (dec == 2) {
                                    System.out.println("Lista wypozyczen:");
                                    for (Rental rental : save.rentals) {
                                        if (!rental.getVehicle().isAvailability()) {
                                            System.out.println(rental.toString());
                                        }
                                    }
                                }
                                if (dec != 1 && dec != 2) {
                                    System.out.println("Podana opcja nie istnieje!");
                                    break;
                                }

                                System.out.println("Podaj id wypozyczenia do zwrotu");
                                int idRental = sc.nextInt();
                                save.delRental(idRental);
                            } catch (InputMismatchException e) {
                                System.out.println("Podano błędną wartość!");
                            }

                            System.out.println("\nAby wrocic do menu wprowadz dowolna wartosc.");
                            String hold = sc.next();
                        } //zwroc pojazd
                    case 11 -> {
                            try {
                                System.out.println("Lista wypozyczen MMRentCars z zaleglym terminem zwrotu:");
                                int i = 0;
                                for (Rental rentedVehicle : save.rentals) {
                                    if (rentedVehicle.getOverdueDays() > 0) {
                                        System.out.println(rentedVehicle.toString());
                                        i++;
                                    }
                                }
                                if (i == 0) {
                                    System.out.println("Brak wypozyczen z zaleglym terminem zwrotu! :)");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Podano błędną wartość!");
                            }

                            System.out.println("\nAby wrocic do menu wprowadz dowolna wartosc.");
                            String hold = sc.next();
                        } //wypozyczenia z zaleglym terminem zwrotu
                    case 12 -> {
                            System.out.println("Lista wypozyczen MMRentCars:");
                            for (Rental rentedVehicle : save.rentals) {
                                System.out.println(rentedVehicle.toString());
                            }
                            System.out.println("\nAby wrocic do menu wprowadz dowolna wartosc.");
                            String hold = sc.next();
                        } //przegladaj wypozyczenia
                    case 13 -> {
                            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("MMRentCars.dat"));
                            out.writeObject(customersId);
                            out.writeObject(vehiclesId);
                            out.writeObject(save);
                            out.close();
                            System.out.println("Zapisano pomyslnie!");
                            System.out.println("\nAby wrocic do menu wprowadz dowolna wartosc.");
                            String hold = sc.next();
                        } //zapis
                    case 14 -> {
                            try {
                                ObjectInputStream in = new ObjectInputStream(new FileInputStream("MMRentCars.dat"));
                                customersId = (IdSetter) in.readObject();
                                vehiclesId = (IdSetter) in.readObject();
                                save = (MMRentCars) in.readObject();
                                in.close();
                                System.out.println("Wczytano pomyslnie!");
                            } catch (InvalidClassException e) {
                                System.out.println("Poprzedni zapis pochodzi z wcześniejszej wersji pliku!");
                            }
                            System.out.println("\nAby wrocic do menu wprowadz dowolna wartosc.");
                            String hold = sc.next();
                        } //odczyt
                    case 15 -> {
                            exit = true;
                        } //wyjscie
                    default -> {
                            System.out.println("Wybrana opcja nie istnieje: " + options);
                        } //default
                }
            }
            catch (InputMismatchException e){
                System.out.println("Podano błędną wartość!");
                sc.nextLine();
            }
        }
        while (!exit);
    }
}

//3) Wypożyczalnia pojazdów
//        Aplikacja wspomagająca wypożyczanie pojazdów. Podstawowe funkcjonalności:
//        - dodawanie/usuwanie/wyszukiwanie/przeglądanie klientów
//        - dodawanie/usuwanie/ wyszukiwanie/przeglądanie pojazdów, obsługa 3 rodzajów pojazdów (np.: samochody osobowe,
//        samochody ciężarowe, motocykle), które posiadają wspólne atrybuty i specyficzne dla danego typu
//        - dodawanie/przegląd wypożyczeń (klient, pojazd, okres, przebieg, stan)
//        - wyświetlanie pojazdów dostępnych, wypożyczonych, oraz z zaległym terminem zwrotu
//        - zapis/odczyt z pliku
