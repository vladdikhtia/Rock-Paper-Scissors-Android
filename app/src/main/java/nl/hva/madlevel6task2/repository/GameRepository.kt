package nl.hva.madlevel6task2.repository

import android.content.Context
import nl.hva.madlevel6task2.database.GameDao
import nl.hva.madlevel6task2.model.Game

class GameRepository(context : Context) {

    private val gameDao : GameDao

    init {
        val database = GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    fun getGames() = gameDao.getGames()

    suspend fun insert(game : Game) = gameDao.insert(game)

    suspend fun deleteAll() = gameDao.deleteAll()

}