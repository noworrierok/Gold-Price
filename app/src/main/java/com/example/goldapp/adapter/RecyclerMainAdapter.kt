package com.example.goldapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.goldapp.databinding.RecyclerMainItemBinding
import com.example.goldapp.remote.datamodel.ContentModel
import java.text.DecimalFormat

class RecyclerMainAdapter(
    private val allData : ArrayList<ContentModel>
) : RecyclerView.Adapter<RecyclerMainAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(RecyclerMainItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))

    }

    override fun getItemCount(): Int = allData.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setData(allData[position])
    }

    inner class ItemViewHolder(
        private val binding : RecyclerMainItemBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun setData(data: ContentModel){

            val formatter = DecimalFormat("#,###")
            val price = data.price / 10
            val formattedPrice = formatter.format(price).replace(",", "/")

            binding.txtHeaderItem.text = data.label
            binding.txtPriceItem.text = formattedPrice

        }

    }
}
