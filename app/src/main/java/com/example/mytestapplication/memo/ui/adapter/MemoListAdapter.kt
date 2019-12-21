package com.example.mytestapplication.memo.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.mytestapplication.R
import com.example.mytestapplication.base.DataBindingAdapter
import com.example.mytestapplication.memo.data.MemoTile

class MemoListAdapter : DataBindingAdapter<MemoTile>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<MemoTile>() {
        override fun areItemsTheSame(oldItem: MemoTile, newItem: MemoTile): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MemoTile, newItem: MemoTile): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun getItemViewType(position: Int) = R.layout.item_memo
}