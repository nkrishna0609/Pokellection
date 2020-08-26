package ca.nkrishnaswamy.virtualcardcollectionbinder.data.models

import androidx.room.*
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.typeConverters.*

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
)
{
    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0

    fun getId(): Int{
        return id
    }
    fun setId(num: Int){
        id=num
    }

    fun getAbility(): PokemonCardAbility {
        return ability
    }

    fun setAbility(newAbility: PokemonCardAbility) {
        ability = newAbility
    }

    fun getAncientTrait(): PokemonCardAncientTrait {
        return ancientTrait
    }
    fun setAncientTrait(newTrait: PokemonCardAncientTrait) {
        ancientTrait = newTrait
    }
    fun getTypes():ArrayList<String>{
        return types
    }

    fun setTypes(newTypes: ArrayList<String>){
        types = newTypes
    }

    fun getAttacks():ArrayList<PokemonCardAttacks>{
        return attacks
    }

    fun setAttacks(newAttacks: ArrayList<PokemonCardAttacks>){
        attacks = newAttacks
    }

    fun getWeaknesses():ArrayList<PokemonCardWeaknesses>{
        return weaknesses
    }

    fun setWeaknesses(newWeaknesses: ArrayList<PokemonCardWeaknesses>){
        weaknesses = newWeaknesses
    }

    fun getResistances():ArrayList<PokemonCardResistances>{
        return resistances
    }

    fun setResistances(newResists: ArrayList<PokemonCardResistances>){
        resistances = newResists
    }

    fun getName():String{
        return name
    }

    fun setName(newName: String){
        name= newName
    }


    fun getImageUrlHiRes():String{
        return imageUrlHiRes
    }

    fun setImageUrlHiRes(newImg: String){
        imageUrlHiRes = newImg
    }

    fun getSupertype():String{
        return supertype
    }

    fun setSupertype(newType: String){
        supertype = newType
    }

    fun getSubtype():String{
        return subtype
    }

    fun setSubtype(newType: String){
        subtype = newType
    }

    fun getEvolvesFrom():String{
        if (evolvesFrom.isNullOrEmpty()){
            return "Not applicable to this card."
        }
        else{
            return evolvesFrom
        }
    }

    fun setEvolvesFrom(newEvolves: String){
        evolvesFrom = newEvolves
    }

    fun getHp():String{
        if (hp.isNullOrEmpty()){
            return "Not applicable to this card."
        }
        else{
            return hp
        }
    }

    fun setHp(newHp: String){
        hp = newHp
    }

    fun getNumber():String{
        return number
    }

    fun setNumber(newSet:String){
        number = newSet
    }

    fun getRarity():String{
        return rarity
    }

    fun setRarity(newSet:String){
        rarity = newSet
    }
    fun getSeries():String{
        return series
    }

    fun setSeries(newSeries:String){
        series = newSeries
    }

    fun getSet():String{
        return set
    }

    fun setSet(newSet: String){
        set=newSet
    }

    fun getSetCode():String{
        return setCode
    }

    fun setSetCode(newCode: String){
        setCode = newCode
    }
    override fun toString():String{
        return "$name - $set $number"
    }
    /*
    fun pokemonWeaknessesToString(): String{
        var output=""
        var separator = ""

        if (weaknesses.isNullOrEmpty()){
            output= "Not applicable to this card."
        }
        else{
            for (weakness in weaknesses){
                output=output+separator + weakness.toString()
                separator = ", "
            }
        }
        return output
    }
    fun pokemonAncientTraitToString(): String {
        if (ancientTrait.getPokemonAncientTraitName().isNullOrEmpty()){
            return "Not applicable to this card."
        }
        else{
            return "\n\t\tName: ${ancientTrait.getPokemonAncientTraitName()}\n\t\tDescription: ${ancientTrait.getPokemonAncientTraitText()}"
        }
    }

    fun pokemonAbilityToString(): String {
        if (ability.getPokemonAbilityName().isNullOrEmpty()){
            return "Not applicable to this card."
        }
        else{
            return "\n\t\tName: ${ability.getPokemonAbilityName()}\n\t\tType: ${ability.getPokemonAbilityType()}\n\t\tDescription: ${ability.getPokemonAbilityText()}"

        }
    }

    fun pokemonResistancesToString(): String{
        var output=""
        var separator = ""
        if (resistances.isNullOrEmpty()){
            output= "Not applicable to this card."
        }
        else{
            for (resistance in resistances){
                output=output+separator + resistance.toString()
                separator = ", "
            }
        }
        return output
    }

    fun pokemonTypesToString(): String{
        var output=""
        var separator = ""

        if (types.isNullOrEmpty()){
            output= "Not applicable to this card."
        }
        else{
            for (type in types){
                output=output+separator + type
                separator = ", "
            }
        }
        return output
    }

    fun pokemonAttacksToString(): String{
        var output=""
        var count=1

        if (attacks.isNullOrEmpty()){
            output="Not applicable to this card."
        }
        else{
            for (attack in attacks){
                output += "\n\t\tAttack $count: \n${attack.toString()}"
                count++
            }
        }
        return output
    }
*/
}
