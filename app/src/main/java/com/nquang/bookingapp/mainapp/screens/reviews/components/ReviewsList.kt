package com.nquang.bookingapp.mainapp.screens.reviews.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nquang.bookingapp.mainapp.data.model.reviews.ReviewData

@Composable
fun ReviewsList(reviews: List<ReviewData>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        reviews.forEach { review ->
            ReviewCard(review = review)
        }
    }
}
