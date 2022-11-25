package com.example.bookadviser.Daos;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bookadviser.Entities.Publisher;


@Dao
public interface PublisherDao {

    @Query("SELECT * FROM Publisher")
    List<Publisher> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Publisher task);

    @Delete
    void delete(Publisher task);

    @Update
    void update(Publisher task);
}
