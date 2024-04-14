package com.sharedpref_hilt

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sharedpref_hilt.model.CustomClass
import java.lang.reflect.Type
import java.util.*
import javax.inject.Inject

class AppSession @Inject constructor(val sharedPreferences: SharedPreferences) {


    fun getStringData( keyName:String, defaultValue: String?):String{
        return sharedPreferences?.let {
            sharedPreferences.getString(keyName, defaultValue)
        }.toString()

    }

    fun putStringData( keyName:String, defaultValue: String){

        sharedPreferences?. let {
            var editor=sharedPreferences.edit()
            editor.putString(keyName,defaultValue)
            editor.commit()
        }

    }
    fun putBooleanData(keyName: String, defaultValue: Boolean) {
        sharedPreferences?. let {
            var editor=sharedPreferences.edit()
            editor.putBoolean(keyName,defaultValue)
            editor.commit()
        }

    }

    fun getBooleanData(keyName: String, defaultValue: Boolean): Boolean? {
        return sharedPreferences?.let {
            sharedPreferences.getBoolean(keyName, defaultValue)
        }
    }

    fun getObjectData(keyName: String, classOfT: Class<*>?): Any? {
        val json= sharedPreferences?.getString(keyName, "")
        val gson = Gson()
        val selectedUser = gson.fromJson(json, classOfT)
        return selectedUser

    }

    fun putObjectData(keyName: String, modelObj: Object){

        val gson = Gson()
        val jsonObject = gson.toJson(modelObj)
        var editor=sharedPreferences.edit()
        editor.putString(keyName, jsonObject)
        editor.commit()
    }

    fun getArrayList(key: String?): ArrayList<CustomClass> {

        val gson = Gson()

        val json = sharedPreferences.getString(key, null)
        val type: Type = object : TypeToken<ArrayList<CustomClass?>?>() {}.type
        var courseList = gson.fromJson<Any>(json, type)
        var notificationList = ArrayList<CustomClass>()

        if(courseList==null) {
            courseList = ArrayList<CustomClass>()

        }else{
            notificationList=courseList as ArrayList<CustomClass>
        }

        return  notificationList
    }
    fun saveArrayList(list: java.util.ArrayList<CustomClass>, key: String?) {
        val editor= sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()


    }



}