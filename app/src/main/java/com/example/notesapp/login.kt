package com.example.notesapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.text.TextUtils

import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class login : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)


        val signinButton = findViewById<Button>(R.id.signup)
        signinButton.setOnClickListener{
            val intent = Intent(this,register::class.java)
            startActivity(intent)
        }


        val loginEmail = findViewById<EditText>(R.id.login_email)
        val loginPassword = findViewById<EditText>(R.id.login_password)
        val signin = findViewById<Button>(R.id.signin)

        signin.setOnClickListener {

            when {
                TextUtils.isEmpty(loginEmail.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@login,
                        "Please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(loginPassword.text.toString().trim{ it <= ' ' }) -> {
                    Toast.makeText(
                        this@login,
                        "Please enter password",
                        Toast.LENGTH_SHORT
                    ).show()

                } else -> {

                val email: String = loginEmail.text.toString().trim{ it <= ' '}
                val password: String = loginPassword.text.toString().trim{ it <= ' '}

                //create a user with email and password
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener(
                    OnCompleteListener<AuthResult>{ task ->

                        //IF REGISTRATION IS SUCCESSFULLY DONE
                        if (task.isSuccessful) {

                            //firebase registered user
                           // val firebaseUser: FirebaseUser = task.result!!.user!!

                            Toast.makeText(
                                this@login,
                                "You are logged in!",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent =
                                Intent(this@login, notes_activity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id",FirebaseAuth.getInstance().currentUser!!.uid)
                            intent.putExtra("email_id", email)
                            startActivity(intent)
                            finish()

                        } else {
                            //if registering is not successful
                            Toast.makeText(
                                this@login,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            }
            }
        }








/*
// put the following code for the logout button

// put the following code for the logout button

        val sharedPreferences: SharedPreferences =
            getActivity()?.getSharedPreferences("autoLogin", Context.MODE_PRIVATE) ?:

        val editor = sharedPreferences.edit()
        editor.putInt("key", 0)
        editor.apply()


         */

    }
}