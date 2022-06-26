package com.example.k_myfinalproject.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.k_myfinalproject.R
import com.example.k_myfinalproject.databinding.CardPostBinding
import com.example.k_myfinalproject.databinding.CardTodosBinding
import com.example.k_myfinalproject.model.Posts
import com.example.k_myfinalproject.model.Todos
import com.example.k_myfinalproject.ui.CommentActivity

class AdapterToDos(var context: Context, var data: ArrayList<Todos>) :
    RecyclerView.Adapter<AdapterToDos.MyViewHolder>() {


    class MyViewHolder(binding: CardTodosBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvId = binding.tvId
        val tvTitle = binding.tvTitle
        val imageView = binding.imageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CardTodosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = data[position]
        holder.tvId.text = "ID: ${post.id.toString()}"
        holder.tvTitle.text = "Title: ${post.title}"
        if (post.completed){
            holder.imageView.setImageResource(R.drawable.completed)
        } else {
            holder.imageView.setImageResource(R.drawable.not_completed)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}