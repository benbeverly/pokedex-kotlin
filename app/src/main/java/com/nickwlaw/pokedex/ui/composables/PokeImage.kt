package com.nickwlaw.pokedex.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.nickwlaw.pokedex.data.models.domain.Pokemon
import com.nickwlaw.pokedex.data.models.domain.Sprite

@ExperimentalCoilApi
@Composable
fun PokeImage(mon: Pokemon) {
    var displayUrl by remember { mutableStateOf(mon.sprite.displayUrl) }
    Image(
        painter = rememberImagePainter(
            data = displayUrl
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable {
                displayUrl = mon.sprite.swapDisplayUrl()
            },
        contentDescription = mon.name
    )
}

fun Sprite.swapDisplayUrl(): String {
    displayUrl = when (displayUrl) {
        frontUrl -> backUrl
        else -> frontUrl
    }
    return displayUrl
}
