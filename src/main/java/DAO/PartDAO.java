package DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Components.Part;

@Dao
public interface PartDAO {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void insert(Part part);

    @Update
    void update(Part part);

    @Delete
    void delete(Part part);

    @Query("Delete From part_table")
    void deleteAllParts();

    @Query("SELECT * FROM part_table ORDER BY partNumber asc")
    List<Part> getAllParts();
}
