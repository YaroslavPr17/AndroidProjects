package com.example.bookadviser.DbAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookadviser.Entities.Publisher;
import com.example.bookadviser.R;

import java.util.List;

public class PublisherAdapter extends RecyclerView.Adapter<PublisherAdapter.PublisherViewholder> {

    private final Context context;
    private final List<Publisher> publisherList;

    public PublisherAdapter(Context context, List<Publisher> publisherList){
        this.context = context;
        this.publisherList = publisherList;
    }

    @NonNull
    @Override
    public PublisherViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.publisher_recycler, parent, false);
        return new PublisherViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublisherViewholder holder, int position) {

        Publisher publisher = publisherList.get(position);

        String _publisherName, _publisherDirector;
        _publisherName = publisher.getName();
        _publisherDirector = publisher.getDirectorName();

        holder.pubMainTextview.setText(publisher.getName());
        holder.pubSecondTextview.setText("Found: " + publisher.getYear() + "\n" +
                "Director: " +  publisher.getDirectorName());
        if (publisher.isOnlineShoppingAvailable()){
            holder.pubThirdTextview.setText(R.string.available_online);
            holder.pubThirdTextview.setTextColor(context.getColor(R.color.green));
        }
        else{
            holder.pubThirdTextview.setText("");
        }

    }

    public class PublisherViewholder extends RecyclerView.ViewHolder {

        public TextView pubMainTextview, pubSecondTextview, pubThirdTextview;

        public PublisherViewholder(@NonNull View itemView) {
            super(itemView);
            pubMainTextview = itemView.findViewById(R.id.recyclerPublisher_main_textView);
            pubSecondTextview = itemView.findViewById(R.id.recyclerPublisher_second_textView);
            pubThirdTextview = itemView.findViewById(R.id.recyclerPublisher_third_textView);

        }
    }


    @Override
    public int getItemCount() {
        return this.publisherList.size();
    }
}