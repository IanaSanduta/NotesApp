package com.example.notesapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.MenuPopupWindow
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.max
import android.view.MenuInflater as ViewMenuInflater

class notes_activity : AppCompatActivity() {

    lateinit var homeFragment: HomeFragment
    lateinit var todoFragment: TodoFragment
    lateinit var folderFragment: FolderFragment



    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)


        val userId = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")





        val logoutBtn = findViewById<Button>(R.id.button_logout)
        logoutBtn.setOnClickListener{
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@notes_activity, login::class.java))
            finish()

        }
/*
        val dropDown = findViewById<MenuPopupWindow.MenuDropDownListView>(R.id.dopDpwnMenu)
        dropDown.background = null

        val MenuInflater = menuInflater
        menuInflater.inflate(
            R.id.dopDpwnMenu,
            Menu menu
        )


 */


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavView)

        bottomNavigationView.background = null
       // bottomNavigationView.menu.getItem(0).setEnabled(false)

        val addNoteBtn = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        addNoteBtn.setOnClickListener{
            val intent = Intent(this, CreateNoteActivity::class.java)
            startActivity(intent)
        }

      //  makeCurrentFragment(homeFragment)


        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
/*
                R.id.home -> makeCurrentFragment((homeFragment))
                R.id.check -> makeCurrentFragment((todoFragment))
                R.id.folder -> makeCurrentFragment((folderFragment))
*/
                R.id.home ->{

                    homeFragment = HomeFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fl_wraper,homeFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.check ->{

                    todoFragment = TodoFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fl_wraper,todoFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.folder ->{

                    folderFragment = FolderFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fl_wraper,folderFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

            }
            true
        }

    }

    private fun makeCurrentFragment(fragment:Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wraper, fragment)
            commit()
        }

}












