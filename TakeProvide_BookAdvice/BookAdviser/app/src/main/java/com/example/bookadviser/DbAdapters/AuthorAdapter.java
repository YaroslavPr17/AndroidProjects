package com.example.bookadviser.DbAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.bookadviser.Activities.AllAuthors;
import com.example.bookadviser.Activities.Search;
//import com.example.bookadviser.AuthorViewholder;
import com.example.bookadviser.AppDatabase;
import com.example.bookadviser.DatabaseClient;
import com.example.bookadviser.Entities.Author;
import com.example.bookadviser.R;
import com.example.bookadviser.SafeFunctions;

import java.io.Serializable;
import java.util.List;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorViewholder> {

    private final Context context;
    private final List<Author> authorsList;

    public AuthorAdapter(Context context, List<Author> authorsList){
        this.context = context;
        this.authorsList = authorsList;

    }

    @NonNull
    @Override
    public AuthorViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.author_recycler, parent, false);
        return new AuthorViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewholder holder, int position) {

        Author author = authorsList.get(position);

        String _authorName, _authorSurname, _authorCountry, _authorLanguage;
        _authorName = author.getName();
        _authorSurname = author.getSurname();
        _authorCountry = author.getNativeCountry();
        _authorLanguage = author.getLanguage();

        holder.authMainTextview.setText(_authorName + " " + _authorSurname + " (" + author.getYear() + ")");
        holder.authSecondTextview.setText("Country: " + _authorCountry + "\n" +
                "Original books language: " + _authorLanguage);

    }

    @Override
    public int getItemCount() {
        return this.authorsList.size();
    }

    public class AuthorViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView authMainTextview, authSecondTextview;
        Button editAuthor, removeAuthor;
        AppDatabase db;

        public AuthorViewholder(@NonNull View itemView) {
            super(itemView);
            authMainTextview = itemView.findViewById(R.id.recyclerAuthors_main_textView);
            authSecondTextview = itemView.findViewById(R.id.recyclerAuthors_second_textView);

            editAuthor = itemView.findViewById(R.id.editAuthorButton);

            editAuthor.setOnClickListener(this::onEditAuthorClick);
            editAuthor.setOnLongClickListener(this::onEditAuthorLongClick);

            db = DatabaseClient.getInstance(context).getAppDatabase();

            itemView.setOnClickListener(this);
        }

        private boolean onEditAuthorLongClick(View view) {
            Author author = authorsList.get(getAdapterPosition());
            SafeFunctions.deleteAuthor(context, author, db, true);

            return true;
        }


        private void onEditAuthorClick(View view) {
            Intent intent;
            Author author = authorsList.get(getAdapterPosition());
            intent = new Intent("ru.book-adviser.intent.action.add_author");
            intent.putExtra("ExistingAuthor", (Serializable) author);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            if (intent.resolveActivity(context.getPackageManager()) == null) {
                Toast.makeText(context, "No suitable intent", Toast.LENGTH_LONG).show();
            } else {
                context.startActivity(intent);
            }
        }

        public void onClick (View view) {
            Author author = authorsList.get(getAdapterPosition());

            Intent intent = new Intent(context, Search.class);
            intent.putExtra("AuthorName", author.getName());
            intent.putExtra("AuthorSurname", author.getSurname());

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);
        }
    }

}