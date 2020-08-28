package ca.nkrishnaswamy.virtualcardcollectionbinder.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonCardResistances(
    protected val type: String,
    protected val value: String): Parcelable{

    fun getResistanceType():String{
        return type
    }

    fun getPokemonResistanceAmount():String{
        return value
    }
}