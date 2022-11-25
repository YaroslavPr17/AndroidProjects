package com.example.adviselistener.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adviselistener.BookAdviseProviderContract;
import com.example.adviselistener.R;

public class AddBookActivity extends AppCompatActivity {

    Button save;
    EditText name_field, publicationYear_field, publisher_field, creationYear_field,
            authorName_field, authorSurname_field, language_field;


    ContentResolver cr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        cr = getContentResolver();

        save = findViewById(R.id.saveBookButton);
        save.setOnClickListener(this::onSaveClick);

        name_field = findViewById(R.id.editTextBookName);
        publicationYear_field = findViewById(R.id.editTextBookPublicationYear);
        publisher_field = findViewById(R.id.editTextBookPublisher);
        creationYear_field = findViewById(R.id.editTextBookCreationYear);
        authorName_field = findViewById(R.id.editTextBookAuthorName);
        authorSurname_field = findViewById(R.id.editTextBookAuthorSurname);
        language_field = findViewById(R.id.editTextBookLanguage);


    }

    private void onSaveClick(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", name_field.getText().toString().trim());
        contentValues.put("publicationYear", publicationYear_field.getText().toString().trim());
        contentValues.put("language", language_field.getText().toString().trim());
        contentValues.put("authorName", authorName_field.getText().toString().trim());
        contentValues.put("authorSurname", authorSurname_field.getText().toString().trim());
        contentValues.put("publisher", publisher_field.getText().toString().trim());
        contentValues.put("creationYear", creationYear_field.getText().toString().trim());

        cr.insert(BookAdviseProviderContract.BOOK_ALL_ROWS_URI, contentValues);
        this.finish();
    }

    public static class MainActivity extends AppCompatActivity {

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
}