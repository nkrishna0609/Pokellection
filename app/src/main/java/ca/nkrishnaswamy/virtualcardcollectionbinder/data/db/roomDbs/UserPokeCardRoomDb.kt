package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.roomDbs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.DAOs.UserCardsDAO
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.typeConverters.*
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCard

@Database(entities=arrayOf(PokemonCard::class), version=1)
@TypeConverters(StringArrayListTypeConverters::class, PokemonAttacksArrayListTypeConverters::class, PokemonResistancesArrayListTypeConverters::class, PokemonWeaknessesArrayListTypeConverters::class,
    PokemonAbilityTypeConverter::class,PokemonAncientTraitTypeConverter::class)
abstract class UserPokeCardRoomDb : RoomDatabase(){

    abstract fun localPokeCardDao(): UserCardsDAO

    companion object{
        @Volatile
        private var INSTANCE: UserPokeCardRoomDb? = null

        fun getInstance(context: Context): UserPokeCardRoomDb {
            synchronized(this) {
                var instance=
                    INSTANCE

                if (instance == null){
                    instance= Room.databaseBuilder(context.applicationContext, UserPokeCardRoomDb::class.java, "userCardsDb").build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}