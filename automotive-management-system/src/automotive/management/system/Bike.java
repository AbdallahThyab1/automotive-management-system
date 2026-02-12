package automotive.management.system;

public class Bike extends Vehicle implements Cloneable {

    private static int totalBikesMade = 0;
    private int gears;
    private final int id = super.getVehicleID();

    public Bike() {
        super();
        ++totalBikesMade;
    }

    public Bike(String name, int gears) {
        super(name);
        ++totalBikesMade;
        this.gears = gears;
    }

    public static void setTotalBikesMade(int totalBikesMade) {
        Bike.totalBikesMade = totalBikesMade;
    }

    public int getId() {
        return super.getVehicleID();
    }

    public int getGears() {
        return gears;
    }

    public void setGears(int gears) {
        this.gears = gears;
    }

    public static int getTotalBikesMade() {
        return Bike.totalBikesMade;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Bike)) {
            return false;
        }
        Bike otherBike = (Bike) obj;
        if (this.getId() == otherBike.getId() || obj == this) {
            return true;
        } else {
            return false;
        }
    }

    public Bike getBikeWithMaxGears(Bike... bikes) {
        if (bikes == null || bikes.length == 0) {
            return null;
        }
        Bike maxGearsBike = bikes[0];
        for (Bike bike : bikes) {
            if ( bike != null && (bike.getGears() > maxGearsBike.getGears())) {
                maxGearsBike = bike;
            }
        }
        return maxGearsBike;
    }

    @Override
    public String toString() {
        String line = "+----------------------+----------------------+----------------+----------------+\n";
        String header = String.format("| %-20s | %-20s | %-14s | %-14s |\n",
                "Name", "Gears", "ID", "TotalBikes");
        String data = String.format("| %-20s | %-20d | %-14d | %-14d |\n",
                getName(), gears, this.getId(), totalBikesMade);
        return line + header + line + data + line;
    }

}
