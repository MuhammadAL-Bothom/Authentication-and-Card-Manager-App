package com.example.m5

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.m5.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnReset.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()

            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.etEmail.error = "Valid email required"
                return@setOnClickListener
            }

            // Pass 'this' (the activity context) as first parameter
            val token = AuthHelper.generateResetToken(this, email)
            if (token != null) {
                val intent = Intent(this, ResetPasswordActivity::class.java).apply {
                    putExtra("RESET_TOKEN", token)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Email not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun BacktoLogin(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}