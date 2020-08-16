package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.typeConverters

import androidx.room.TypeConverter
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCardAbility
import com.google.gson.Gson

class PokemonAbilityTypeConverter {
    val gson= Gson()


    @TypeConverter
    fun convertPokeAbilityToString(ability: PokemonCardAbility): String{
        if (ability.getAbilityName().isNullOrEmpty()) {
            return "Not applicable to this card."
        }
        else {
            return gson.toJson(ability)
        }
    }

    @TypeConverter
    fun convertStringToPokeAbility(data: String): PokemonCardAbility{
        if (data == "Not applicable to this card." ) {

            return PokemonCardAbility("","","")
        }
        else {
            return gson.fromJson(data, PokemonCardAbility::class.java)
        }
    }

}