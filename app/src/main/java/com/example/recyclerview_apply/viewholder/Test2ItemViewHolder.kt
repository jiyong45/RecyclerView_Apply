package com.example.recyclerview_apply.viewholder

import android.graphics.Color
import android.graphics.Color.parseColor
import android.graphics.Rect
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview_apply.R
import com.example.recyclerview_apply.data.TestData

class Test2ItemViewHolder (view: View): RecyclerView.ViewHolder(view){

    private lateinit var tabList: RecyclerView
    private lateinit var contentList: RecyclerView

    fun onBind(data: Any?){
        (data as? TestData.TestDataItem)?.let{
            initView(it)
        }
    }

    private fun initView(dataItem: TestData.TestDataItem){
        tabList = itemView.findViewById<RecyclerView>(R.id.tab)
        tabList.apply{
            layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = TabListAdapter(dataItem.tabList){
                Toast.makeText(itemView.context," 선택학 값 ? ${dataItem.tabList?.getOrNull(it)?.tabName}",Toast.LENGTH_SHORT).show()

                val oldSelectedIndex = dataItem.tabList?.indexOfFirst { it.isSelected }?: -1
                if(oldSelectedIndex != -1){
                    dataItem.tabList?.getOrNull(oldSelectedIndex)?.isSelected =false
                    dataItem.tabList?.getOrNull(it)?.isSelected = true
                }
                adapter?.notifyItemChanged(oldSelectedIndex)
                adapter?.notifyItemChanged(it)

                (contentList.adapter as? ContentListAdapter)?.items = dataItem.tabList?.getOrNull(it)?.list
            }

            itemAnimator = DefaultItemAnimator().apply{
                changeDuration = 0
            }

            if(itemDecorationCount==0) addItemDecoration(object: RecyclerView.ItemDecoration(){
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ){
                    super.getItemOffsets(outRect, view, parent, state)

                    val position = parent.getChildAdapterPosition(view)

                    val size = parent.adapter?.itemCount ?: 0
                    if(position == 0){
                        outRect.set(100,0,50,0)
                    }else if(position == 0){
                        outRect.set(50,0,100,0)
                    }else {
                        outRect.set(50,0,50,0)
                    }
                }
            })
        }

        contentList = itemView.findViewById(R.id.content)
        contentList.apply{
            layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL,false)
            adapter = ContentListAdapter().apply{
                var selectedIndex = dataItem.tabList?.indexOfFirst { it.isSelected } ?: -1
                if(selectedIndex == -1){
                    selectedIndex = 0
                }
                items = dataItem.tabList?.getOrNull(selectedIndex)?.list
            }
        }
    }

    private inner class TabListAdapter(
        private val items:List<TestData.TestDataItem.Tab>?,
        private val callback: (Int) -> Unit
    ): RecyclerView.Adapter<TabItemViewHolder>(){
        override fun getItemCount(): Int = items?.size ?: 0
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabItemViewHolder =
            TabItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_test2_tab_item,parent,false))
        override fun onBindViewHolder(holder: TabItemViewHolder, position: Int) {
            holder.onBind(items?.getOrNull(position), callback)
        }
    }

    private inner class TabItemViewHolder(view:View): RecyclerView.ViewHolder(view){
        private lateinit var txt: TextView
        private lateinit var clickCallback: (Int) -> Unit
        fun onBind(data:TestData.TestDataItem.Tab?, callback:(Int) -> Unit){
            txt = itemView.findViewById(R.id.tabTxt)
            clickCallback = callback

            txt.apply{
                text = data?.tabName
                if(data?.isSelected == true){
                    setTypeface(typeface, Typeface.BOLD)
                    setTextColor(Color.parseColor("#e33333"))
                }else {
                    setTypeface(typeface, Typeface.NORMAL)
                    setTextColor(Color.parseColor("#000000"))
                }
            }
            itemView.setOnClickListener{
                clickCallback.invoke(adapterPosition)
            }
        }
    }

    private inner class ContentListAdapter: RecyclerView.Adapter<ContentItemViewHolder>(){
        var items: List<TestData.TestDataItem.Tab.TabContent>? = null
            set(value){
                notifyItemRangeRemoved(0,field?.size ?:0)
                field = value
                notifyItemRangeInserted(0,value?.size ?: 0)
            }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentItemViewHolder =
            ContentItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_test2_item,parent,false))

        override fun onBindViewHolder(holder: ContentItemViewHolder, position: Int) {
            holder.onBind(items?.getOrNull(position))
        }

        override fun getItemCount(): Int = items?.size ?:0
    }

    private inner class ContentItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        private lateinit var img: ImageView
        fun onBind(data:TestData.TestDataItem.Tab.TabContent?){
            img = itemView.findViewById(R.id.img)
            Glide.with(itemView.context).load(data?.img).into(img)
        }

    }
}
