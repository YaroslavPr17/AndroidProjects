package com.example.bookadviser.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookadviser.AppDatabase;
import com.example.bookadviser.DatabaseClient;
import com.example.bookadviser.Entities.Author;
import com.example.bookadviser.Entities.Publisher;
import com.example.bookadviser.Initializer;
import com.example.bookadviser.R;
import com.example.bookadviser.SafeFunctions;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button allAuthors, allBooks, allPublishers, search;
    AppDatabase db;

    //@SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allAuthors = findViewById(R.id.authors);
        allBooks = findViewById(R.id.books);
        allPublishers = findViewById(R.id.publishers);
        search = findViewById(R.id.search);

        allAuthors.setOnClickListener(this::onClickAuthors);
        allBooks.setOnClickListener(this::onClickBooks);
        allPublishers.setOnClickListener(this::onClickPublishers);
        search.setOnClickListener(this::onClickSearch);

        db = SafeFunctions.createDatabase(getApplicationContext(), true);

        Initializer.initializeAuthors(getApplicationContext());
        Initializer.initializePublishers(getApplicationContext());
        Initializer.initializeBooks(getApplicationContext());
    }


    private void onClickSearch(View view) {
        Intent intent;
        intent = new Intent("ru.book-adviser.intent.action.open-search");
        if (intent.resolveActivity(getApplicationContext().getPackageManager()) == null) {
            Toast.makeText(getApplicationContext(), "No suitable intent", Toast.LENGTH_LONG).show();
        } else {
            startActivity(intent);
        }
    }

    private void onClickPublishers(View view) {
        Intent intent;
        intent = new Intent("ru.book-adviser.intent.action.open-publishers");

        if (intent.resolveActivity(getApplicationContext().getPackageManager()) == null) {
            Toast.makeText(getApplicationContext(), "No suitable intent", Toast.LENGTH_LONG).show();
        } else {
            startActivity(intent);
        }
    }

    private void onClickBooks(View view) {
        Intent intent;
        intent = new Intent("ru.book-adviser.intent.action.open-books");

        if (intent.resolveActivity(getApplicationContext().getPackageManager()) == null) {
            Toast.makeText(getApplicationContext(), "No suitable intent", Toast.LENGTH_LONG).show();
        } else {
            startActivity(intent);
        }
    }

    private void onClickAuthors(View view) {
        Intent intent;
        intent = new Intent("ru.book-adviser.intent.action.open-authors");

        if (intent.resolveActivity(getApplicationContext().getPackageManager()) == null) {
            Toast.makeText(getApplicationContext(), "No suitable intent", Toast.LENGTH_LONG).show();
        } else {
            startActivity(intent);
        }
    }

}