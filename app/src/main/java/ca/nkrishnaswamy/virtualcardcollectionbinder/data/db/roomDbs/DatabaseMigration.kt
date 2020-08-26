package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.roomDbs

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2: Migration = object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE UNIQUE INDEX index_pokemonCardsTable_card_setName_card_num ON pokemonCardsTable (card_setName, card_num)")
    }

}