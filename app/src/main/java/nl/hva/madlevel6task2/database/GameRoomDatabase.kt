package nl.hva.madlevel6task2.database;


import android.content.Context
import androidx.room.Database;
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nl.hva.madlevel6task2.model.Game

import kotlin.jvm.Volatile;

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameRoomDatabase : RoomDatabase(){

    abstract fun gameDao():GameDao

        companion object{
            // Singleton prevents multiple instances of database opening at the
            // same time.
            private const val DATABASE_NAME="GAME_DATABASE"

            @Volatile
            private var INSTANCE:GameRoomDatabase?=null

            fun getDatabase(context: Context):GameRoomDatabase?{
                    if(INSTANCE==null){
                        synchronized(GameRoomDatabase::class.java){
                            if(INSTANCE==null){
                            INSTANCE= Room.databaseBuilder(
                            context.applicationContext,
                            GameRoomDatabase::class.java,
                            DATABASE_NAME
                            ).fallbackToDestructiveMigration()
                            .build()
                            //That means that a previous version of the database is overwritten, without migration.
                            // For our purposes that is sufficient.
                            }
                        }
                    }
            return INSTANCE
            }
        }
}
