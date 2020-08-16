package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.typeConverters

import androidx.room.TypeConverter
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCardAncientTrait
import com.google.gson.Gson

class PokemonAncientTraitTypeConverter {
    val gson= Gson()


    @TypeConverter
    fun convertPokeAncientTraitToString(ability: PokemonCardAncientTrait): String{
        if (ability.getTraitName().isNullOrEmpty()) {
            return "Not applicable to this card."
        }
        else {
            return gson.toJson(ability)
        }
    }

    @TypeConverter
    fun convertStringToPokeAbility(data: String): PokemonCardAncientTrait {
        if (data == "Not applicable to this card.") {

            return PokemonCardAncientTrait("", "")
        } else {
            return gson.fromJson(data, PokemonCardAncientTrait::class.java)
        }
    }
}