package com.example.listview2

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity2 : AppCompatActivity() {
    companion object {
        private const val REQUEST_CALL_PERMISSION = 1
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val name = intent.getStringExtra("name")
        val tech = intent.getStringExtra("tech")
        val course = intent.getStringExtra("course")
        val branch = intent.getStringExtra("branch")
        val birth = intent.getStringExtra("birth")
        val img = intent.getIntExtra("image", R.drawable.profile)
        val num = intent.getStringExtra("number")

        val me = findViewById<TextView>(R.id.textView)
        val me2 = findViewById<TextView>(R.id.textView2)
        val me3 = findViewById<TextView>(R.id.textView3)
        val me4 = findViewById<TextView>(R.id.textView4)
        val me5 = findViewById<TextView>(R.id.textView5)
        val me6 = findViewById<TextView>(R.id.textView6)
        val imgg = findViewById<ImageView>(R.id.img)
        val call = findViewById<Button>(R.id.callButton)
        val chatme = findViewById<Button>(R.id.messbutton)

        me.text = name
        me2.text = tech
        me3.text = course
        me4.text = branch
        me5.text = birth
        me6.text = num
        imgg.setImageResource(img)


        call.setOnClickListener {
            if (num != null && num.isNotEmpty()) {

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.CALL_PHONE),
                        REQUEST_CALL_PERMISSION
                    )
                } else {

                    makeCall(num)
                }
            }
        }


        chatme.setOnClickListener {
            if (num != null && num.isNotEmpty()) {
                makemess(num)
            }
        }
    }


    private fun makeCall(phonenumber: String) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$phonenumber")
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent)
        } else {
            Toast.makeText(this, "Call permission not granted", Toast.LENGTH_SHORT).show()
        }
    }


    private fun makemess(phonenumber: String) {
        val messIntent = Intent(Intent.ACTION_SENDTO)
        messIntent.data = Uri.parse("smsto:$phonenumber")
        startActivity(messIntent)
        finish()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                val num = findViewById<TextView>(R.id.textView6).text.toString()
                makeCall(num)
            } else {

                Toast.makeText(this, "Call permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
