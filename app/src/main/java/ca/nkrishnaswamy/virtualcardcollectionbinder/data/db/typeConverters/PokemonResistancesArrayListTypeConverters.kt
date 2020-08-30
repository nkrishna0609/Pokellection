package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.typeConverters

import androidx.room.TypeConverter
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCardResistances
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PokemonResistancesArrayListTypeConverters {
    private val gson= Gson()

    @TypeConverter
    fun convertPokeResistancesArrayListToString(resistancesList: ArrayList<PokemonCardResistances>): String{

        return if (resistancesList.isNullOrEmpty()){
            "Not applicable to this card."
        } else{
            gson.toJson(resistancesList, object: TypeToken<ArrayList<PokemonCardResistances>>() {}.type)
        }
    }

    @TypeConverter
    fun convertStringToPokeResistancesArrayList(data: String): ArrayList<PokemonCardResistances>{
        return if (data == "Not applicable to this card."){
            arrayListOf<PokemonCardResistances>()
        } else{
            gson.fromJson(data, object: TypeToken<ArrayList<PokemonCardResistances>>() {}.type)
        }
    }
}