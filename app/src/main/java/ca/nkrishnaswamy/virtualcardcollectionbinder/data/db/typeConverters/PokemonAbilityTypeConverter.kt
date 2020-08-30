package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.typeConverters

import androidx.room.TypeConverter
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCardAbility
import com.google.gson.Gson

class PokemonAbilityTypeConverter {
    private val gson= Gson()


    @TypeConverter
    fun convertPokeAbilityToString(ability: PokemonCardAbility): String{
        return if (ability.getAbilityName().isNullOrEmpty()) {
            "Not applicable to this card."
        } else {
            gson.toJson(ability)
        }
    }

    @TypeConverter
    fun convertStringToPokeAbility(data: String): PokemonCardAbility{
        return if (data == "Not applicable to this card." ) {
            PokemonCardAbility("","","")
        } else {
            gson.fromJson(data, PokemonCardAbility::class.java)
        }
    }

}