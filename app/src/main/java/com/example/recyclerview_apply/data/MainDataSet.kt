package com.example.recyclerview_apply.data

data class MainDataSet(
    val viewType: ViewType,
    var data: Any? = null
)

enum class ViewType {
    TEST1,
    TEST2,
    TEST3,
    EMPTY
}