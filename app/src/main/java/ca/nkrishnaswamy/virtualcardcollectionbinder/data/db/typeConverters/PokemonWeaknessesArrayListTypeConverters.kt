package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.typeConverters

import androidx.room.TypeConverter
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCardWeaknesses
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PokemonWeaknessesArrayListTypeConverters {
    val gson= Gson()

    @TypeConverter
    fun convertPokeWeaknessesArrayListToString(weaknessesList: ArrayList<PokemonCardWeaknesses>): String{

        if (weaknessesList.isNullOrEmpty()){
            return "Not applicable to this card."
        }
        else{
            return gson.toJson(weaknessesList, object: TypeToken<ArrayList<PokemonCardWeaknesses>>() {}.type)
        }
    }

    @TypeConverter
    fun convertStringToPokeWeaknessesArrayList(data: String): ArrayList<PokemonCardWeaknesses>{
        if (data == "Not applicable to this card."){
            return arrayListOf<PokemonCardWeaknesses>()
        }
        else{
            return gson.fromJson(data, object: TypeToken<ArrayList<PokemonCardWeaknesses>>() {}.type)
        }
    }
}