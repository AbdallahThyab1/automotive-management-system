package automotive.management.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AutomotiveManagementSystem implements FileOperator {

    private static ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void createVehicles() {
        System.out.println("#######  CREATE NEW VEHICLES  ######\n");

        int choice = 0;
        while (true) {
            System.out.println("Select Vehicle Type:");
            System.out.println("1 - Car");
            System.out.println("2 - Bike");
            System.out.print("Enter your choice (1 or 2): ");
            choice = Integer.parseInt(input.nextLine());
            if (choice == 1 || choice == 2) {
                break;
            }
            System.out.println("Invalid input! Please enter 1 or 2.\n");
        }

        System.out.print("Enter Vehicle Name: ");
        String name = input.nextLine();
        System.out.print("Enter Date of Manufacturing (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (choice == 1) {
            System.out.println("\n### Creating a Car ###");
            System.out.print("Enter Car Brand: ");
            String brand = input.nextLine();
            System.out.print("Enter Car Color: ");
            String color = input.nextLine();
            System.out.print("Enter Car Price: ");
            double price = Double.parseDouble(input.nextLine());
            vehicles.add(new Car(name, brand, color, price));
            System.out.println("\nCar added successfully!");

        } else {
            System.out.println("\n### Creating a Bike ###");
            System.out.print("Enter Number of Gears: ");
            int gears = Integer.parseInt(input.nextLine());
            vehicles.add(new Bike(name, gears));
            System.out.println("\nBike added successfully!");
        }
    }

    @Override
    public ArrayList<String> readFile(String path) {
        FileOperator reader = p -> {
            ArrayList<String> lines = new ArrayList<>();
            try {
                Scanner sc = new Scanner(new File(p));
                while (sc.hasNextLine()) {
                    String next = sc.nextLine();
                    lines.add(next);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
            }
            return lines;
        };
        return reader.readFile(path);
    }

    public static List<Car> getCar(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            return null;
        }
        List<Car> cars = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Car) {
                cars.add((Car) vehicle);
            }
        }
        return cars;
    }

    public static List<Bike> getBike(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            return null;
        }
        List<Bike> bikes = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Bike) {
                bikes.add((Bike) vehicle);
            }
        }
        return bikes;
    }

    public static int getNumberOfBikes(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            return 0;
        }
        int countBikes = 0;
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Bike) {
                ++countBikes;
            }
        }
        return countBikes;
    }

    public static int getNumberOfCars(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            return 0;
        }
        int countCars = 0;
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Car) {
                ++countCars;
            }
        }
        return countCars;
    }

    public ArrayList<Vehicle> createSetOfVehicles() {

        int n = 0;
        while (true) {
            System.out.print("Enter the number of vehicles you want to create: ");
            try {
                n = Integer.parseInt(input.nextLine());
                if (n > 0) {
                    break;
                }
                System.out.println("Invalid Input! Please enter a positive number.\n");
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input! Please enter a valid number.\n");
            }
        }

        ArrayList<Vehicle> returnList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Creating Vehicle " + (i + 1) + " of " + n + " ---");
            int currentSize = vehicles.size();
            createVehicles();
            Vehicle newVehicle = vehicles.get(vehicles.size() - 1);
            returnList.add(newVehicle);
        }

        System.out.println("\n Successfully created " + n + " vehicles!");
        return returnList;
    }

