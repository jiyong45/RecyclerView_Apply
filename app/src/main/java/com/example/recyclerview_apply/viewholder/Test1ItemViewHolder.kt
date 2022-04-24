package com.example.recyclerview_apply.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.recyclerview_apply.R
import com.example.recyclerview_apply.data.TestData

class Test1ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
    fun onBind(data: Any?){
        (data as? TestData.TestDataItem)?.let{
            initView(it)
        }
    }

    private fun initView(dataItem: TestData.TestDataItem){
        itemView.findViewById<ViewPager2>(R.id.viewPager).apply{
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = PagerAdapter(dataItem.list)
        }
    }

    private inner class PagerAdapter(private val items:List<TestData.TestDataItem.Content>?): RecyclerView.Adapter<ItemViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
            ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_test1_item,parent,false))
        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.onBind(items?.getOrNull(position))
        }
        override fun getItemCount(): Int = items?.size ?:0

    }

    private inner class ItemViewHolder(view:View): RecyclerView.ViewHolder(view){
        private lateinit var img: ImageView
        private lateinit var title: TextView
        private lateinit var subTitle: TextView

        fun onBind(data: TestData.TestDataItem.Content?) {
            img = itemView.findViewById(R.id.img)
            title = itemView.findViewById(R.id.title)
            subTitle = itemView.findViewById(R.id.subTitle)

            Glide.with(itemView.context).load(data?.img).into(img)
            title.text = data?.title
            subTitle.text = data?.subTitle
        }
    }
}