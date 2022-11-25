package com.example.adviselistener.Activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adviselistener.R;

public class MainActivity extends AppCompatActivity {

    Button btnAddBook, btnShowQuery, btnDeleteBook;


    ContentResolver cr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAddBook = findViewById(R.id.buttonAddBook);
        btnDeleteBook = findViewById(R.id.buttonRemoveBook);
        btnShowQuery = findViewById(R.id.buttonShowQuery);

        btnAddBook.setOnClickListener(this::onAddBookClick);
        btnDeleteBook.setOnClickListener(this::onDeleteBookClick);
        btnShowQuery.setOnClickListener(this::onShowQueryClick);

    }

    private void onAddBookClick(View view) {
        Intent intent;
        intent = new Intent("ru.book-adviser.intent.action.add-book-activity");

        if (intent.resolveActivity(getApplicationContext().getPackageManager()) == null) {
            Toast.makeText(getApplicationContext(), "No suitable intent", Toast.LENGTH_LONG).show();
        } else {
            startActivity(intent);
        }
    }

    private void onDeleteBookClick(View view) {
        Intent intent;
        intent = new Intent("ru.book-adviser.intent.action.delete-book-activity");

        if (intent.resolveActivity(getApplicationContext().getPackageManager()) == null) {
            Toast.makeText(getApplicationContext(), "No suitable intent", Toast.LENGTH_LONG).show();
        } else {
            startActivity(intent);
        }
    }

    private void onShowQueryClick(View view) {
        Intent intent;
        intent = new Intent("ru.book-adviser.intent.action.show-query-activity");

        if (intent.resolveActivity(getApplicationContext().getPackageManager()) == null) {
            Toast.makeText(getApplicationContext(), "No suitable intent", Toast.LENGTH_LONG).show();
        } else {
            startActivity(intent);
        }
    }


}