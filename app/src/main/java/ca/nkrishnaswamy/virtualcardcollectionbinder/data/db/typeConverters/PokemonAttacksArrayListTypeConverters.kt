package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.typeConverters

import androidx.room.TypeConverter
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCardAttacks
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PokemonAttacksArrayListTypeConverters {
    private val gson= Gson()
    @TypeConverter
    fun convertPokeAttacksArrayListToString(attacksList: ArrayList<PokemonCardAttacks>): String{

        return if (attacksList.isNullOrEmpty()){
            "Not applicable to this card."
        } else{
            gson.toJson(attacksList, object: TypeToken<ArrayList<PokemonCardAttacks>>() {}.type)
        }
    }

    @TypeConverter
    fun convertStringToPokeAttacksArrayList(data: String): ArrayList<PokemonCardAttacks>{
        return if (data == "Not applicable to this card." ){
            arrayListOf<PokemonCardAttacks>()
        } else{
            gson.fromJson(data, object: TypeToken<ArrayList<PokemonCardAttacks>>() {}.type)
        }
    }
}