package com.nickwlaw.pokedex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import coil.compose.rememberImagePainter
import com.nickwlaw.pokedex.R
import com.nickwlaw.pokedex.data.models.domain.Sprite
import com.nickwlaw.pokedex.viewmodel.PokemonListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalFoundationApi
class PokemonListFragment : Fragment() {
    private val viewModel: PokemonListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val pokemon by viewModel.pokemonList.collectAsState(listOf())
            val lazyGridState = rememberLazyListState()
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                state = lazyGridState
            ) {
                items(pokemon) { mon ->
                    Card(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .padding(4.dp),
                        shape = RoundedCornerShape(12.dp),
                        elevation = 6.dp,
                        backgroundColor = colorResource(R.color.purple_200)
                    ) {
                        Column {
                            var displayUrl by remember { mutableStateOf(mon.sprite.displayUrl) }
                            Image(
                                painter = rememberImagePainter(
                                    data = displayUrl
                                ),
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .height(200.dp)
                                    .clickable {
                                        displayUrl = mon.sprite.swapDisplayUrl()
                                    },
                                contentDescription = mon.name
                            )
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
            }
        }
    }

    private fun Sprite.swapDisplayUrl(): String {
        displayUrl = when (displayUrl) {
            frontUrl -> backUrl
            else -> frontUrl
        }
        return displayUrl
    }
}
