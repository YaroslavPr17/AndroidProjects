package com.example.bookadviser.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookadviser.AppDatabase;
import com.example.bookadviser.DatabaseClient;
import com.example.bookadviser.Entities.Book;
import com.example.bookadviser.R;
import com.example.bookadviser.SafeFunctions;

import java.util.Locale;

public class AddBook extends AppCompatActivity {

    Button save;
    EditText name_field, publicationYear_field, publisher_field, creationYear_field,
            authorName_field, authorSurname_field, language_field;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        save = findViewById(R.id.saveBookButton);
        save.setOnClickListener(this::onSaveClick);

        name_field = findViewById(R.id.editTextBookName);
        publicationYear_field = findViewById(R.id.editTextBookPublicationYear);
        publisher_field = findViewById(R.id.editTextBookPublisher);
        creationYear_field = findViewById(R.id.editTextBookCreationYear);
        authorName_field = findViewById(R.id.editTextBookAuthorName);
        authorSurname_field = findViewById(R.id.editTextBookAuthorSurname);
        language_field = findViewById(R.id.editTextBookLanguage);


        db = SafeFunctions.createDatabase(this.getApplicationContext(), false);

    }

    private void onSaveClick(View view) {
        Book b = new Book();
        b.setTitle(name_field.getText().toString().trim());
        b.setPublicationYear(publicationYear_field.getText().toString().trim());
        b.setPublisher(publisher_field.getText().toString().trim());
        b.setCreationYear(creationYear_field.getText().toString().trim());
        b.setAuthorName(authorName_field.getText().toString().trim());
        b.setAuthorSurname(authorSurname_field.getText().toString().trim());
        b.setLanguage(language_field.getText().toString().trim());

        SafeFunctions.insertBook(getApplicationContext(), b, db, true);

        this.finish();
    }
}