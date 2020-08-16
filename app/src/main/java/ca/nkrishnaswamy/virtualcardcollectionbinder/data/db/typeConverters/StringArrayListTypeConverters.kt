package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringArrayListTypeConverters {
    val gson= Gson()
    @TypeConverter
    fun convertPokeTypesArrayListToString(stringList: ArrayList<String>): String{

        if (stringList.isNullOrEmpty()){
            return "Not applicable to this card."
        }
        else{
            return gson.toJson(stringList, object: TypeToken<ArrayList<String>>() {}.type)
        }
    }

    @TypeConverter
    fun convertStringToPokeTypesArrayList(data: String): ArrayList<String>{
        if (data == "Not applicable to this card."){
            return arrayListOf<String>()
        }
        else{
            return gson.fromJson(data, object: TypeToken<ArrayList<String>>() {}.type)
        }
    }
}