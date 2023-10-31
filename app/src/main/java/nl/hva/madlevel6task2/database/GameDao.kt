package nl.hva.madlevel6task2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import nl.hva.madlevel6task2.model.Game

@Dao
interface GameDao {
    @Query("SELECT * from game")
    fun getGames() : LiveData<List<Game>>


    @Insert
    suspend fun insert(game : Game)

    @Insert
    suspend fun insert(game : List<Game>)

    @Query("DELETE from game")
    suspend fun deleteAll()
}