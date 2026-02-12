package automotive.management.system;

import java.time.LocalDate;

public class Vehicle {

    private static int totalVehicle = 0;
    private String name;
    private LocalDate dateOfManufacturing = LocalDate.parse("2000-01-01");
    private final int id;

    public Vehicle() {
        this.id = ++totalVehicle;
    }

    public Vehicle(String name) {
        this();
        this.name = name;
    }

    public Vehicle(String name, LocalDate dateOfManufacturing) {
        this(name);
        this.dateOfManufacturing = dateOfManufacturing;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getDateOfManufacturing() {
        return this.dateOfManufacturing;
    }

    public int getVehicleID() {
        return this.id;
    }

    public int getTotalVehicle() {
        return Vehicle.totalVehicle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfManufacturing(LocalDate dateOfManufacturing) {
        this.dateOfManufacturing = dateOfManufacturing;
    }

    @Override
    public String toString() {
        if (this == null) {
            return "null Object";
        }
        String line = "+----------------------+----------------------+----------------+\n";
        String header = String.format("| %-20s | %-20s | %-14s |\n", "Name", "DateOfManufacturing", "ID");
        String data = String.format("| %-20s | %-20s | %-14d |\n", this.name, this.dateOfManufacturing, this.id);
        return line + header + line + data + line;
    }

}


