package nl.hva.madlevel6task2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.hva.madlevel6task2.R
import nl.hva.madlevel6task2.model.Game
import nl.hva.madlevel6task2.repository.GameRepository
import java.util.Calendar
import java.util.Date

class GameViewModel(application : Application) : AndroidViewModel(application) {

    private val gameRepository = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    var wins = gameRepository.getWinsCount()
    var draws = gameRepository.getDrawsCount()
    var losses = gameRepository.getLossesCount()

    val gameHistory =
        gameRepository.getGames() // LiveData object exposes the games from the room database.

    /**
     * Insert a game into the repository.
     */

    fun insertGame(game : Game) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.insert(game)
            }
        }
    }

    /**
     * Delete game history from the repository.
     */

    fun deleteAll() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteAll()
            }
        }
    }

    private fun getRandomNumber() : Int {
        return (1..3).random()
    }

    private fun valueToImageId(imageValue : Int) : Int {
        val imageList = arrayOf(
            R.drawable.rock,
            R.drawable.paper,
            R.drawable.scissors
        )
        return imageList[imageValue - 1]
    }

    //for PlayScreen
    fun getRandomImage() : Int {
        return valueToImageId(getRandomNumber())
    }

    //date and time for HistoryScreen
    fun getDateAndTime() : Date {
        return Calendar.getInstance().time
    }

    //game logic
    fun checkWinner(userSelectedImage : Int?, computerSelectedImage : Int?) : String {
        val result : String
        if (userSelectedImage == computerSelectedImage) {
            result = "Draw"
        } else if ((userSelectedImage == R.drawable.rock && computerSelectedImage == R.drawable.scissors) ||
            (userSelectedImage == R.drawable.paper && computerSelectedImage == R.drawable.rock) ||
            (userSelectedImage == R.drawable.scissors && computerSelectedImage == R.drawable.paper)
        ) {
            result = "You win!"
        } else {
            result = "Computer wins!"
        }
        return result
    }


    var userSelectedImage : Int? = null
        private set
    var computerSelectedImage : Int? = null
        private set


}

//Dispatchers.Main: Main thread on Android, interact with the UI and perform light work.
//Dispatchers.IO: Optimized for disk and network IO.