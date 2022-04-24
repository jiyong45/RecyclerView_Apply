package com.example.recyclerview_apply

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_apply.data.MainDataSet
import com.example.recyclerview_apply.data.TestData
import com.example.recyclerview_apply.data.ViewType
import com.example.recyclerview_apply.utils.getStringFromAsset
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val testData = getData()
        val mainDataSet = validateDataEntity(testData)

        findViewById<RecyclerView>(R.id.recyclerView).apply{
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL, false)
            adapter = MainListAdapter(mainDataSet)
        }
    }

    private fun validateDataEntity(testData: TestData?): MutableList<MainDataSet>{
        val modules = mutableListOf<MainDataSet>()

        testData?.forEach{
            val viewType = when (it.title){
                "TEST1" -> ViewType.TEST1
                "TEST2" -> ViewType.TEST2
                "TEST3" -> ViewType.TEST3
                else -> ViewType.EMPTY
            }
            if(viewType == ViewType.TEST2){
                it.tabList?.getOrNull(0)?.isSelected = true
            }
            modules.add(MainDataSet(viewType = viewType, data = it))
        }
        return modules
    }

    private fun getData(): TestData? {
        return try {
            val str = applicationContext.assets.getStringFromAsset("data.json")
            val response = Gson().fromJson(str, TestData::class.java)
            response
        } catch (ex: Exception) {
            null
        }
    }
}