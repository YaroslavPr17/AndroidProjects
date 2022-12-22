package com.example.secvkalbums

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class Albums : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        val tvNoAlbums :TextView = findViewById(R.id.tvNoAlbumsAvailable)

        val arguments = intent.extras
        val ownerId = arguments!!.get("id") as String
        val first_name = arguments.get("first_name") as String
        val last_name = arguments.get("last_name") as String

        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            getRequestUserAlbums(ownerId, 1, 1),
            null,
            { response ->
                if (response.getJSONObject("response").getInt("count") == 0){
                    tvNoAlbums.visibility = View.VISIBLE
                    tvNoAlbums.text = getString(R.string.no_albums, first_name, last_name)
                }
                else{
                    val albums :JSONArray = response.getJSONObject("response").getJSONArray("items")
                    println(albums)
                    val mRecyclerView :RecyclerView = findViewById(R.id.rvAlbums)
                    mRecyclerView.layoutManager = LinearLayoutManager(this)
                    mRecyclerView.adapter = AlbumAdapter(this.applicationContext, albums, ownerId)
                }
            },
            {
                Toast.makeText(this, "Unable to get albums", Toast.LENGTH_LONG).show()
                // openNoInternetAccessDialog()
            })

        queue.add(jsonObjectRequest)
    }
}