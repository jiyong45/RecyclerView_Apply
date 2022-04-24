package com.example.recyclerview_apply.utils

import android.content.res.AssetManager
import java.io.InputStream

fun AssetManager.getStringFromAsset(fileName: String): String {
    var `is`: InputStream? = null
    var result: String = ""
    try {
        `is` = this.open(fileName)
        val size = `is`.available()
        if(size > 0){
            val data = ByteArray(size)
            `is`.read(data)
            result = String(data)
        }
    } catch (e: java.lang.Exception){
        e.printStackTrace()
    } finally {
        if(`is` != null){
            try{
                `is`.close()
            }catch (ignore:java.lang.Exception){
            }
        }
    }
    return result
}