package nl.hva.madlevel6task2.ui.screens

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import nl.hva.madlevel6task2.R
import nl.hva.madlevel6task2.model.Game
import nl.hva.madlevel6task2.viewModel.GameViewModel
import java.util.Date


@Composable
fun PlayScreen(modifier : Modifier, navController : NavController, viewModel : GameViewModel) {
    // Create a LiveData to observe the selected image
    val userSelectedImage = remember { mutableStateOf(viewModel.userSelectedImage) }

    val computerSelectedImage = remember { mutableStateOf(viewModel.computerSelectedImage) }
    val result = remember {
        mutableStateOf(String())
    }
    val wins by viewModel.wins.observeAsState()
    val draws by viewModel.draws.observeAsState()
    val losses by viewModel.losses.observeAsState()



    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
        )
        Text(
            text = stringResource(R.string.instructions),
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight(400),
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.statistics),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight(500),
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )
        Text(
            text = "WIN: $wins DRAW: $draws LOSE: $losses"
        )
        Column(modifier = Modifier.height(60.dp)) {
            if (!result.value.isNullOrEmpty()) {
                Text(
                    text = viewModel.checkWinner(
                        userSelectedImage.value,
                        computerSelectedImage.value
                    ),
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(40.dp)
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 16.dp)
                .height(155.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                computerSelectedImage.value?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = "Computer random image",
                        modifier = Modifier
                            .size(130.dp)
                            .padding(top = 16.dp)
                    )

                }
                Text(text = "Computer", style = MaterialTheme.typography.h6)
            }

            Text(
                text = "V.S.",
                style = MaterialTheme.typography.h4,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )


            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                userSelectedImage.value?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = "Selected Image",
                        modifier = Modifier
                            .size(130.dp)
                            .padding(top = 16.dp)

                    )
                }
                Text(text = "You", style = MaterialTheme.typography.h6)
            }
        }




        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 44.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            Card {
                Image(
                    painter = painterResource(id = R.drawable.rock),
                    contentDescription = "Rock",
                    modifier = Modifier
                        .rotate(-90F)
                        .clickable {
                            userSelectedImage.value = R.drawable.rock
                            computerSelectedImage.value = viewModel.getRandomImage()
                            viewModel.getDateAndTime()

                            result.value = viewModel.checkWinner(
                                userSelectedImage.value,
                                computerSelectedImage.value
                            )
                            val newGame = verifyInputAndCorrect(viewModel.getDateAndTime(),
                                computerSelectedImage.value!!, userSelectedImage.value!!
                            )
                            // TODO: insert the new game into the database!
                            viewModel.insertGame(newGame)
                        }
                )
            }
            Card {
                Image(
                    painter = painterResource(id = R.drawable.paper),
                    contentDescription = "Paper",
                    modifier = Modifier
                        .rotate(-90F)
                        .clickable {
                            userSelectedImage.value = R.drawable.paper
                            computerSelectedImage.value = viewModel.getRandomImage()
                            viewModel.getDateAndTime()
                            result.value = viewModel.checkWinner(
                                userSelectedImage.value,
                                computerSelectedImage.value
                            )
                            val newGame = verifyInputAndCorrect(viewModel.getDateAndTime(),
                                computerSelectedImage.value!!, userSelectedImage.value!!
                            )
                            // TODO: insert the new game into the database!
                            viewModel.insertGame(newGame)
                        }
                )
            }
            Card {
                Image(painter = painterResource(id = R.drawable.scissors),
                    contentDescription = "Scissors",
                    modifier = Modifier
                        .rotate(-90F)
                        .clickable {
                            userSelectedImage.value = R.drawable.scissors
                            computerSelectedImage.value = viewModel.getRandomImage()
                            viewModel.getDateAndTime()
                            result.value = viewModel.checkWinner(
                                userSelectedImage.value,
                                computerSelectedImage.value
                            )
                            val newGame = verifyInputAndCorrect(viewModel.getDateAndTime(),
                                computerSelectedImage.value!!, userSelectedImage.value!!
                            )
                                // TODO: insert the new game into the database!
                                viewModel.insertGame(newGame)
                        }

                )
            }
        }
    }
}
fun verifyInputAndCorrect(
    gameDate: Date,
    computerChoice: Int,
    userChoice: Int
): Game {
    val result: String

    if (userChoice == computerChoice) {
        result = "Draw"
    } else if (
        (userChoice == R.drawable.rock && computerChoice == R.drawable.scissors) ||
        (userChoice == R.drawable.paper && computerChoice == R.drawable.rock) ||
        (userChoice == R.drawable.scissors && computerChoice == R.drawable.paper)
    ) {
        result = "You win!"
    } else {
        result = "Computer wins!"
    }

    // Create a Game object with the calculated result, game date, and choices
    return Game(result, gameDate, computerChoice, userChoice)
}



//gameTitle = title
//gamePlatform = platform
//resultingGame = Game(gameTitle, gamePlatform, gameReleaseDate)
//return resultingGame
@Preview
@Composable
fun PreviewPlayScreen() {
    PlayScreen(
        modifier = Modifier,
        navController = rememberNavController(),
        viewModel = GameViewModel(Application())
    )
}