package com.example.k_myfinalproject.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.k_myfinalproject.databinding.CardCommentBinding
import com.example.k_myfinalproject.databinding.CardPostBinding
import com.example.k_myfinalproject.model.Comments
import com.example.k_myfinalproject.model.Posts
import com.example.k_myfinalproject.ui.CommentActivity

class AdapterComments(var context: Context, var data: ArrayList<Comments>) :
    RecyclerView.Adapter<AdapterComments.MyViewHolder>() {


    class MyViewHolder(binding:CardCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvId = binding.tvId
        val tvName = binding.tvName
        val tvEmail = binding.tvEmail
        val tvBody = binding.tvBody
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CardCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = data[position]
        holder.tvId.text = "ID: ${post.id.toString()}"
        holder.tvName.text = "Name: ${post.name}"
        holder.tvEmail.text = "Name: ${post.email}"
        holder.tvBody.text = "Body: ${post.body}"
    }

    override fun getItemCount(): Int {
        return data.size
    }
}