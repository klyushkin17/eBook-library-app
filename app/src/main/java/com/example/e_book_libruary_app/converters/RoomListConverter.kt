package com.example.e_book_libruary_app.converters

import androidx.room.TypeConverter
import com.google.gson.Gson

class RoomListConverter{

    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}