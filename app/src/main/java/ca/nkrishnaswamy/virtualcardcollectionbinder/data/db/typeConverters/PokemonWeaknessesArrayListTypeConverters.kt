package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.typeConverters

import androidx.room.TypeConverter
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCardWeaknesses
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PokemonWeaknessesArrayListTypeConverters {
    private val gson= Gson()

    @TypeConverter
    fun convertPokeWeaknessesArrayListToString(weaknessesList: ArrayList<PokemonCardWeaknesses>): String{

        return if (weaknessesList.isNullOrEmpty()){
            "Not applicable to this card."
        } else{
            gson.toJson(weaknessesList, object: TypeToken<ArrayList<PokemonCardWeaknesses>>() {}.type)
        }
    }

    @TypeConverter
    fun convertStringToPokeWeaknessesArrayList(data: String): ArrayList<PokemonCardWeaknesses>{
        return if (data == "Not applicable to this card."){
            arrayListOf<PokemonCardWeaknesses>()
        } else{
            gson.fromJson(data, object: TypeToken<ArrayList<PokemonCardWeaknesses>>() {}.type)
        }
    }
}