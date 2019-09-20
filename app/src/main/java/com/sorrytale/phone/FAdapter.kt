package com.sorrytale.phone

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FAdapter(var contactList: MutableList<Contact>) :
    RecyclerView.Adapter<FAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return contactList.size
    }

    inner class ViewHolder
        (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var nameView = itemView.findViewById<TextView>(R.id.contactName)
        var initialView = itemView.findViewById<TextView>(R.id.contactInitial)


        init {
            //Finds the views from our row.xml
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val pos = adapterPosition
            val context = v.context
            context.startActivity(Intent(context, MainActivity::class.java).apply {
                putExtra("caller", contactList[pos].name)
            })
        }
    }

    init {
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.nameView.text = contactList[i].name
        viewHolder.initialView.text = contactList[i].initial
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }
}