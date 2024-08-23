package com.plcoding.rtl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class Adapter : RecyclerView.Adapter<Adapter.MyViewHolder>() {
    private val dataSource: MutableList<Int> = mutableListOf()
    
    init {
        repeat(30) {
            dataSource.add(it + 1)
        }
    }
    
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        
        
        init {
            textView.setOnClickListener {
                Toast.makeText(it.context, (it as TextView).text, Toast.LENGTH_SHORT).show()
            }
        }
        
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_vh, parent, false))
    }
    
    override fun getItemCount() = dataSource.size
    
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = dataSource[position].toString()
    }
}