package com.maksimzotov.notebook.presenter.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maksimzotov.notebook.databinding.ItemAboutBinding
import com.maksimzotov.notebook.domain.entities.itemabout.ItemAbout
import com.maksimzotov.notebook.presenter.main.util.OnItemClickListener

class ItemAboutViewHolder(
    binding: ItemAboutBinding,
    private val onCityClickListener: OnItemClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            onCityClickListener.onItemClick(adapterPosition)
        }
    }

    fun bind(item: ItemAbout) {
        TODO()
    }
}

class ItemsAboutAdapter(
    var itemsAbout: List<ItemAbout>,
    private val onItemClickListener: OnItemClickListener,
) : RecyclerView.Adapter<ItemAboutViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(cities: List<ItemAbout>) {
        itemsAbout = cities
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAboutViewHolder {
        return ItemAboutViewHolder(
            ItemAboutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: ItemAboutViewHolder, position: Int) {
        holder.bind(itemsAbout[position])
    }

    override fun getItemCount(): Int {
        return itemsAbout.size
    }
}