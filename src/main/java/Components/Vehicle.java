package Components;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "vehicle_table")
public class Vehicle implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int unitNumber;
    private String VIN;
    private int year;
    private String make;
    private String model;
    private String bodyStyle;
    private String lastUpdated;

    public Vehicle (int unitNumber, String VIN, int year, String make, String model, String bodyStyle, String lastUpdated){
        this.unitNumber = unitNumber;
        this.VIN = VIN;
        this.year = year;
        this.make = make;
        this.model = model;
        this.bodyStyle=bodyStyle;
        this.lastUpdated = lastUpdated;
    }

    public int getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(int unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
