package nl.hva.madlevel6task2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "game")
data class Game(
    @ColumnInfo(name = "result")
    var result : String,

    @ColumnInfo(name = "gameDate")
    var gameDate : Date,

    @ColumnInfo(name = "computerChoice")
    var computerChoice : Int,

    @ColumnInfo(name = "userChoice")
    var userChoice : Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long = 0
)

