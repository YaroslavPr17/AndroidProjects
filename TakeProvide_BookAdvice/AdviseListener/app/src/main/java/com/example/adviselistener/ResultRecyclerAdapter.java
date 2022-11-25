package com.example.adviselistener;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.PipedOutputStream;
import java.io.Serializable;
import java.util.List;

public class ResultRecyclerAdapter extends RecyclerView.Adapter<ResultRecyclerAdapter.ResultViewholder> {
    private final Context context;
    private final List<Book> bookList;

    public ResultRecyclerAdapter(Context context, List<Book> bookList){
        this.context = context;
        this.bookList = bookList;
    }


    @NonNull
    @Override
    public ResultViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.result_recycler, parent, false);
        return new ResultRecyclerAdapter.ResultViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultRecyclerAdapter.ResultViewholder holder, int position) {
        System.out.println("IN BIND");
        System.out.println("POSITION = " + position);
        Book b = bookList.get(position);
        System.out.println("TITLE = " + b.getTitle());

        holder.bookTitleTextView.setText(b.getTitle());
        holder.bookLanguageTextView.setText(b.getLanguage());
        holder.bookAuthorTextView.setText(b.getAuthorName() + " " + b.getAuthorSurname());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }


    public class ResultViewholder extends RecyclerView.ViewHolder {

        TextView bookTitleTextView, bookLanguageTextView, bookAuthorTextView;

        public ResultViewholder(@NonNull View itemView) {
            super(itemView);

            bookTitleTextView = itemView.findViewById(R.id.textViewBookTitle);
            bookLanguageTextView = itemView.findViewById(R.id.textViewBookLanguage);
            bookAuthorTextView = itemView.findViewById(R.id.textViewBookAuthor);
        }
    }

}

