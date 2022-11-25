package com.example.bookadviser.Daos;
import android.database.Cursor;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.bookadviser.Entities.Book;
import com.example.bookadviser.TypeConverters.StrToInteger;


@Dao
public interface BookDao {

    @Query("SELECT * FROM Book")
    List<Book> getAll();

    @Query("SELECT * FROM Book WHERE title LIKE :title_pattern AND creationYear = :creation_year_pattern ")
    List<Book> strict_findBookWithTitleAndCreationYear (String title_pattern, String creation_year_pattern);

    @Query("SELECT * FROM Book WHERE " +
            "LOWER(Book.authorName) LIKE LOWER(:authorName) AND " +
            "LOWER(Book.authorSurname) LIKE LOWER(:authorSurname)")
    List<Book> findBookWithAuthorNameAndSurname(
            String authorName,
            String authorSurname
    );

    @Query("SELECT * FROM Book " +
            "INNER JOIN Author on " +
                " LOWER(Book.authorName) = LOWER(Author.name) AND LOWER(Book.authorSurname) = LOWER(Author.surname) " +
            "INNER JOIN Publisher on " +
                "LOWER(Book.publisher) = LOWER(Publisher.name) " +
            "WHERE " +
            // "LOWER(Book.title) LIKE LOWER(:bookTitle)  AND " +
            "Book.title LIKE :bookTitle AND " +
            "LOWER(Book.language) LIKE LOWER(:bookLanguage) AND " +
            "LOWER(Book.authorName) LIKE LOWER(:authorName) AND " +
            "LOWER(Book.authorSurname) LIKE LOWER(:authorSurname) AND " +
            "LOWER(Book.publisher) LIKE LOWER(:publisher) AND " +
            "(Publisher.isOnlineShoppingAvailable OR (NOT :onlineAccessBoolean)) AND " +
            "( " +
                ":authorBirthYear = \"\" OR " +
                ":relationParamAuthor = \"<\" AND Author.year < :authorBirthYear OR " +
                ":relationParamAuthor = \"=\" AND Author.year = :authorBirthYear OR " +
                ":relationParamAuthor = \">\" AND Author.year > :authorBirthYear" +
            ") AND " +
            "( " +
                ":bookCreationYear = \"\" OR " + // If the year is empty string then needless to check
                ":relationParamBook = \"<\" AND Book.creationYear < :bookCreationYear OR " +
                ":relationParamBook = \"=\" AND Book.creationYear = :bookCreationYear OR " +
                ":relationParamBook = \">\" AND Book.creationYear > :bookCreationYear" +
            ") AND " +
            "(:yearOfPublishing = \"\" OR " +
            "publicationYear = :yearOfPublishing)" +
            "")
    List<Book> findBookWithAllParameters(
                                           String bookTitle,
                                           @TypeConverters({StrToInteger.class}) String bookCreationYear,
                                         String bookLanguage,
                                         String authorName,
                                         String authorSurname,
                                           @TypeConverters({StrToInteger.class}) String authorBirthYear,
                                         String publisher,
                                           @TypeConverters({StrToInteger.class}) String yearOfPublishing,
                                         boolean onlineAccessBoolean,
                                         String relationParamAuthor,
                                         String relationParamBook
    );


//    @Query("SELECT :projection[0] FROM Book WHERE  ")
//    Cursor completelyCustomizedQuery(String[] projection,
//                                     String selection,
//                                     String selectionArgs );

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Book book);

    @Delete
    void delete(Book book);

    @Update
    void update(Book book);



}


