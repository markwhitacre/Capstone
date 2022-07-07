package Components;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "product_table")
public class Product implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int productID;
    private String productName;
    private String updated;

public Product (int productID,String productName, String updated){
    this.productID = productID;
    this.productName = productName;
    this.updated = updated;
}

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
