package com.example.secvkalbums

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    private var btGetAlbums: Button? = null
    private var etEnterId: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btGetAlbums = findViewById(R.id.btGetAlbums)
        etEnterId = findViewById(R.id.etEnterId)

        btGetAlbums!!.setOnClickListener {

            val id :String = etEnterId!!.text.toString()
            val queue = Volley.newRequestQueue(this)

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET,
                getRequestUserInfo(id),
                null,
                { response ->
                    val jsonPersonData = response.getJSONArray("response").getJSONObject(0)
                    if (jsonPersonData.getBoolean("can_access_closed")){
                        intent = Intent("com.example.secvkalbums.albums")

                        if (intent.resolveActivity(applicationContext.packageManager) == null) {
                            Toast.makeText(applicationContext, "No suitable intent", Toast.LENGTH_LONG).show()
                        } else {
                            intent.putExtra("id", jsonPersonData.getString("id"))
                            intent.putExtra("first_name", jsonPersonData.getString("first_name"))
                            intent.putExtra("last_name", jsonPersonData.getString("last_name"))
                            startActivity(intent)
                        }
                    }
                    else{
                        openNoAccessDialog()
                    }
                },
                {
                    openNoInternetAccessDialog()
                })

            queue.add(jsonObjectRequest)


        }


    }


    private fun openNoAccessDialog() {
        val quitDialog = AlertDialog.Builder(
            this@MainActivity
        )
        quitDialog.setTitle(R.string.no_access)
        quitDialog.setPositiveButton(R.string.no_access_agree,
            DialogInterface.OnClickListener { dialog, which ->  })
        quitDialog.show()
    }

    private fun openNoInternetAccessDialog() {
        val quitDialog = AlertDialog.Builder(
            this@MainActivity
        )
        quitDialog.setTitle(R.string.no_internet)
        quitDialog.setPositiveButton(R.string.no_internet_agree,
            DialogInterface.OnClickListener { dialog, which -> })
        quitDialog.show()
    }
}