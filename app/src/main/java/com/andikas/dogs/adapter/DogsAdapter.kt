package com.andikas.dogs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andikas.dogs.R
import com.andikas.dogs.data.response.DogsResponseItem
import com.andikas.dogs.databinding.ItemDogBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class DogsAdapter(
    private val dogs: List<DogsResponseItem>? = null,
) : RecyclerView.Adapter<DogsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsAdapter.ViewHolder {
        val binding = ItemDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogsAdapter.ViewHolder, position: Int) {
        if (dogs != null) {
            val dog = dogs[position]

            Glide.with(holder.itemView)
                .load(dog.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_dog)
                .error(R.drawable.img_error)
                .centerCrop()
                .into(holder.binding.imgDog)
        }
    }

    override fun getItemCount(): Int = dogs?.size ?: 20

    inner class ViewHolder(val binding: ItemDogBinding) : RecyclerView.ViewHolder(binding.root)

}