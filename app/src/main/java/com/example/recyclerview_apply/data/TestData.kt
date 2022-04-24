package com.example.recyclerview_apply.data


import com.google.gson.annotations.SerializedName

class TestData : ArrayList<TestData.TestDataItem>(){
    data class TestDataItem(
        @SerializedName("list") var list: List<Content>? = null,
        @SerializedName("tabList") var tabList: List<Tab>? = null,
        @SerializedName("title") var title: String? = null
    ) {
        data class Content(
            @SerializedName("img") var img: String? = null,
            @SerializedName("subTitle") var subTitle: String? = null,
            @SerializedName("title") var title: String? = null
        )
        data class Tab(
            @SerializedName("list") var list: List<TabContent>? = null,
            @SerializedName("tabName") var tabName: String? = null,
            var isSelected: Boolean = false
        ) {
            data class TabContent(
                @SerializedName("img") var img: String? = null
            )
        }
    }
}