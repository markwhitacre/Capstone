package DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Components.Vehicle;


@Dao
public interface VehicleDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Vehicle vehicle);

    @Update
    void update(Vehicle vehicle);

    @Delete
    void delete(Vehicle vehicle);

    @Query("Delete From vehicle_table")
    void deleteAllVehicles();

    @Query("SELECT * FROM vehicle_table ORDER BY unitNumber ASC")
    List<Vehicle> getAllVehicles();


}

