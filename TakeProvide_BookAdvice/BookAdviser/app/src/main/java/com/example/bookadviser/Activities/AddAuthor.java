package com.example.bookadviser.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookadviser.AppDatabase;
import com.example.bookadviser.DatabaseClient;
import com.example.bookadviser.Entities.Author;
import com.example.bookadviser.R;
import com.example.bookadviser.SafeFunctions;

public class AddAuthor extends AppCompatActivity {

    Button save;
    EditText name_field, surname_field, year_field, country_field, language_field;
    AppDatabase db;

    boolean argumentsAvailable;

    Author author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_author);

        save = findViewById(R.id.saveAuthorButton);
        save.setOnClickListener(this::onSaveClick);

        name_field = findViewById(R.id.editAuthorTextName);
        surname_field = findViewById(R.id.editTextAuthorSurname);
        language_field = findViewById(R.id.editTextAuthorLanguage);
        year_field = findViewById(R.id.editTextAuthorYear);
        country_field = findViewById(R.id.editTextAuthorCountry);

        db = SafeFunctions.createDatabase(this.getApplicationContext(), false);

        Bundle arguments = getIntent().getExtras();

        String name, surname, language, year, country;

        if(arguments!=null){
            if (arguments.get("ExistingAuthor") != null){
                argumentsAvailable = true;
                author = (Author) arguments.get("ExistingAuthor");
                name = author.getName();
                surname = author.getSurname();
                language = author.getLanguage();
                year = author.getYear();
                country = author.getNativeCountry();

                name_field.setText(name);
                surname_field.setText(surname);
                language_field.setText(language);
                year_field.setText(year);
                country_field.setText(country);
            }

        }
    }


    private void onSaveClick(View view) {
        if (argumentsAvailable)
            SafeFunctions.insertAuthor(getApplicationContext(), author, db, true);

        Author a = new Author();
        a.setName(name_field.getText().toString().trim());
        a.setSurname(surname_field.getText().toString().trim());
        a.setNativeCountry(country_field.getText().toString().trim());
        a.setLanguage(language_field.getText().toString().trim());
        a.setYear(year_field.getText().toString().trim());

        SafeFunctions.insertAuthor(getApplicationContext(), a, db, true);

        this.finish();
    }
}