package com.example.adviselistener.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.adviselistener.BookAdviseProviderContract;
import com.example.adviselistener.R;

public class RemoveDeleteBookByNameActivity extends AppCompatActivity {

    EditText bookTitle, bookCreationYear;
    Button btnDeleteBook;

    ContentResolver cr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_delete_book_by_name);

        bookTitle = findViewById(R.id.editTextBookTitleToDelete);
        bookCreationYear = findViewById(R.id.editTextBookCreationYearToDelete);
        btnDeleteBook = findViewById(R.id.buttonDeleteBookToDelete);

        btnDeleteBook.setOnClickListener(this::onDeleteBookClick);

        cr = getContentResolver();
    }

    private void onDeleteBookClick(View view) {
        if (bookTitle.getText().toString().isEmpty()) {
            bookTitle.setError("Book title is required!");
            bookTitle.requestFocus();

            return;
        }

        if (bookCreationYear.getText().toString().isEmpty()) {
            bookCreationYear.setError("Book creation year is required!");
            bookCreationYear.requestFocus();

            return;
        }

        try {
            cr.delete(BookAdviseProviderContract.BOOK_ALL_ROWS_URI,
                    null,
                    new String[]{bookTitle.getText().toString().trim(),
                            bookCreationYear.getText().toString().trim()});

            this.finish();
        }
        catch (IllegalArgumentException iae){
            openQuitDialog();
        }


    }

    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(this);
        quitDialog.setTitle(R.string.database_not_exist);

        quitDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        quitDialog.show();
    }

}