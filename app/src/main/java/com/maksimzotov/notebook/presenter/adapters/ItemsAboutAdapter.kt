package com.maksimzotov.notebook.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.maksimzotov.notebook.databinding.ItemAboutBinding
import com.maksimzotov.notebook.domain.entities.itemabout.ItemAbout
import com.maksimzotov.notebook.presenter.main.util.OnItemClickListener

class ItemAboutViewHolder(
    private val binding: ItemAboutBinding,
    private val onCityClickListener: OnItemClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            onCityClickListener.onItemClick(adapterPosition)
        }
    }

    fun bind(item: ItemAbout) {
        with(binding) {
            title.text = item.title
            image.load(item.urlToImage) {
                crossfade(500)
                transformations(RoundedCornersTransformation(25f))
            }
        }
    }
}

class ItemsAboutAdapter(
    var itemsAbout: List<ItemAbout>,
    private val onItemClickListener: OnItemClickListener,
) : RecyclerView.Adapter<ItemAboutViewHolder>() {

    fun setData(newItemsAbout: List<ItemAbout>) {
        val diffUtil = DiffUtilItemsAbout(itemsAbout, newItemsAbout)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        itemsAbout = newItemsAbout
        diffResults.dispatchUpdatesTo(this)
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

class DiffUtilItemsAbout(
    private val oldList: List<ItemAbout>,
    private val newList: List<ItemAbout>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].urlToWebPage == newList[newItemPosition].urlToWebPage

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}