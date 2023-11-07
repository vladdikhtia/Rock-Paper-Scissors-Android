package nl.hva.madlevel6task2.ui.screens

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.hva.madlevel6task2.viewModel.GameViewModel

@Composable
fun HistoryScreen(modifier : Modifier, viewModel : GameViewModel) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val gameHistory = viewModel.gameHistory.observeAsState().value ?: emptyList()

        LazyColumn(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                gameHistory.size
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = gameHistory[it].result,
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight(600),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Text(
                        text = gameHistory[it].gameDate.toString(),
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = gameHistory[it].computerChoice),
                                contentDescription = "paper",
                                modifier = Modifier
                                    .background(Color.Blue)
                            )

                            Text(
                                text = "Computer",
                                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                                style = MaterialTheme.typography.h6
                            )
                        }

                        Text(
                            text = "V.S.",
                            style = MaterialTheme.typography.h6,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold
                        )
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = gameHistory[it].userChoice),
                                contentDescription = "paper",
                                modifier = Modifier
                                    .background(Color.Blue)
                            )

                            Text(
                                text = "You",
                                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                                style = MaterialTheme.typography.h6
                            )
                        }
                    }
                    Divider(thickness = 2.dp, color = Color.LightGray)
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewHistoryScreen() {
    HistoryScreen(modifier = Modifier, viewModel = GameViewModel(application = Application()))
}
