package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button saverButton;
    EditText etName, etBirthday, etCourse;
    String name, dateBth, course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saverButton = findViewById(R.id.b2);
        saverButton.setOnClickListener(this::onClick);

    }

    public void onClick(View v) {
        etName = findViewById(R.id.editName);
        etBirthday = findViewById(R.id.editBirthday);
        etCourse = findViewById(R.id.editCourse);

        name = etName.getText().toString();
        dateBth = etBirthday.getText().toString();
        course = etCourse.getText().toString();
        Intent intent;
        if (v.getId() == R.id.b2) {
            intent = new Intent("ru.datatransfer.intent.action.showinfo");
            intent.putExtra("name", name);
            intent.putExtra("birthday", dateBth);
            intent.putExtra("course", course);
            if (intent.resolveActivity(getApplicationContext().getPackageManager()) == null) {
                Toast.makeText(getApplicationContext(), "No suitable intent", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Your data was saved.",
                        Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        }
    }
}