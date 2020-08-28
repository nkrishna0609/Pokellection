package ca.nkrishnaswamy.virtualcardcollectionbinder.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonCardAbility (
    protected val name: String,
    protected val text: String,
    protected val type: String): Parcelable{

    fun getAbilityName(): String{
        return name
    }

    fun getAbilityText(): String{
        return text
    }

    fun getAbilityType(): String{
        return type
    }
}