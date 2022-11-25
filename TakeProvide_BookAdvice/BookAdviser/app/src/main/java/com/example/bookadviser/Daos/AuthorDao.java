package com.example.bookadviser.Daos;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.Update;

import com.example.bookadviser.Entities.Author;


@Dao
public interface AuthorDao {

    @Query("SELECT * FROM Author")
    List<Author> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Author author);

    @Delete
    void delete(Author author);

    @Update
    void update(Author author);

}
