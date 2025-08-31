package com.example.m5

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.m5.databinding.ActivityResetPasswordBinding

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var resetToken: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        resetToken = intent.getStringExtra("RESET_TOKEN") ?: run {
            Toast.makeText(this, "Invalid reset link", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        binding.btnSubmit.setOnClickListener {
            val newPassword = binding.etNewPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if (newPassword.length < 6) {
                binding.etNewPassword.error = "Password must be 6+ characters"
                return@setOnClickListener
            }
            if (newPassword != confirmPassword) {
                binding.etConfirmPassword.error = "Passwords don't match"
                return@setOnClickListener
            }

            // Pass 'this' (activity context) as first parameter
            if (AuthHelper.resetPassword(this, resetToken, newPassword)) {
                Toast.makeText(this, "Password reset successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finishAffinity()
            } else {
                Toast.makeText(this, "Invalid or expired token", Toast.LENGTH_SHORT).show()
            }
        }
    }
}