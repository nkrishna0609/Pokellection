package ca.nkrishnaswamy.virtualcardcollectionbinder.data.models

import android.os.Parcelable
import androidx.room.*
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.typeConverters.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "pokemonCardsTable", indices = [Index(value=["card_setName","card_num"],unique = true)])
data class PokemonCard(
    @ColumnInfo(name="card_name")
    private var name: String,
    @ColumnInfo(name="card_img")
    private var imageUrlHiRes: String,
    @ColumnInfo(name="card_types")
    @TypeConverters(StringArrayListTypeConverters::class)
    private var types: ArrayList<String>,
    @ColumnInfo(name="card_supertype")
    private var supertype: String,
    @ColumnInfo(name="card_subtype")
    private var subtype: String,
    @ColumnInfo(name="card_evolvesFrom")
    private var evolvesFrom: String,
    @ColumnInfo(name="card_hp")
    private var hp: String,
    @ColumnInfo(name="card_num")
    private var number: String,
    @ColumnInfo(name="card_rarity")
    private var rarity: String,
    @ColumnInfo(name="card_seriesName")
    private var series: String,
    @ColumnInfo(name="card_setName")
    private var set: String,
    @ColumnInfo(name="card_setCode")
    private var setCode: String,
    @ColumnInfo(name="card_attacks")
    @TypeConverters(PokemonAttacksArrayListTypeConverters::class)
    private var attacks: ArrayList<PokemonCardAttacks>,
    @ColumnInfo(name="card_weaknesses")
    @TypeConverters(PokemonWeaknessesArrayListTypeConverters::class)
    private var weaknesses: ArrayList<PokemonCardWeaknesses>,
    @ColumnInfo(name="card_resistances")
    @TypeConverters(PokemonResistancesArrayListTypeConverters::class)
    private var resistances: ArrayList<PokemonCardResistances>,
    @ColumnInfo(name="card_ancientTrait")
    @TypeConverters(PokemonAncientTraitTypeConverter::class)
    private var ancientTrait: PokemonCardAncientTrait,
    @ColumnInfo(name="card_ability")
    @TypeConverters(PokemonAbilityTypeConverter::class)
    private var ability: PokemonCardAbility
): Parcelable
{
    @PrimaryKey(autoGenerate = true)
    private var id: Long = 0

    fun getId(): Long{
        return id
    }
    fun setId(num: Long){
        id=num
    }

    fun getAbility(): PokemonCardAbility {
        return ability
    }

    fun getAncientTrait(): PokemonCardAncientTrait {
        return ancientTrait
    }

    fun getTypes():ArrayList<String>{
        return types
    }

    fun getAttacks():ArrayList<PokemonCardAttacks>{
        return attacks
    }

    fun getWeaknesses():ArrayList<PokemonCardWeaknesses>{
        return weaknesses
    }

    fun getResistances():ArrayList<PokemonCardResistances>{
        return resistances
    }

    fun getName():String{
        return name
    }

    fun getImageUrlHiRes():String{
        return imageUrlHiRes
    }

    fun getSupertype():String{
        return supertype
    }

    fun getSubtype():String{
        return subtype
    }

    fun getEvolvesFrom():String{
        if (evolvesFrom.isNullOrEmpty()){
            return "Not applicable to this card."
        }
        else{
            return evolvesFrom
        }
    }

    fun getHp():String{
        if (hp.isNullOrEmpty()){
            return "Not applicable to this card."
        }
        else{
            return hp
        }
    }

    fun getNumber():String{
        return number
    }

    fun getRarity():String{
        return rarity
    }

    fun getSeries():String{
        return series
    }

    fun getSet():String{
        return set
    }

    fun getSetCode():String{
        return setCode
    }

    override fun toString():String{
        return "$name - $set $number"
    }
}
