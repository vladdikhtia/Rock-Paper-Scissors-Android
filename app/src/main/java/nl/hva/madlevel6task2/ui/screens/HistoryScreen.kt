package nl.hva.madlevel6task2.ui.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import nl.hva.madlevel6task2.R

@Composable
fun HistoryScreen(modifier : Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HistoryColumn()

        //It's not visible
//        FloatingActionButton(
//            onClick = { /*TODO*/ },
//            modifier = Modifier
//                .background(Color.LightGray)
//                .padding(16.dp)
//        ) {
//          Icon(Icons.Default.Delete, "Delete all", tint = Color.Red)
//        }

    }
}

@Composable
fun HistoryColumn() {
    LazyColumn(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(5) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Draw",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight(600),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Date and Time, Change later",
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
                            painter = painterResource(id = R.drawable.paper),
                            contentDescription = "paper",
                            modifier = Modifier
                                .background(Color.Blue)
                        )

                        Text(
                            text = "Computer",
                            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
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
                            painter = painterResource(id = R.drawable.paper),
                            contentDescription = "paper",
                            modifier = Modifier
                                .background(Color.Blue)
                        )

                        Text(
                            text = "You",
                            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
                        )
                    }
                }


                Divider(thickness = 2.dp, color = Color.LightGray)


            }
        }
    }
}
