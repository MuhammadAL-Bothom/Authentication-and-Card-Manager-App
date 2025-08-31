package com.example.m5

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class card_info : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_info)

        val textCardInfo = findViewById<TextView>(R.id.textCardInfo)
        val editAmount = findViewById<EditText>(R.id.editAmount)
        val btnConfirm = findViewById<Button>(R.id.btnConfirm)
        val imageBankLogo = findViewById<ImageView>(R.id.imageBankLogo)

        val bankName = intent.getStringExtra("bank")
        textCardInfo.text = "معلومات بطاقة بنك: $bankName"

        // استقبل الصورة من الـ Intent
        val imageBytes = intent.getByteArrayExtra("card_image")
        if (imageBytes != null) {
            val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            imageBankLogo.setImageBitmap(bitmap)
        }

        btnConfirm.setOnClickListener {
            val amount = editAmount.text.toString()
            if (amount.isNotEmpty()) {
                Toast.makeText(this, "تم خصم $amount من الرصيد", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "الرجاء إدخال مبلغ الخصم", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
