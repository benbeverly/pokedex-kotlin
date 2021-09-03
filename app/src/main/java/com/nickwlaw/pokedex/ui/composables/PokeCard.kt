package com.nickwlaw.pokedex.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.nickwlaw.pokedex.R
import com.nickwlaw.pokedex.data.models.domain.Pokemon

@ExperimentalCoilApi
@Composable
fun PokeCard(mon: Pokemon) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 6.dp,
        backgroundColor = colorResource(R.color.purple_200)
    ) {
        Column {
            PokeImage(mon)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = mon.id.toString(),
                    modifier = Modifier.padding(start = 12.dp),
                    color = Color.White,
                    fontSize = 20.sp
                )
                Text(
                    text = mon.name,
                    modifier = Modifier.padding(end = 12.dp),
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }
    }
}
