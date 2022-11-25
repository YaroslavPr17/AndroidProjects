package com.example.adviselistener.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import com.example.adviselistener.Book;
import com.example.adviselistener.BookAdviseProviderContract;
import com.example.adviselistener.R;
import com.example.adviselistener.ResultRecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowQueryActivity extends AppCompatActivity {

    TextView number;
    ContentResolver contentResolver;
    RecyclerView recyclerView;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_query);

        recyclerView = findViewById(R.id.ResultRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        number = findViewById(R.id.textViewShowNumber);

        contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(BookAdviseProviderContract.BOOK_ALL_ROWS_URI,
                new String[]{"COUNT(*)"},
                "title LIKE ? AND language LIKE ?",
                new String[]{"%a%", "%rus%"},
                null);
        cursor.moveToFirst();
        number.setText(String.valueOf(cursor.getInt(0)));
        cursor.close();

        Cursor cursor1 = contentResolver.query(BookAdviseProviderContract.BOOK_ALL_ROWS_URI,
                new String[]{"*"},
                "title LIKE ? AND language LIKE ?",
                new String[]{"%a%", "%rus%"},
                null);
        cursor1.moveToFirst();

        List<Book> books = new ArrayList<>();

//        System.out.println("INDEX = " + cursor1.get getColumnIndex("language"));


        while (!cursor1.isAfterLast()){
            Book b = new Book();
            b.setTitle(cursor1.getString(cursor1.getColumnIndex("title")));
            b.setPublisher(cursor1.getString(cursor1.getColumnIndex("publisher")));
            b.setLanguage(cursor1.getString(cursor1.getColumnIndex("language")));
            b.setCreationYear(cursor1.getString(cursor1.getColumnIndex("creationYear")));
            b.setPublicationYear(cursor1.getString(cursor1.getColumnIndex("publicationYear")));
            b.setAuthorName(cursor1.getString(cursor1.getColumnIndex("authorName")));
            b.setAuthorSurname(cursor1.getString(cursor1.getColumnIndex("authorSurname")));

            books.add(b);
            cursor1.moveToNext();
        }

        System.out.println("BOOKS: " + books);

        recyclerView.setAdapter(new ResultRecyclerAdapter(getApplicationContext(), books) );

        cursor1.close();


    }
}