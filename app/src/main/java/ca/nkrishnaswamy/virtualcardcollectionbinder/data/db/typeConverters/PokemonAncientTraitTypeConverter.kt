package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.typeConverters

import androidx.room.TypeConverter
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCardAncientTrait
import com.google.gson.Gson

class PokemonAncientTraitTypeConverter {
    private val gson= Gson()

    @TypeConverter
    fun convertPokeAncientTraitToString(ability: PokemonCardAncientTrait): String{
        return if (ability.getTraitName().isNullOrEmpty()) {
            "Not applicable to this card."
        } else {
            gson.toJson(ability)
        }
    }

    @TypeConverter
    fun convertStringToPokeAbility(data: String): PokemonCardAncientTrait {
        return if (data == "Not applicable to this card.") {

            PokemonCardAncientTrait("", "")
        } else {
            gson.fromJson(data, PokemonCardAncientTrait::class.java)
        }
    }
}