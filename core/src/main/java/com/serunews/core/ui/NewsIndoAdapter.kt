package com.serunews.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.serunews.core.R
import com.serunews.core.databinding.NewsItemListBinding
import com.serunews.core.domain.model.IndoNews

class NewsIndoAdapter : RecyclerView.Adapter<NewsIndoAdapter.NewsTechViewHolder>() {

    private var listData = ArrayList<IndoNews>()
    var onItemClick: ((IndoNews) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<IndoNews>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = NewsTechViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.news_item_list, parent, false)
    )

    override fun onBindViewHolder(holder: NewsIndoAdapter.NewsTechViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class NewsTechViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = NewsItemListBinding.bind(itemView)
        fun bind(data: IndoNews) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.foto)
                    .into(imgNews)
                tvTitle.text = data.judul
                tvDate.text = data.rilisberita

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

}