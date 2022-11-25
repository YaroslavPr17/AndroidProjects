package com.example.bookadviser.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookadviser.AppDatabase;
import com.example.bookadviser.DbAdapters.AuthorAdapter;
import com.example.bookadviser.DbAdapters.BookAdapter;
import com.example.bookadviser.DatabaseClient;
import com.example.bookadviser.Entities.Author;
import com.example.bookadviser.Entities.Book;
import com.example.bookadviser.R;
import com.example.bookadviser.SafeFunctions;

import java.util.List;

public class AllBooks extends AppCompatActivity {

    RecyclerView booksRecycler;
    Button filterBooks, addBook;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        db = SafeFunctions.createDatabase(this.getApplicationContext(), false);

        List<Book> books = SafeFunctions.getAllBooks(getApplicationContext(), db);

        booksRecycler = findViewById(R.id.bookRecyclerView);
        booksRecycler.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        booksRecycler.setAdapter(new BookAdapter(this.getApplicationContext(), books));

        filterBooks = findViewById(R.id.filterBook);
        addBook = findViewById(R.id.addBook);

        filterBooks.setOnClickListener(this::onFilterClick);
        addBook.setOnClickListener(this::onAddBookClick);

    }

    private void onAddBookClick(View view) {
        Intent intent;
        intent = new Intent("ru.book-adviser.intent.action.add_book");
        if (intent.resolveActivity(getApplicationContext().getPackageManager()) == null) {
            Toast.makeText(getApplicationContext(), "No suitable intent", Toast.LENGTH_LONG).show();
        } else {
            startActivity(intent);
        }
    }

    private void onFilterClick(View view) {
        Toast.makeText(getApplicationContext(), "Not implemented yet",
                Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();

        List<Book> books = SafeFunctions.getAllBooks(getApplicationContext(), db);
        booksRecycler.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        booksRecycler.setAdapter(new BookAdapter(this.getApplicationContext(), books));
    }

}

