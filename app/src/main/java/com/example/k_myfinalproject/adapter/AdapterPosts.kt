package com.example.k_myfinalproject.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.k_myfinalproject.databinding.CardPostBinding
import com.example.k_myfinalproject.model.Posts
import com.example.k_myfinalproject.ui.CommentActivity

class AdapterPosts(var context: Context, var data: ArrayList<Posts>) :
    RecyclerView.Adapter<AdapterPosts.MyViewHolder>() {


    class MyViewHolder(binding: CardPostBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvId = binding.tvId
        val tvTitle = binding.tvTitle
        val tvBody = binding.tvBody
        val btnComment = binding.btnComment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = data[position]
        holder.tvId.text = "ID: ${post.id.toString()}"
        holder.tvTitle.text = "Title: ${post.title}"
        holder.tvBody.text = "Body: ${post.body}"
        holder.btnComment.setOnClickListener {
            val intent = Intent(context,CommentActivity::class.java)
            intent.putExtra("postId",post.id)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
}