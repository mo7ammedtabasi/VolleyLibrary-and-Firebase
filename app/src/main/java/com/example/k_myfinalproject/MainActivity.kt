package com.example.k_myfinalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.k_myfinalproject.databinding.ActivityMainBinding
import com.example.k_myfinalproject.fragment.CurrencyConverterFragment
import com.example.k_myfinalproject.fragment.PhotosFragment
import com.example.k_myfinalproject.fragment.PostsFragment
import com.example.k_myfinalproject.fragment.ToDoFragment
import com.example.k_myfinalproject.ui.LoginActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth= Firebase.auth

        actionBarToggle = ActionBarDrawerToggle(this, binding.drawerLayout, 0, 0)

        binding.drawerLayout.addDrawerListener(actionBarToggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        actionBarToggle.syncState()

        val postsFragment=PostsFragment()
        val toDoFragment=ToDoFragment()
        val photosFragment=PhotosFragment()
        val currencyConverterFragment=CurrencyConverterFragment()

        setCurrentFragment(postsFragment)

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.posts -> {
                    Toast.makeText(this, "posts", Toast.LENGTH_SHORT).show()
                    setCurrentFragment(postsFragment)
                    true
                }
                R.id.to_do_list -> {
                    Toast.makeText(this, "To Do List", Toast.LENGTH_SHORT).show()
                    setCurrentFragment(toDoFragment)
                    true
                }
                R.id.photos -> {
                    Toast.makeText(this, "photos", Toast.LENGTH_SHORT).show()
                    setCurrentFragment(photosFragment)
                    true
                }
                R.id.currency_converter -> {
                    Toast.makeText(this, "currency converter", Toast.LENGTH_SHORT).show()
                    setCurrentFragment(currencyConverterFragment)
                    true
                }
                R.id.log_out -> {
                    auth.signOut()
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()
                    Toast.makeText(this, "log out", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
    }

}