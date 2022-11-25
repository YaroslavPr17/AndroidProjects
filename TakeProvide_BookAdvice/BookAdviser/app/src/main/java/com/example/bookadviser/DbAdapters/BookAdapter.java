package com.example.bookadviser.DbAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookadviser.Entities.Book;
import com.example.bookadviser.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewholder> {

    private final Context context;
    private final List<Book> booksList;

    public BookAdapter(Context context, List<Book> booksList){
        this.context = context;
        this.booksList = booksList;
    }

    @NonNull
    @Override
    public BookViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.book_recycler, parent, false);
        return new BookViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewholder holder, int position) {
        Book book = booksList.get(position);

        String _bookName, _authorName, _authorSurname;
        _bookName = book.getTitle();
        _authorName = book.getAuthorName();
        _authorSurname = book.getAuthorSurname();

        holder.bookMainTextview.setText(_bookName);
        holder.bookSecondTextview.setText("Author: " + _authorName + " " +
               _authorSurname + "\n" +
                "Creation year: " + book.getCreationYear() + "\n" +
                "Publishing year: " + book.getPublicationYear() + "\n" +
                "Publisher: " + book.getPublisher());
    }

    @Override
    public int getItemCount() {
        return this.booksList.size();
    }

    public class BookViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView bookMainTextview, bookSecondTextview;

        public BookViewholder(@NonNull View itemView) {
            super(itemView);
            bookMainTextview = itemView.findViewById(R.id.recyclerBooks_main_textView);
            bookSecondTextview = itemView.findViewById(R.id.recyclerBooks_second_textView);

        }

        @Override
        public void onClick (View view) {
            Book book = booksList.get(getAdapterPosition());

//            Intent intent = new Intent(context, Search.class);
//            intent.putExtra("task", task);
//
//            mCtx.startActivity(intent);
        }
    }
}