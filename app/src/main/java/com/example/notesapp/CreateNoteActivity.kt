package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class CreateNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        val backBtn = findViewById<ImageView>(R.id.image_back)
        backBtn.setOnClickListener {
            val intent = Intent(this, notes_activity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }//end of backBtn functionality

            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())

            val tvDateTime = findViewById<TextView>(R.id.textDateTime)

            tvDateTime.text = currentDate

            val saveNote = findViewById<ImageView>(R.id.image_save)

            saveNote.setOnClickListener{
                saveNote()
            }

    }

    private fun saveNote(){
        val textNoteTitle = findViewById<EditText>(R.id.NoteTitle)
        val textNoteSubTitle = findViewById<EditText>(R.id.noteSubtitle)
        val textNoteText = findViewById<EditText>(R.id.inputNote)

        if(textNoteTitle.text.isNullOrEmpty()){
            //Check this line!
            Toast.makeText(this,"Title is required", Toast.LENGTH_SHORT).show()
        }

        if(textNoteSubTitle.text.isNullOrEmpty()){
            Toast.makeText(this,"Subtitle is required", Toast.LENGTH_SHORT).show()
        }

        if(textNoteText.text.isNullOrEmpty()){
            Toast.makeText(this,"Note description is required", Toast.LENGTH_SHORT).show()
        }


    }
}