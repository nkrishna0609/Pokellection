package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.typeConverters

import androidx.room.TypeConverter
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCardAttacks
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PokemonAttacksArrayListTypeConverters {
    val gson= Gson()
    @TypeConverter
    fun convertPokeAttacksArrayListToString(attacksList: ArrayList<PokemonCardAttacks>): String{

        if (attacksList.isNullOrEmpty()){
            return "Not applicable to this card."
        }
        else{
            return gson.toJson(attacksList, object: TypeToken<ArrayList<PokemonCardAttacks>>() {}.type)
        }
    }

    @TypeConverter
    fun convertStringToPokeAttacksArrayList(data: String): ArrayList<PokemonCardAttacks>{
        if (data == "Not applicable to this card." ){
            return arrayListOf<PokemonCardAttacks>()
        }
        else{
            return gson.fromJson(data, object: TypeToken<ArrayList<PokemonCardAttacks>>() {}.type)
        }
    }
}