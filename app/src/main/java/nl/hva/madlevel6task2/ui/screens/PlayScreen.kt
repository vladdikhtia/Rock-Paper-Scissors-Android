package nl.hva.madlevel6task2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.hva.madlevel6task2.R
import java.time.format.TextStyle

@Composable
fun PlayScreen(modifier : Modifier, navController : NavController) {
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
            style = MaterialTheme.typography.overline,
            fontWeight = FontWeight(500),
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )
        Text(
            text = "WIN: 1 DRAW: 1 LOSE: 1"
        )
        Text(
            text = "You win, change later",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 150.dp, bottom = 48.dp),

            )
            {
              Text(text = "Computer")
                
              Text(
                  text = "V.S.",
                  style = MaterialTheme.typography.h4,
                  fontStyle = FontStyle.Italic,
                  fontWeight = FontWeight.Bold
                  )
                
              Text(text = "You")
            }
        
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ){
                Card {
                    Image(painter = painterResource(R.drawable.rock), contentDescription = "Rock")
                }
                Card {
                    Image(painter = painterResource(id = R.drawable.paper), contentDescription = "Paper")
                }
                Card {
                    Image(painter = painterResource(id = R.drawable.scissors), contentDescription = "Scissors",
                        modifier = Modifier.clickable { navController.navigate(GameScreens.HistoryScreen.route) })
                }
            }


    }


}