package ca.nkrishnaswamy.virtualcardcollectionbinder.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonCardWeaknesses (
    protected val type: String,
    protected val value: String): Parcelable {
}