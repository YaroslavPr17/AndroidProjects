package com.example.bookadviser;

import android.os.Parcelable;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.bookadviser.Daos.AuthorDao;
import com.example.bookadviser.Daos.BookDao;
import com.example.bookadviser.Daos.PublisherDao;
import com.example.bookadviser.Entities.Author;
import com.example.bookadviser.Entities.Book;
import com.example.bookadviser.Entities.Publisher;

import java.io.Serializable;


@Database(entities = {Author.class, Book.class, Publisher.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase implements Serializable {
    public abstract AuthorDao authorDao();
    public abstract BookDao bookDao();
    public abstract PublisherDao publisherDao();
}
