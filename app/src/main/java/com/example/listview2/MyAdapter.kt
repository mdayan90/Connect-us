package com.example.listview2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.core.content.contentValuesOf


class MyAdapter(context: Activity, userarray: ArrayList<data>) :
    ArrayAdapter<data>(context,R.layout.eachrow,userarray) {

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflator= LayoutInflater.from(context)
        val view = inflator.inflate(R.layout.eachrow, null)
        val user = getItem(position)
            val name = view.findViewById<TextView>(R.id.nameview)
        val tech = view.findViewById<TextView>(R.id.techview)
        val lodu = view.findViewById<TextView>(R.id.loduview)
        val img = view.findViewById<ImageView>(R.id.img)
        name.text = user?.name
        tech.text = user?.tech
        lodu.text = user?.lodu
        user?.let {
            img.setImageResource(it.img)
        } ?: img.setImageResource(R.drawable.ic_launcher_background)


        return view

    }
    }


