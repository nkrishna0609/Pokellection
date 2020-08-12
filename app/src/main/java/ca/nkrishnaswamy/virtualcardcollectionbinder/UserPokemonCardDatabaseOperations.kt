package ca.nkrishnaswamy.virtualcardcollectionbinder

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserPokemonCardDatabaseOperations(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

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
                "POKEMONID TEXT,NATIONALPOKEDEXNUMBER INTEGER,IMAGEURLHIRES TEXT,TYPES TEXT,SUPERTYPE TEXT," +
                "SUBTYPE TEXT,EVOLVESFROM TEXT,HP TEXT,RETREATCOST INTEGER," +
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
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NAME+"'")
    }

}