package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringArrayListTypeConverters {
    private val gson= Gson()
    @TypeConverter
    fun convertPokeTypesArrayListToString(stringList: ArrayList<String>): String{

        return if (stringList.isNullOrEmpty()){
            "Not applicable to this card."
        } else{
            gson.toJson(stringList, object: TypeToken<ArrayList<String>>() {}.type)
        }
    }

    @TypeConverter
    fun convertStringToPokeTypesArrayList(data: String): ArrayList<String>{
        return if (data == "Not applicable to this card."){
            arrayListOf<String>()
        } else{
            gson.fromJson(data, object: TypeToken<ArrayList<String>>() {}.type)
        }
    }
}