package com.nquang.bookingapp.mainapp.screens.hoteldetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.nquang.bookingapp.model.FavouriteHotelModel
import com.nquang.bookingapp.model.HotelModelGet
import com.nquang.bookingapp.utils.FirebaseUtils
import com.nquang.bookingapp.utils.Utils
import com.nquang.bookingapp.viewmodel.HotelViewModel

@Composable
fun HotelTopBar(
    navController: NavController, modifier: Modifier = Modifier,
    hotelViewModel: HotelViewModel,
    hotelModel: HotelModelGet
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .size(40.dp)
                .background(Color.White.copy(alpha = 0.9f), CircleShape)
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }

        Row {
            IconButton(
                onClick = {
                    val favouriteHotelModel = FavouriteHotelModel(
                        Utils.genUUID(),
                        FirebaseAuth.getInstance().currentUser!!.uid,
                        hotelModel.id
                    )

                    FirebaseUtils.saveFavouriteHotel(favouriteHotelModel)
                },
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.9f), CircleShape)
            ) {
                Icon(
                    Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(15.dp))

            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.9f), CircleShape)
            ) {
                Icon(
                    Icons.Default.Share,
                    contentDescription = "Share",
                    tint = Color.Black
                )
            }
        }
    }
}
