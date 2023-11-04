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

    // Query to get the number of wins
    @Query("SELECT COUNT(*) FROM game WHERE result = 'win'")
    suspend fun getWinsCount(): Int

    // Query to get the number of draws
    @Query("SELECT COUNT(*) FROM game WHERE result = 'draw'")
    suspend fun getDrawsCount(): Int

    // Query to get the number of losses
    @Query("SELECT COUNT(*) FROM game WHERE result = 'loss'")
    suspend fun getLossesCount(): Int

    @Query("DELETE from game")
    suspend fun deleteAll()
}