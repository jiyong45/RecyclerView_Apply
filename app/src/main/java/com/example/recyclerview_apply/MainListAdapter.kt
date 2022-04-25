package com.example.recyclerview_apply

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_apply.data.MainDataSet
import com.example.recyclerview_apply.data.ViewType
import com.example.recyclerview_apply.viewholder.Test1ItemViewHolder
import com.example.recyclerview_apply.viewholder.Test2ItemViewHolder
import com.example.recyclerview_apply.viewholder.Test3ItemViewHolder

class MainListAdapter (private val items: MutableList<MainDataSet>? = null): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = items?.size ?: 0
    override fun getItemViewType(position: Int): Int = items?.getOrNull(position)?.viewType?.ordinal ?: ViewType.EMPTY.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (ViewType.values()[viewType]){
            ViewType.TEST2 -> Test2ItemViewHolder(LayoutInflater.from(parent.context).inflate((R.layout.view_test2),parent,false))
            ViewType.TEST3 -> Test3ItemViewHolder(LayoutInflater.from(parent.context).inflate((R.layout.view_test3),parent,false))
            else -> Test1ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_test1,parent,false))
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = items?.getOrNull(position)?.data
        (holder as? Test1ItemViewHolder)?.onBind(data)
        (holder as? Test2ItemViewHolder)?.onBind(data)
        (holder as? Test3ItemViewHolder)?.onBind(data)
    }


}