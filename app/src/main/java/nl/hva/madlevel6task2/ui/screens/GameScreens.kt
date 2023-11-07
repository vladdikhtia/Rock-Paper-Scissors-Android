package nl.hva.madlevel6task2.ui.screens

import androidx.annotation.StringRes
import nl.hva.madlevel6task2.R

sealed class GameScreens(val route : String, @StringRes val resourceId : Int) {
    object PlayScreen : GameScreens("play", R.string.playres)
    object HistoryScreen : GameScreens("history", R.string.historyres)
}
