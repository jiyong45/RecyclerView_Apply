package com.example.recyclerview_apply.viewholder

import android.graphics.Rect
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview_apply.R
import com.example.recyclerview_apply.data.TestData

class Test3ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
    fun onBind(data: Any?){
        (data as? TestData.TestDataItem)?.let{
            initView(it)
        }
    }

    private fun initView(dataItem: TestData.TestDataItem){
        itemView.findViewById<RecyclerView>(R.id.test3).apply{
            layoutManager = GridLayoutManager(itemView.context,3,LinearLayoutManager.VERTICAL,false)
            adapter = RecycleAdapter(dataItem.list)

            if(itemDecorationCount==0) addItemDecoration(object:RecyclerView.ItemDecoration(){
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    super.getItemOffsets(outRect, view, parent, state)
                    val position = parent.getChildAdapterPosition(view)
                    val column = position % 3
                    Log.d("position", "this position = ${position}")
                    Log.d("spanPosition", "this spanPosition = ${column}")
                    val size = parent.adapter?.itemCount ?: 0
                    if(column == 0){
                        outRect.set(50,50,25,0)
                    }else if(column == 2){
                        outRect.set(25,50,50,0)
                    }else {
                        outRect.set(25,50,25,0)
                    }
                }
            })
        }
    }

    private inner class RecycleAdapter(private val items:List<TestData.TestDataItem.Content>?): RecyclerView.Adapter<ItemViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
            ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_test3_item,parent,false))
        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.onBind(items?.getOrNull(position))
        }

        override fun getItemCount(): Int = items?.size ?: 0
    }

    private inner class ItemViewHolder(view:View): RecyclerView.ViewHolder(view){
        private lateinit var item3_img: ImageView
        private lateinit var test3_cnt: TextView
        private lateinit var test3_Title: TextView
        private lateinit var test3_subTitle: TextView

        fun onBind(data:TestData.TestDataItem.Content?){
            item3_img = itemView.findViewById(R.id.item3_img)
            test3_cnt = itemView.findViewById(R.id.test3_cnt)
            test3_Title = itemView.findViewById(R.id.test3_Title)
            test3_subTitle = itemView.findViewById(R.id.test3_subTitle)

            Glide.with(itemView.context).load(data?.img).into(item3_img)
            test3_cnt.text = (position+1).toString()
            test3_Title.text = data?.title
            test3_subTitle.text = data?.subTitle

        }
    }
}