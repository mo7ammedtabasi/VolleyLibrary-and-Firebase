package com.example.k_myfinalproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.k_myfinalproject.MainActivity
import com.example.k_myfinalproject.R
import com.example.k_myfinalproject.databinding.ActivityMainBinding
import com.example.k_myfinalproject.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= Firebase.auth

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

        binding.butRegister.setOnClickListener {

            val email = binding.edEmail.text.toString().trim()
            val password = binding.edPassword.text.toString().trim()
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task->
                    if (task.isSuccessful){
                        Toast.makeText(this,"Signup Ok",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this,"Signup filed",Toast.LENGTH_SHORT).show()
                    }

                }
        }

    }
}