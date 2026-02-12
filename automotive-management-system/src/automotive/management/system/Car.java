package automotive.management.system;

public class Car extends Vehicle implements Comparable<Car> {

    private double price;
    private String brand;
    private String color;
    private static int totalCarsMade = 0;
    private final int id = super.getVehicleID();



    public Car(String name, String brand, String color, double price) {
        super(name);
        ++totalCarsMade;
        this.color = color;
        this.brand = brand;
        this.price = price;
    }
    
    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public int getCarID() {
        return super.getVehicleID();
    }

    public int getTotalCarsMade() {
        return totalCarsMade;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Car)) {
            return false;
        }
        Car otherCar = (Car) obj;
        if (this.getCarID() == otherCar.getCarID() || obj == this) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Car o) {
        if (o == null) {
            return 0;
        }
        if (this == null) {
            return 0;
        }
        if (this.price == o.price) {
            return 0;
        } else if (this.price > o.price) {
            return 1;
        } else {
            return -1;
        }
    }
    
    
    @Override
    public String toString() {
        String line = "+----------------------+----------------------+----------------+----------------+----------------+----------------+\n";
        String header = String.format("| %-20s | %-20s | %-14s | %-14s | %-14s | %-14s |\n",
                "Name", "Brand", "Color", "Price", "ID", "TotalCars");
        String data = String.format("| %-20s | %-20s | %-14s | %-14.2f | %-14d | %-14d |\n",
                getName(), brand, color, price, this.getCarID(), totalCarsMade);

        return line + header + line + data + line;
    }

}


