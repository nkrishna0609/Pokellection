package ca.nkrishnaswamy.virtualcardcollectionbinder.data.models

import android.os.Parcelable
import androidx.room.TypeConverters
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.typeConverters.StringArrayListTypeConverters
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonCardAttacks(
    @TypeConverters(StringArrayListTypeConverters::class)
    protected val cost: ArrayList<String>,
    protected val name: String,
    protected val text: String,
    protected val damage: String,
    protected val convertedEnergyCost: Int): Parcelable{
}