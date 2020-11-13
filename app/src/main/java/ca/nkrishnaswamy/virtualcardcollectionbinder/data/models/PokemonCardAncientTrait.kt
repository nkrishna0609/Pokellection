package ca.nkrishnaswamy.virtualcardcollectionbinder.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonCardAncientTrait(
    protected val name: String,
    protected val text: String): Parcelable {

    fun getTraitName(): String{
        return name
    }
}