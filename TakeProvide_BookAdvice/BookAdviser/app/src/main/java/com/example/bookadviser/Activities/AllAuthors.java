package com.example.bookadviser.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookadviser.AppDatabase;
import com.example.bookadviser.DbAdapters.AuthorAdapter;
import com.example.bookadviser.DatabaseClient;
import com.example.bookadviser.Entities.Author;
import com.example.bookadviser.R;
import com.example.bookadviser.SafeFunctions;

import java.util.List;

public class AllAuthors extends AppCompatActivity {

    RecyclerView authorsRecycler;
    Button filterAuthors, addAuthor;
    AppDatabase db;
    SwipeRefreshLayout srl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_authors);

//        Bundle arguments = getIntent().getExtras();
//        if(arguments!=null){
//            db = (AppDatabase)arguments.get("Database");
//        }

        db = SafeFunctions.createDatabase(this.getApplicationContext(), false);

        List<Author> authors = SafeFunctions.getAllAuthors(getApplicationContext(), db);

        Toast.makeText(getApplicationContext(), R.string.instruction_to_remove_author,
                Toast.LENGTH_LONG).show();

        authorsRecycler = findViewById(R.id.authorRecyclerView);
        authorsRecycler.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        authorsRecycler.setAdapter(new AuthorAdapter(this.getApplicationContext(), authors));

        filterAuthors = findViewById(R.id.filterAuthor);
        addAuthor = findViewById(R.id.addAuthor);
        srl = findViewById(R.id.refreshLayout);

        filterAuthors.setOnClickListener(this::onFilterClick);
        addAuthor.setOnClickListener(this::onAddAuthorClick);
        srl.setOnRefreshListener(this::onRefresh);


    }

    private void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Отменяем анимацию обновления
                srl.setRefreshing(false);

                List<Author> authors = SafeFunctions.getAllAuthors(getApplicationContext(), db);
                authorsRecycler.setAdapter(new AuthorAdapter(getApplicationContext(), authors));
            }
        }, 2000);


    }

    private void onAddAuthorClick(View view) {
        Intent intent;
        intent = new Intent("ru.book-adviser.intent.action.add_author");
        if (intent.resolveActivity(getApplicationContext().getPackageManager()) == null) {
            Toast.makeText(getApplicationContext(),
                    "No suitable intent", Toast.LENGTH_LONG).show();
        } else {
            startActivity(intent);
        }
    }

    private void onFilterClick(View view) {
        Toast.makeText(getApplicationContext(),
                "Not implemented yet", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        List<Author> authors = SafeFunctions.getAllAuthors(getApplicationContext(), db);
        authorsRecycler.setAdapter(new AuthorAdapter(this.getApplicationContext(), authors));

    }
}

