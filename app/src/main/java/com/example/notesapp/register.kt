package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerEmail = findViewById<EditText>(R.id.register_email)
        val registerPassword = findViewById<EditText>(R.id.register_password)
        val registerButton = findViewById<Button>(R.id.btn_register)

        registerButton.setOnClickListener {

            when {
                TextUtils.isEmpty(registerEmail.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@register,
                        "Please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(registerPassword.text.toString().trim{ it <= ' ' }) -> {
                    Toast.makeText(
                        this@register,
                        "Please enter password",
                        Toast.LENGTH_SHORT
                    ).show()

                } else -> {

                    val email: String = registerEmail.text.toString().trim{ it <= ' '}
                    val password: String = registerPassword.text.toString().trim{ it <= ' '}

                    //create a user with email and password
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                        OnCompleteListener<AuthResult>{ task ->

                            //IF REGISTRATION IS SUCCESFULLY DONE
                            if (task.isSuccessful) {

                                //firebase registered user
                                val firebaseUser: FirebaseUser = task.result!!.user!!

                                Toast.makeText(
                                    this@register,
                                    "You are registered!",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent =
                                    Intent(this@register, notes_activity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id",firebaseUser.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()

                            } else {
                                //if registering is not successful
                                Toast.makeText(
                                    this@register,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                }
            }
        }
    }
}
