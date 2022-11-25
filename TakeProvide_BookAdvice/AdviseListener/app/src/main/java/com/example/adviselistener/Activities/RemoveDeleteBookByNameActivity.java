package com.example.adviselistener.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
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

        cr.delete(BookAdviseProviderContract.BOOK_ALL_ROWS_URI,
                null,
                new String[]{bookTitle.getText().toString().trim(),
                        bookCreationYear.getText().toString().trim()});

        this.finish();
    }
}