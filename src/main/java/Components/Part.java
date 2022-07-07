package Components;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "part_table")
public class Part implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int partNumber;
    private String serialNumber;
    private String description;
    private Double cost;
    private int associatedProduct;
    private String lastUpdated;




  public Part(int partNumber,String lastUpdated){
      this.partNumber = partNumber;
      this.lastUpdated = lastUpdated;
  }

  @Ignore public Part(){

  }

    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public int getAssociatedProduct() {
        return associatedProduct;
    }

    public void setAssociatedProduct(int associatedProduct) {
        this.associatedProduct = associatedProduct;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
