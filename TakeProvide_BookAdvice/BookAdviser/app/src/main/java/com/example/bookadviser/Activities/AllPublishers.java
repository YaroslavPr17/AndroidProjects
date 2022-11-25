package com.example.bookadviser.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookadviser.AppDatabase;
import com.example.bookadviser.DatabaseClient;
import com.example.bookadviser.DbAdapters.AuthorAdapter;
import com.example.bookadviser.Entities.Author;
import com.example.bookadviser.Entities.Publisher;
import com.example.bookadviser.DbAdapters.PublisherAdapter;
import com.example.bookadviser.R;
import com.example.bookadviser.SafeFunctions;

import java.util.List;

public class AllPublishers extends AppCompatActivity {

    RecyclerView publishersRecycler;
    Button filterPublishers, addPublisher;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_publishers);

        db = SafeFunctions.createDatabase(this.getApplicationContext(), false);

        List<Publisher> publishers = SafeFunctions.getAllPublishers(getApplicationContext(), db);

        publishersRecycler = findViewById(R.id.publisherRecyclerView);
        publishersRecycler.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        publishersRecycler.setAdapter(new PublisherAdapter(this.getApplicationContext(), publishers));

        filterPublishers = findViewById(R.id.filterPublishers);
        addPublisher = findViewById(R.id.addPublishers);

        filterPublishers.setOnClickListener(this::onFilterClick);
        addPublisher.setOnClickListener(this::onAddBookClick);

    }

    private void onAddBookClick(View view) {
        Intent intent;
        intent = new Intent("ru.book-adviser.intent.action.add_publisher");
        if (intent.resolveActivity(getApplicationContext().getPackageManager()) == null) {
            Toast.makeText(getApplicationContext(), "No suitable intent", Toast.LENGTH_LONG).show();
        } else {
            startActivity(intent);
        }
    }

    private void onFilterClick(View view) {
        Toast.makeText(getApplicationContext(), "Not implemented yet",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        List<Publisher> publishers = SafeFunctions.getAllPublishers(getApplicationContext(), db);
        publishersRecycler.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        publishersRecycler.setAdapter(new PublisherAdapter(this.getApplicationContext(), publishers));
    }

}

