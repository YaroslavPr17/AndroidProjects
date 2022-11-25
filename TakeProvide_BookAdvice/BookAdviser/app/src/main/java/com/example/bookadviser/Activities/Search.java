package com.example.bookadviser.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bookadviser.AppDatabase;
import com.example.bookadviser.Constants;
import com.example.bookadviser.DatabaseClient;
import com.example.bookadviser.Entities.Author;
import com.example.bookadviser.Entities.Book;
import com.example.bookadviser.R;
import com.example.bookadviser.SafeFunctions;

import java.io.Serializable;
import java.util.List;

public class Search extends AppCompatActivity implements Constants {

    EditText bookTitle_field, bookCreationYear_field, authorName_field, authorSurname_field,
        authorBirthYear_field, publisher_field, yearOfPublishing_field, bookLanguage_field;

    RadioGroup rgBookCreationYear, rgAuthorBirthYear;

    RadioButton rbBookCreationYearLESS, rbBookCreationYearGREATER, rbBookCreationYearEQUAL,
            rbAuthorBirthYearLESS,rbAuthorBirthYearGREATER, rbAuthorBirthYearEQUAL;

    CheckBox cbOnlineAccess;

    Button searchButton;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        bookTitle_field = findViewById(R.id.editTextSearchBookTitle);
        bookCreationYear_field = findViewById(R.id.editTextSearchCreationYear);
        bookLanguage_field = findViewById(R.id.editTextSearchBookLanguage);
        authorName_field = findViewById(R.id.editTextSearchAuthorName);
        authorSurname_field = findViewById(R.id.editTextSearchAuthorSurname);
        authorBirthYear_field = findViewById(R.id.editTextSearchAuthorBirthYear);
        publisher_field = findViewById(R.id.editTextSearchPublisher);
        yearOfPublishing_field = findViewById(R.id.editTextSearchPublishingYear);

        rgBookCreationYear = findViewById(R.id.radioGroupBookCreationYear);
        rgAuthorBirthYear = findViewById(R.id.radioGroupAuthorBirthYear);

        rbBookCreationYearLESS = findViewById(R.id.radioButtonBookCreationYearLESS);
        rbBookCreationYearGREATER = findViewById(R.id.radioButtonBookCreationYearGREATER);
        rbBookCreationYearEQUAL = findViewById(R.id.radioButtonBookCreationYearEQUAL);

        rbAuthorBirthYearLESS = findViewById(R.id.radioButtonAuthorBirthYearLESS);
        rbAuthorBirthYearGREATER = findViewById(R.id.radioButtonAuthorBirthYearGREATER);
        rbAuthorBirthYearEQUAL = findViewById(R.id.radioButtonAuthorBirthYearEQUAL);

        cbOnlineAccess = findViewById(R.id.checkBoxSearchIsOnlineAvailable);

        searchButton = findViewById(R.id.finalSearchButton);
        searchButton.setOnClickListener(this::onSearchClick);

        db = SafeFunctions.createDatabase(this.getApplicationContext(), false);

        Bundle arguments = getIntent().getExtras();

        String authorName, authorSurname;

        if(arguments!=null){
            if (arguments.get("AuthorName") != null){
                authorName = (String) arguments.get("AuthorName");
                authorSurname = (String) arguments.get("AuthorSurname");

                authorName_field.setText(authorName);
                authorSurname_field.setText(authorSurname);

                List<Book> results = db.bookDao().findBookWithAuthorNameAndSurname(authorName, authorSurname);

                Intent intent = new Intent("ru.book-adviser.intent.action.show_search_results");
                intent.putExtra("Results", (Serializable) results);
                if (intent.resolveActivity(getApplicationContext().getPackageManager()) == null) {
                    Toast.makeText(getApplicationContext(), "No suitable intent", Toast.LENGTH_LONG).show();
                } else {
                    startActivity(intent);
                }
            }

        }

    }

    private void onSearchClick(View view) {
        String bookTitle, bookCreationYear, bookLanguage, authorName, authorSurname, authorBirthYear,
                publisher, yearOfPublishing;

        boolean onlineAccessBoolean;

        String relationParamAuthor = null, relationParamBook = null;

        if(bookTitle_field.getText().length() > 0){
            //bookTitle = percentize(bookTitle_field.getText().toString());
            bookTitle = bookTitle_field.getText().toString();
        }
        else
            bookTitle = Constants.anySymbols;

        if(bookLanguage_field.getText().length() > 0)
            bookLanguage = percentize(bookLanguage_field.getText().toString());
        else
            bookLanguage = Constants.anySymbols;

        if(bookCreationYear_field.getText().length() > 0){
            bookCreationYear = bookCreationYear_field.getText().toString();
            if (rgBookCreationYear.getCheckedRadioButtonId() != Constants.absentID){
                if (rbBookCreationYearLESS.isChecked())
                    relationParamBook = "<";
                else if(rbBookCreationYearEQUAL.isChecked())
                    relationParamBook = "=";
                else
                    relationParamBook = ">";
            }
            else{
                Toast.makeText(getApplicationContext(), R.string.not_entered_book_creation_year_relation,
                        Toast.LENGTH_LONG).show();
                return;
            }

        }
        else{
            bookCreationYear = Constants.emptyString;
        }

        if (authorName_field.getText().length() > 0)
            authorName = percentize(authorName_field.getText().toString());
        else
            authorName = Constants.anySymbols;

        if (authorSurname_field.getText().length() > 0)
            authorSurname = percentize(authorSurname_field.getText().toString());
        else
            authorSurname = Constants.anySymbols;

        if(authorBirthYear_field.getText().length() > 0){
            authorBirthYear = authorBirthYear_field.getText().toString();
            if (rgAuthorBirthYear.getCheckedRadioButtonId() != Constants.absentID){
                if (rbAuthorBirthYearLESS.isChecked())
                    relationParamAuthor = "<";
                else if(rbAuthorBirthYearEQUAL.isChecked())
                    relationParamAuthor = "=";
                else
                    relationParamAuthor = ">";
            }
            else{
                Toast.makeText(getApplicationContext(), R.string.not_entered_book_creation_year_relation,
                        Toast.LENGTH_LONG).show();
                return;
            }


        }
        else{
            authorBirthYear = Constants.emptyString;
        }

        if(publisher_field.getText().length() > 0)
            publisher = percentize(publisher_field.getText().toString());
        else
            publisher = Constants.anySymbols;

        if (yearOfPublishing_field.getText().length() > 0)
            yearOfPublishing = yearOfPublishing_field.getText().toString();
        else
            yearOfPublishing = Constants.emptyString;

        onlineAccessBoolean = cbOnlineAccess.isChecked();


        List<Book> results = db.bookDao().findBookWithAllParameters(
                bookTitle,
                bookCreationYear,
                bookLanguage,
                authorName,
                authorSurname,
                authorBirthYear,
                publisher,
                yearOfPublishing,
                onlineAccessBoolean,
                relationParamAuthor,
                relationParamBook
        );

        System.out.println("BOOK_TITLE = " + bookTitle);

        Intent intent = new Intent("ru.book-adviser.intent.action.show_search_results");
        intent.putExtra("Results", (Serializable) results);
        if (intent.resolveActivity(getApplicationContext().getPackageManager()) == null) {
            Toast.makeText(getApplicationContext(), "No suitable intent", Toast.LENGTH_LONG).show();
        } else {
            startActivity(intent);
        }

    }

    private String percentize(String input){
        return Constants.anySymbols + input + Constants.anySymbols;
    }
}