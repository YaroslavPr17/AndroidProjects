package com.example.bookadviser.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bookadviser.DbAdapters.BookAdapter;
import com.example.bookadviser.Entities.Book;
import com.example.bookadviser.R;

import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    RecyclerView booksRecycler;
    Activity current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        current = this;

        Bundle arguments = getIntent().getExtras();

        List<Book> result = null;

        if(arguments!=null){
            result = (List<Book>) arguments.get("Results");
        }

        if (result == null || result.size() == 0){
            openQuitDialog();
        }
        else
        {
            booksRecycler = findViewById(R.id.bookRecyclerViewForSearchResults);
            booksRecycler.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
            booksRecycler.setAdapter(new BookAdapter(this.getApplicationContext(), result));
        }

    }


    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(this);
        quitDialog.setTitle(R.string.no_search_results);

        quitDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        quitDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
                RecyclerView rw = current.findViewById(R.id.bookRecyclerViewForSearchResults);
                rw.setBackground(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.road));
            }
        });

        quitDialog.show();
    }
}