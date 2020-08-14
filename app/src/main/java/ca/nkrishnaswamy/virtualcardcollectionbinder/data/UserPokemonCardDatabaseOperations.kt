package ca.nkrishnaswamy.virtualcardcollectionbinder.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.response.PokemonCard

class UserPokemonCardDatabaseOperations(context: Context): SQLiteOpenHelper(context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {

    companion object{
        const val DATABASE_NAME = "UserPokemonCard.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "pokemonCardsTable"
        const val COLUMN_ID = "ID"
        const val COLUMN_POKEMONNAME = "NAME"
        const val COLUMN_POKEMONID = "POKEMONID"
        const val COLUMN_POKEDEXNUM = "NATIONALPOKEDEXNUMBER"
        const val COLUMN_IMAGE = "IMAGEURLHIRES"
        const val COLUMN_TYPES = "TYPES"
        const val COLUMN_SUPERTYPE = "SUPERTYPE"
        const val COLUMN_SUBTYPE = "SUBTYPE"
        const val COLUMN_EVOLVESFROM = "EVOLVESFROM"
        const val COLUMN_HP = "HP"
        const val COLUMN_RETREATCOST = "RETREATCOST"
        const val COLUMN_NUMBER = "NUMBER"
        const val COLUMN_RARITY = "RARITY"
        const val COLUMN_SERIES = "SERIES"
        const val COLUMN_SETNAME = "SETNAME"
        const val COLUMN_SETCODE = "SETCODE"
        const val COLUMN_ATTACKS = "ATTACKS"
        const val COLUMN_WEAKNESSES = "WEAKNESSES"
        const val COLUMN_RESISTANCES="RESISTANCES"
        const val COLUMN_ANCIENTTRAIT="ANCIENTTRAIT"
        const val COLUMN_ABILITY="ABILITY"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_NAME(ID INTEGER PRIMARY KEY "+"AUTOINCREMENT,NAME TEXT," +
                "POKEMONID TEXT,NATIONALPOKEDEXNUMBER TEXT,IMAGEURLHIRES TEXT,TYPES TEXT,SUPERTYPE TEXT," +
                "SUBTYPE TEXT,EVOLVESFROM TEXT,HP TEXT,RETREATCOST TEXT," +
                "NUMBER TEXT,RARITY TEXT,SERIES TEXT,SETNAME TEXT,SETCODE TEXT,ATTACKS TEXT,WEAKNESSES TEXT,RESISTANCES TEXT,ANCIENTTRAIT TEXT,ABILITY TEXT) ")
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME)
    }

    fun insertCard(card: PokemonCard){
        val db=this.writableDatabase
        val contentvalues=ContentValues()
        contentvalues.put(COLUMN_POKEMONNAME,card.getName())
        contentvalues.put(COLUMN_POKEMONID,card.getId())
        contentvalues.put(COLUMN_POKEDEXNUM,card.getPokedexNum())
        contentvalues.put(COLUMN_IMAGE,card.getImage())
        contentvalues.put(COLUMN_TYPES,card.pokemonTypesToString())
        contentvalues.put(COLUMN_SUPERTYPE,card.getSuperType())
        contentvalues.put(COLUMN_SUBTYPE,card.getSubType())
        contentvalues.put(COLUMN_EVOLVESFROM,card.getEvolvesFrom())
        contentvalues.put(COLUMN_HP,card.gethp())
        contentvalues.put(COLUMN_RETREATCOST,card.getRetreatCost())
        contentvalues.put(COLUMN_NUMBER,card.getSetNum())
        contentvalues.put(COLUMN_RARITY,card.getRarity())
        contentvalues.put(COLUMN_SERIES,card.getSeriesName())
        contentvalues.put(COLUMN_SETNAME,card.getSetName())
        contentvalues.put(COLUMN_SETCODE,card.getSetCode())
        contentvalues.put(COLUMN_ATTACKS,card.pokemonAttacksToString())
        contentvalues.put(COLUMN_WEAKNESSES,card.pokemonWeaknessesToString())
        contentvalues.put(COLUMN_RESISTANCES,card.pokemonResistancesToString())
        contentvalues.put(COLUMN_ANCIENTTRAIT,card.pokemonAncientTraitToString())
        contentvalues.put(COLUMN_ABILITY,card.pokemonAbilityToString())

        db.insert(TABLE_NAME,null, contentvalues)
    }

    fun deleteCard(id: String):Int{
        val db=this.writableDatabase
        return db.delete(TABLE_NAME,"ID=?",arrayOf(id))
    }
    val showData: Cursor
    get() {
        val db=this.writableDatabase
        return db.rawQuery("SELECT * FROM "+ TABLE_NAME,null)
    }

    fun resetDb(){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NAME +"'")
    }

    fun dataToString():String{
        println("There is/are ${showData.count} card(s) in the complete Pokemon Card database.")
        val res=showData
        val buffer=StringBuffer()
        while (res.moveToNext()){
            buffer.append("ID: "+res.getString(0)+"\n")
            buffer.append("Name: "+res.getString(1)+"\n")
            buffer.append("Rarity: "+res.getString(12)+"\n")
            buffer.append("HP: "+res.getString(9)+"\n")
            buffer.append("Type(s): "+res.getString(5)+"\n")
            buffer.append("Attack(s): "+res.getString(16)+"\n")
            buffer.append("Ability: "+res.getString(20)+"\n")
            buffer.append("Weakness(es): "+res.getString(17)+"\n")
            buffer.append("Resistance(s): "+res.getString(18)+"\n")
            buffer.append("Retreat Cost: "+res.getString(10)+"\n")
            buffer.append("Ancient Trait: "+res.getString(19)+"\n")
            buffer.append("Set Name: "+res.getString(14)+"\n")
            buffer.append("Set Number: "+res.getString(11)+"\n")
            buffer.append("Super Type: "+res.getString(6)+"\n")
            buffer.append("Sub Type: "+res.getString(7)+"\n")
        }
        return buffer.toString()
    }

}