package com.example.m5

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import java.io.ByteArrayOutputStream
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.m5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var frontImage: Bitmap? = null
    private var backImage: Bitmap? = null
    private val REQUEST_FRONT = 1
    private val REQUEST_BACK = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val banks = arrayOf(
            "اختر البنك",
            "البنك العربي",
            "بنك الإسكان للتجارة والتمويل",
            "بنك الأردن",
            "بنك القاهرة عمان",
            "البنك الإسلامي الأردني",
            "البنك الأهلي الأردني",
            "بنك الاتحاد",
            "البنك التجاري الأردني",
            "بنك الاستثمار العربي الأردني (AJIB)",
            "البنك الأردني الكويتي",
            "بنك صفوة الإسلامي",
            "بنك الأردن دبي الإسلامي"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, banks)
        binding.bankSpinner.adapter = adapter

        binding.btnCaptureFront.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUEST_FRONT)
        }

        binding.btnCaptureBack.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUEST_BACK)
        }

        binding.btnProcessCard.setOnClickListener {
            if (frontImage != null && backImage != null) {
                val intent = Intent(this, card_info::class.java)
                intent.putExtra("bank", binding.bankSpinner.selectedItem.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this, "يرجى التقاط صورتي البطاقة", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            val image = data.extras?.get("data") as Bitmap
            if (requestCode == REQUEST_FRONT) {
                frontImage = image
                binding.imageFront.setImageBitmap(image)
            } else if (requestCode == REQUEST_BACK) {
                backImage = image
                binding.imageBack.setImageBitmap(image)
            }

        }
        binding.btnProcessCard.setOnClickListener {
            if (frontImage != null && backImage != null) {
                val stream = ByteArrayOutputStream()
                frontImage?.compress(Bitmap.CompressFormat.PNG, 100, stream)
                val byteArray = stream.toByteArray()

                val intent = Intent(this, card_info::class.java)
                intent.putExtra("bank", binding.bankSpinner.selectedItem.toString())
                intent.putExtra("card_image", byteArray)
                startActivity(intent)
            } else {
                Toast.makeText(this, "يرجى التقاط صورتي البطاقة", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
