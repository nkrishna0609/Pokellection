package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.typeConverters

import androidx.room.TypeConverter
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCardResistances
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PokemonResistancesArrayListTypeConverters {
    val gson= Gson()

    @TypeConverter
    fun convertPokeResistancesArrayListToString(resistancesList: ArrayList<PokemonCardResistances>): String{

        if (resistancesList.isNullOrEmpty()){
            return "Not applicable to this card."
        }
        else{
            return gson.toJson(resistancesList, object: TypeToken<ArrayList<PokemonCardResistances>>() {}.type)
        }
    }

    @TypeConverter
    fun convertStringToPokeResistancesArrayList(data: String): ArrayList<PokemonCardResistances>{
        if (data == "Not applicable to this card."){
            return arrayListOf<PokemonCardResistances>()
        }
        else{
            return gson.fromJson(data, object: TypeToken<ArrayList<PokemonCardResistances>>() {}.type)
        }
    }
}