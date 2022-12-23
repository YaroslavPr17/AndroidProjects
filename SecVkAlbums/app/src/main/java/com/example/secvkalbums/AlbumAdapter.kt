package com.example.secvkalbums

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.json.JSONArray
import org.json.JSONObject

class AlbumAdapter(private val context: Context, albumsList: JSONArray, owner_id :String) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewholder>() {
    private val albumsList: JSONArray
    private val ownerId: String

    init {
        this.albumsList = albumsList
        this.ownerId = owner_id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewholder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.album_recycler_layout, parent, false)
        return AlbumViewholder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewholder, position: Int) {
        holder.ivAlbumPreview.load(albumsList.getJSONObject(position).getString("thumb_src"))
        holder.tvAlbumDescription.text = albumsList.getJSONObject(position).getString("title") + "\n" +
                "(" + albumsList.getJSONObject(position).getString("size") + ")"
        holder.data = albumsList.getJSONObject(position)

    }

    override fun getItemCount(): Int {
        return albumsList.length()
    }

    inner class AlbumViewholder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val ivAlbumPreview :ImageView = itemView.findViewById(R.id.ivAlbumPreview)
        val tvAlbumDescription :TextView = itemView.findViewById(R.id.tvAlbumDescription)
        var data :JSONObject? = null

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val intent = Intent("com.example.secvkalbums.photos")

            if (intent.resolveActivity(context.packageManager) == null) {
                Toast.makeText(context, "No suitable intent", Toast.LENGTH_LONG).show()
            } else {
                intent.putExtra("owner_id", ownerId)
                intent.putExtra("album_id", data?.getString("id"))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

                context.startActivity(intent)
            }

        }
    }
}