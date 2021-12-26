package com.example.notesapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signinButton = findViewById<Button>(R.id.getStarted)
        signinButton.setOnClickListener{
            val intent = Intent(
                this,
                login::class.java
            )
            startActivity(intent)
        }


        //val userId = intent.getStringExtra("user_id")
        //val emailId = intent.getStringExtra("email_id")



        var sharedpreferences = getSharedPreferences("autoLogin", Context.MODE_PRIVATE)
        val j: Int = sharedpreferences.getInt("key", 0)

        //Default is 0 so autologin is disabled

        //Default is 0 so autologin is disabled
        if (j > 0) {
            Log.i("Loogged in", "your in")
            val activity = Intent(applicationContext, notes_activity::class.java)
            startActivity(activity)
        } else {
            Log.i("Not luck", "your not in")
        }

        val TAG = this@MainActivity::class.java.simpleName
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            // User is signed in
            val i = Intent(this@MainActivity, notes_activity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        } else {
            // User is signed out
            val d = Log.d(
                TAG,
                "onAuthStateChanged:signed_out"
            )
        }
    }


}