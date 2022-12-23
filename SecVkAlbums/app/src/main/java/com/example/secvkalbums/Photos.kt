package com.example.secvkalbums

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class Photos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        val arguments = intent.extras
        val ownerId = arguments!!.get("owner_id") as String
        val albumId = arguments.get("album_id") as String

        println(albumId)

        val queue = Volley.newRequestQueue(this)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            getRequestAlbumPhotos(ownerId, albumId),
            null,
            { response ->
                println(response.toString())

                val photos :JSONArray = response.getJSONObject("response").getJSONArray("items")
                val count :Int = response.getJSONObject("response").getInt("count")
                println(count)
                println(photos)

                val mRecyclerView : RecyclerView = findViewById(R.id.rvPhotos)
                mRecyclerView.layoutManager = GridLayoutManager(this, 3)
                mRecyclerView.adapter = PhotoAdapter(this.applicationContext, photos, count)
            },
            {

            })

        queue.add(jsonObjectRequest)

    }
}