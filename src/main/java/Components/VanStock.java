package Components;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "van_stock_table")
public class VanStock extends Part{
    @PrimaryKey(autoGenerate = false)
    private int partNumber;
    private int associatedVehicle;
    private int quantity;
    private String updated;

    public VanStock(int partNumber, int associatedVehicle, int quantity, String lastUpdated){
        this.partNumber = partNumber;
        this.associatedVehicle = associatedVehicle;
        this.quantity = quantity;
        this.updated = lastUpdated;
    }

    public int getAssociatedVehicle() {
        return associatedVehicle;
    }

    public void setAssociatedVehicle(int associatedVehicle) {
        this.associatedVehicle = associatedVehicle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int getPartNumber() {
        return partNumber;
    }

    @Override
    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
