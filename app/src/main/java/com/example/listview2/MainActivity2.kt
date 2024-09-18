package com.example.listview2

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.*
import android.net.Uri
import android.os.Bundle
import android.view.textclassifier.ConversationActions.Request
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    companion object {
        private const val REQUEST_CALL_PERMISSION = 1
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val name = intent.getStringExtra("name")
        val tech = intent.getStringExtra("tech")
        val course = intent.getStringExtra("course")
        val branch = intent.getStringExtra("branch")
        val birth = intent.getStringExtra("birth")
        val img = intent.getIntExtra("image", R.drawable.profile)
        val num = intent.getStringExtra("number")


        val me= findViewById<TextView>(R.id.textView)
        val me2= findViewById<TextView>(R.id.textView2)
        val me3= findViewById<TextView>(R.id.textView3)
        val me4= findViewById<TextView>(R.id.textView4)
        val me5= findViewById<TextView>(R.id.textView5)
        val me6= findViewById<TextView>(R.id.textView6)
        val imgg= findViewById<ImageView>(R.id.img)
        val call = findViewById<Button>(R.id.callButton)
        val whatsapp = findViewById<Button>(R.id.whatsappbtn)
        me.text= name
        me2.text= tech
        me3.text= course
        me4.text= branch
        me5.text= birth
        me6.text= num
        imgg.setImageResource(img)

        call.setOnClickListener {
            if (num != null && num.isNotEmpty()) {
                makeCall(num)
            }
        }
        whatsapp.setOnClickListener {
            if (num != null && num.isNotEmpty()) {
                if (isWhatsAppInstalled()) {
                    openWhatsAppChat(num)
                } else {
                    Toast.makeText(this, "WhatsApp is not installed on this device", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Phone number is not available", Toast.LENGTH_SHORT).show()
            }
        }

    }



    private fun makeCall(phonenumber: String){
        if  (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                REQUEST_CALL_PERMISSION
            )
        }
        else {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$phonenumber")
            startActivity(callIntent)
        }
        {


        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PERMISSION_GRANTED) {
                val num = intent.getStringExtra("number")
                if (num != null) {
                    makeCall(num)
                }
            } else {
                Toast.makeText(this,"Number not available", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun isWhatsAppInstalled(): Boolean {
        val packageManager = packageManager
        return try {
            packageManager.getPackageInfo("com.whatsapp", 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
    private fun openWhatsAppChat(phoneNumber: String) {
        try {
            // Ensure phone number contains country code

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber")

            // Create intent for WhatsApp
            val whatsappIntent = Intent(Intent.ACTION_VIEW)
            whatsappIntent.setPackage("com.whatsapp")

            // Check if WhatsApp or WhatsApp Business is installed
            val packageManager = packageManager
            if (whatsappIntent.resolveActivity(packageManager) != null) {
                startActivity(whatsappIntent)
            } else {
                // Check for WhatsApp Business
                whatsappIntent.setPackage("com.whatsapp.w4b")
                if (whatsappIntent.resolveActivity(packageManager) != null) {
                    startActivity(whatsappIntent)
                } else {
                    Toast.makeText(this, "WhatsApp or WhatsApp Business is not installed", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

}