public static void main(String[] args) {
    System.out.println("==== AUTOMOTIVE MANAGEMENT SYSTEM ====\n");
    
 
    AutomotiveManagementSystem ams = new AutomotiveManagementSystem();
    

    String filePath = "C:\\Users\\Dream4Net\\Documents\\NetBeansProjects\\automotive-management-system\\data.txt";
    System.out.println("Reading data from file: " + filePath);
    System.out.println("---------------------------------------");
    
    ArrayList<String> fileData = ams.readFile(filePath);
    System.out.println("Found " + fileData.size() + " records in file");
    
    // 3. تحويل بيانات الملف إلى مركبات
    System.out.println("\nConverting file data to vehicles...");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    for (String line : fileData) {
        String[] parts = line.split(",");
        
        if (parts[0].equals("Car") && parts.length >= 6) {
            try {
                Car car = new Car(parts[1], parts[2], parts[3], Double.parseDouble(parts[4]));
                car.setDateOfManufacturing(LocalDate.parse(parts[5], formatter));
                vehicles.add(car);
                System.out.println("! Added car: " + parts[1]);
            } catch (Exception e) {
                System.out.println("! Error adding car: " + line);
            }
        } 
        else if (parts[0].equals("Bike") && parts.length >= 4) {
            try {
                Bike bike = new Bike(parts[1], Integer.parseInt(parts[2]));
                bike.setDateOfManufacturing(LocalDate.parse(parts[3], formatter));
                vehicles.add(bike);
                System.out.println("! Added bike: " + parts[1]);
            } catch (Exception e) {
                System.out.println("! Error adding bike: " + line);
            }
        }
    }
    
    System.out.println("\n! Total vehicles loaded: " + vehicles.size());
    
    // 4. إنشاء مجموعة جديدة من المركبات
    System.out.println("\n=========================================");
    System.out.println("CREATE NEW SET OF VEHICLES");
    System.out.println("=========================================");
    
    ArrayList<Vehicle> newVehicles = ams.createSetOfVehicles();
    System.out.println("! Created " + newVehicles.size() + " new vehicles");
    
    // 5. إضافة مركبة منفردة
    System.out.println("\n=========================================");
    System.out.println("ADD SINGLE VEHICLE");
    System.out.println("=========================================");
    createVehicles();
    
    // 6. عرض جميع المركبات
    System.out.println("\n=========================================");
    System.out.println("ALL VEHICLES IN SYSTEM (" + vehicles.size() + ")");
    System.out.println("=========================================");
    
    Vehicle[] vehiclesArray = vehicles.toArray(new Vehicle[0]);
    ams.printContent(vehiclesArray);
    
    // 7. عرض السيارات فقط
    System.out.println("\n=========================================");
    System.out.println("CARS ONLY");
    System.out.println("=========================================");
    
    List<Car> cars = ams.getCar(vehicles);
    if (cars != null) {
        System.out.println("Total cars: " + cars.size());
        for (Car car : cars) {
            System.out.println(car);
        }
    } else {
        System.out.println("No cars found");
    }
    
    // 8. عرض الدراجات فقط
    System.out.println("\n=========================================");
    System.out.println("BIKES ONLY");
    System.out.println("=========================================");
    
    List<Bike> bikes = ams.getBike(vehicles);
    if (bikes != null) {
        System.out.println("Total bikes: " + bikes.size());
        for (Bike bike : bikes) {
            System.out.println(bike);
        }
    } else {
        System.out.println("No bikes found");
    }
    
    // 9. الحصول على عدد السيارات والدراجات
    System.out.println("\n=========================================");
    System.out.println("VEHICLE COUNT");
    System.out.println("=========================================");
    
    int carCount = ams.getNumberOfCars(vehicles);
    int bikeCount = ams.getNumberOfBikes(vehicles);
    
    System.out.println("Cars: " + carCount);
    System.out.println("Bikes: " + bikeCount);
    System.out.println("Total: " + vehicles.size());
    
    // 10. مقارنة سيارتين
    System.out.println("\n=========================================");
    System.out.println("COMPARE TWO CARS");
    System.out.println("=========================================");
    
    if (cars != null && cars.size() >= 2) {
        Car car1 = cars.get(0);
        Car car2 = cars.get(1);
        
        int comparison = car1.compareTo(car2);
        System.out.println("Comparing: " + car1.getName() + " vs " + car2.getName());
        System.out.println("Result: " + comparison);
        
        if (comparison > 0) {
            System.out.println(car1.getName() + " is more expensive");
        } else if (comparison < 0) {
            System.out.println(car2.getName() + " is more expensive");
        } else {
            System.out.println("Both have same price");
        }
    } else {
        System.out.println("Need at least 2 cars for comparison");
    }
    
    // 11. استنساخ دراجة
    System.out.println("\n=========================================");
    System.out.println("CLONE A BIKE");
    System.out.println("=========================================");
    
    if (bikes != null && !bikes.isEmpty()) {
        Bike originalBike = bikes.get(0);
        try {
            Bike clonedBike = (Bike) originalBike.clone();
            System.out.println("Original: " + originalBike);
            System.out.println("Cloned: " + clonedBike);
            System.out.println("Are equal? " + originalBike.equals(clonedBike));
        } catch (Exception e) {
            System.out.println("Clone failed: " + e.getMessage());
        }
    } else {
        System.out.println("No bikes available for cloning");
    }
    

    System.out.println("\n=========================================");
    System.out.println("BIKE WITH MAX GEARS");
    System.out.println("=========================================");
    
    if (bikes != null && !bikes.isEmpty()) {
        Bike maxGearsBike = bikes.get(0);
        for (Bike bike : bikes) {
            if (bike.getGears() > maxGearsBike.getGears()) {
                maxGearsBike = bike;
            }
        }
        System.out.println("Bike with max gears: " + maxGearsBike.getName());
        System.out.println("Number of gears: " + maxGearsBike.getGears());
    } else {
        System.out.println("No bikes available");
    }
    
    System.out.println("\n=========================================");
    System.out.println("CONVERT ARRAYLIST TO ARRAY");
    System.out.println("=========================================");
    
    ArrayList<String> namesList = new ArrayList<>();
    for (Vehicle vehicle : vehicles) {
        namesList.add(vehicle.getName());
    }
    
    String[] namesArray = ams.convertArrayListToArray(namesList);
    System.out.println("Converted " + namesList.size() + " names to array");
    

    System.out.println("\n=========================================");
    System.out.println("SAVE TO OUTPUT FILE");
    System.out.println("=========================================");
    
    String outputPath = "C:\\Users\\Dream4Net\\Documents\\NetBeansProjects\\automotive-management-system\\output.txt";
    ArrayList<String> outputData = new ArrayList<>();
    

    for (Vehicle vehicle : vehicles) {
        outputData.add(vehicle.toString());
    }
    
    ams.saveFile(outputData, outputPath);
    System.out.println("✓ Saved " + vehicles.size() + " vehicles to: " + outputPath);
    

    System.out.println("\n=========================================");
    System.out.println("FINAL STATISTICS");
    System.out.println("=========================================");
    
    System.out.println("Total Vehicles Instantiated: " +(new Vehicle()).getTotalVehicle());
    System.out.println("Total Cars Made: " + getNumberOfCars(vehicles));
    System.out.println("Total Bikes Made: " + Bike.getTotalBikesMade());
    System.out.println("Current Vehicles in System: " + vehicles.size());
    System.out.println("Loaded from file: " + fileData.size());
    System.out.println("Created by user: " + (vehicles.size() - fileData.size()));
    
    System.out.println("\n==== SYSTEM RUN COMPLETE ====");
    System.out.println("Output saved to: " + outputPath);
}
}