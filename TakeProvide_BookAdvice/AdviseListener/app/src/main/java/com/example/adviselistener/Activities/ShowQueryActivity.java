package com.example.adviselistener.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import com.example.adviselistener.Book;
import com.example.adviselistener.BookAdviseProviderContract;
import com.example.adviselistener.R;
import com.example.adviselistener.ResultRecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowQueryActivity extends AppCompatActivity {

    TextView number;
    ContentResolver contentResolver;
    RecyclerView recyclerView;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_query);

        recyclerView = findViewById(R.id.ResultRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        number = findViewById(R.id.textViewShowNumber);

        contentResolver = getContentResolver();

        try {
            Cursor cursor = contentResolver.query(BookAdviseProviderContract.BOOK_ALL_ROWS_URI,
                    new String[]{"COUNT(*)"},
                    "title LIKE ? AND language LIKE ?",
                    new String[]{"%a%", "%rus%"},
                    null);
            cursor.moveToFirst();
            number.setText(String.valueOf(cursor.getInt(0)));
            cursor.close();

            Cursor bookCursor = contentResolver.query(BookAdviseProviderContract.BOOK_ALL_ROWS_URI,
                    new String[]{"*"},
                    "title LIKE ? AND language LIKE ?",
                    new String[]{"%a%", "%rus%"},
                    null);
            bookCursor.moveToFirst();

            List<Book> books = new ArrayList<>();

            while (!bookCursor.isAfterLast()){
                Book b = new Book();
                b.setTitle(bookCursor.getString(bookCursor.getColumnIndex("title")));
                b.setPublisher(bookCursor.getString(bookCursor.getColumnIndex("publisher")));
                b.setLanguage(bookCursor.getString(bookCursor.getColumnIndex("language")));
                b.setCreationYear(bookCursor.getString(bookCursor.getColumnIndex("creationYear")));
                b.setPublicationYear(bookCursor.getString(bookCursor.getColumnIndex("publicationYear")));
                b.setAuthorName(bookCursor.getString(bookCursor.getColumnIndex("authorName")));
                b.setAuthorSurname(bookCursor.getString(bookCursor.getColumnIndex("authorSurname")));

                books.add(b);
                bookCursor.moveToNext();
            }

            recyclerView.setAdapter(new ResultRecyclerAdapter(getApplicationContext(), books));

            bookCursor.close();

        }
        catch (IllegalArgumentException | NullPointerException npe){
            openQuitDialogBecauseOfNoDB();
        }
    }

    private void openQuitDialogBecauseOfNoDB() {
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