package com.example.movieapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.movieapp.R

@Composable
fun FloatingActionButtonComponent(
    onClick: () -> Unit,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = modifier,
    ) {
        if (isFavorite) {
            Icon(Icons.Default.Clear, contentDescription = stringResource(R.string.remove))

        } else {
            Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add))
        }
    }
}