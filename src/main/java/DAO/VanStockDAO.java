package DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Components.VanStock;

@Dao
public interface VanStockDAO {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(VanStock vanStock);

    @Update
    void update(VanStock vanStock);

    @Delete
    void delete(VanStock vanStock);

    @Query("Delete From van_stock_table")
    void deleteAllVanStock();

    @Query("SELECT * FROM van_stock_table ORDER BY partNumber asc")
    List<VanStock> getAllVanStock();
}
