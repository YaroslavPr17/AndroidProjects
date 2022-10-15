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
    private static final int SHOW_OTHER_ACTIVITY = 1;

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

    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(getApplicationContext(), "Main onStart() called",
//                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        Toast.makeText(getApplicationContext(), "Main onRestart() called",
//                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Toast.makeText(getApplicationContext(), "Main onPause() called",
//                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Toast.makeText(getApplicationContext(), "Main onStop() called",
//                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Toast.makeText(getApplicationContext(), "Main onResume() called",
//                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Toast.makeText(getApplicationContext(), "Main onDestroy() called",
//                Toast.LENGTH_LONG).show();
    }
}