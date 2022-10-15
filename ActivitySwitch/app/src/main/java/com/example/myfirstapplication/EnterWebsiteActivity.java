package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class EnterWebsiteActivity extends AppCompatActivity {

    Button backButton;
    EditText etWebsite;
    RadioButton rbWork, rbUseCuriosity, rbSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_website);
        etWebsite = findViewById(R.id.editTextSite);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this::onClick);

        rbWork = findViewById(R.id.workingRadioButton);
        rbWork.setOnClickListener(radioButtonClickListener);

        rbUseCuriosity = findViewById(R.id.curiousRadioButton);
        rbUseCuriosity.setOnClickListener(radioButtonClickListener);

        rbSearch = findViewById(R.id.searchRadioButton);
        rbSearch.setOnClickListener(radioButtonClickListener);
    }


    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton)v;
            switch (rb.getId()) {
                case R.id.workingRadioButton: {
                    etWebsite.setText(R.string.lmsWebsite);
                    break;
                }

                case R.id.searchRadioButton: {
                    etWebsite.setText(R.string.searchWebsite);
                    break;
                }

                case R.id.curiousRadioButton: {
                    etWebsite.setText(R.string.useCuriosityWebsite);
                    break;
                }

                default:
                    break;
            }
        }
    };


    private void onClick(View v) {
        String siteValue = etWebsite.getText().toString();
        Intent backIntent = new Intent();
        backIntent.putExtra("siteValue", siteValue);
        setResult(RESULT_OK, backIntent);
        this.finish();
    }
}