package com.example.secvkalbums

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.json.JSONArray
import org.json.JSONObject

class PhotoAdapter(private val context: Context, photosList: JSONArray, count :Int) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewholder>() {
    private val photosList: JSONArray
    private val count: Int

    init {
        this.photosList = photosList
        this.count = count
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewholder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.photo_recycler_layout, parent, false)
        return PhotoViewholder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewholder, position: Int) {
        holder.ivPhotoPreview.load(photosList.getJSONObject(position).getJSONArray("sizes").getJSONObject(0).getString("url"))
    }

    override fun getItemCount(): Int {
        return photosList.length()
    }

    inner class PhotoViewholder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val ivPhotoPreview :ImageView = itemView.findViewById(R.id.ivImagePreview)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {

        }
    }
}