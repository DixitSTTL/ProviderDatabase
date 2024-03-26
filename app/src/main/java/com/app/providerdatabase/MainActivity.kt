package com.app.providerdatabase

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // class to add values in the database
        val values = ContentValues()

        // fetching text from user
        values.put("name", System.currentTimeMillis().toString())


        val PROVIDER_NAME = "com.app.providerdatabase.provider"

        // defining content URI
        val URL = "content://$PROVIDER_NAME/users"

        // parsing the content URI
        val CONTENT_URI = Uri.parse(URL)
        // inserting into database through content URI
        contentResolver.insert(CONTENT_URI, values)

        // displaying a toast message
        Toast.makeText(baseContext, "New Record Inserted", Toast.LENGTH_LONG).show()
    }
}