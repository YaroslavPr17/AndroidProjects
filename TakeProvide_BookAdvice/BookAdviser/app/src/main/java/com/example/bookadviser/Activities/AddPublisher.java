package com.example.bookadviser.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.bookadviser.AppDatabase;
import com.example.bookadviser.DatabaseClient;
import com.example.bookadviser.Entities.Publisher;
import com.example.bookadviser.R;
import com.example.bookadviser.SafeFunctions;

import java.util.Locale;

public class AddPublisher extends AppCompatActivity {

    Button save;
    EditText name_field, foundationYear_field, directorName_field;
    CheckBox isOnlineAvailable_field;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_publisher);

        save = findViewById(R.id.savePublisherButton);
        save.setOnClickListener(this::onSaveClick);

        name_field = findViewById(R.id.editTextPublisherName);
        foundationYear_field = findViewById(R.id.editTextPublisherFoundationYear);
        directorName_field = findViewById(R.id.editTextPublisherDirector);
        isOnlineAvailable_field = findViewById(R.id.checkBoxIsOnlineAvaillable);


        db = SafeFunctions.createDatabase(this.getApplicationContext(), false);

    }

    private void onSaveClick(View view) {

        String name, foundationYear, directorName;

        name = name_field.getText().toString().trim();
        foundationYear = foundationYear_field.getText().toString().trim();
        directorName = directorName_field.getText().toString().trim();

        if (name.isEmpty()) {
            name_field.setError("Publisher name required!");
            name_field.requestFocus();
            return;
        }

        if (foundationYear.isEmpty()) {
            foundationYear_field.setError("Publisher foundation year required");
            foundationYear_field.requestFocus();
            return;
        }

        if (directorName.isEmpty()) {
            directorName_field.setError("Publisher director name required");
            directorName_field.requestFocus();
            return;
        }

        Publisher p = new Publisher();
        p.setName(name);
        p.setYear(foundationYear);
        p.setDirectorName(directorName);
        p.setOnlineShoppingAvailable(isOnlineAvailable_field.isChecked());

        SafeFunctions.insertPublisher(getApplicationContext(), p, db, true);

        this.finish();
    }
}