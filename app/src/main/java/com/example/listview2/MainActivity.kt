package com.example.listview2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val name = arrayOf(
            "Md Ayan Hashmi",
            "Md Arsalan ",
            "Aquib Ansari",
            "Karan kapil",
            "Ayush Arya",
            "Tushar Kumar",
            "Saurabh pandey"
        )
        val tech = arrayOf(
            "Android developer",
            "Web Developer",
            "beginner",
            "Speaker",
            "Web developer",
            "Beginner",
            "DSA"
        )
        val lodu = arrayOf("MEMBER1", "MEMBER2", "MEMBER3", "MEMBER4", "MEMBER5", "MEMBER6", "MEMBER7")
        val course = arrayOf("B.TECH", "B.TECH", "B.TECH", "B.TECH", "B.TECH", "B.TECH", "B.TECH")
        val branch = arrayOf("C.S", "C.S", "C.S", "C.S", "C.S", "C.S", "C.S")
        val birth = arrayOf(
            "10/02/2006",
            "01/01/2004",
            "06/01/2004",
            "22/10/2004",
            "10/05/2005",
            "19/09/2003",
            "03/02/2005"

        )
        val num = arrayOf("9386960284","8294005384","6389617762","8279598550","9559479212","7505928062","9984446318")
        val img = arrayOf(
            R.drawable.ayan,
            R.drawable.arsa, R.drawable.aqib, R.drawable.karan, R.drawable.ayush, R.drawable.tushar, R.drawable.saurabh,

        )

        println("Name array size: ${name.size}")
        println("Tech array size: ${tech.size}")
        println("Lodu array size: ${lodu.size}")
        println("Course array size: ${course.size}")
        println("Branch array size: ${branch.size}")
        println("Birth array size: ${birth.size}")
        println("Img array size: ${img.size}")
        println("num array size: ${num.size}")
        var userarray = ArrayList<data>()
        for (eachIndex in name.indices) {
            val user = data(
                name[eachIndex],
                tech[eachIndex],
                lodu[eachIndex],
                course[eachIndex],
                branch[eachIndex],
                birth[eachIndex],
                num[eachIndex],
                img[eachIndex]

            )
            userarray.add(user)

        }
        val listview = findViewById<ListView>(R.id.list)
        listview.adapter = MyAdapter(this, userarray)
        listview.setOnItemClickListener { parent, view, position, id ->
           val name= name[position]
            val tech=  tech[position]
            val course= course[position]
            val branch = branch[position]
            val birth = birth[position]
            val image= img[position]
            val num = num[position]


            val pass= Intent(this, MainActivity2::class.java)
            pass.putExtra("name",name)
            pass.putExtra("tech",tech)
            pass.putExtra("course",course)
            pass.putExtra("branch",branch)
            pass.putExtra("birth",birth)
            pass.putExtra("image",image)
            pass.putExtra("number", num)
          startActivity(pass)


        }
    }
}