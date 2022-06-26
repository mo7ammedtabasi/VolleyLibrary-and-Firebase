package com.example.k_myfinalproject.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.k_myfinalproject.databinding.CardPhotosBinding
import com.example.k_myfinalproject.databinding.CardPostBinding
import com.example.k_myfinalproject.model.Photos
import com.example.k_myfinalproject.model.Posts
import com.example.k_myfinalproject.ui.CommentActivity
import com.squareup.picasso.Picasso

class AdapterPhotos(var context: Context, var data: ArrayList<Photos>) :
    RecyclerView.Adapter<AdapterPhotos.MyViewHolder>() {


    class MyViewHolder(binding: CardPhotosBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvId = binding.tvId
        val imageView = binding.imageView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CardPhotosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val photos = data[position]
        holder.tvId.text = "ID: ${photos.id}"
        Picasso.get().load(photos.url).into(holder.imageView);

    }

    override fun getItemCount(): Int {
        return data.size
    }
}