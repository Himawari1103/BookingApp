package com.nquang.bookingapp.mainapp.screens.hoteldetails.components

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.nquang.bookingapp.model.FavouriteHotelModel
import com.nquang.bookingapp.model.HotelModelGet
import com.nquang.bookingapp.utils.FirebaseUtils
import com.nquang.bookingapp.utils.Utils
import com.nquang.bookingapp.viewmodel.HotelViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HotelTopBar(
    navController: NavController, modifier: Modifier = Modifier,
    hotelViewModel: HotelViewModel,
    hotelModel: HotelModelGet
) {
    var isFav by remember { mutableStateOf(false) };
    val favouriteHotelList = hotelViewModel.favouriteHotelModels.filter { it!!.userId == FirebaseAuth.getInstance().currentUser!!.uid && it.hotelId == hotelModel.id }
    if (favouriteHotelList.isNotEmpty()) {
        isFav = true
    }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
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
                    isFav = !isFav
                    if(isFav) {
                        val favouriteHotelModel = FavouriteHotelModel(
                            Utils.genUUID(),
                            FirebaseAuth.getInstance().currentUser!!.uid,
                            hotelModel.id
                        )
                        FirebaseUtils.saveFavouriteHotel(favouriteHotelModel)
                        hotelViewModel.favouriteHotelModels.add(favouriteHotelModel)
                        Toast.makeText(context, "Thêm vào danh sách yêu thích thành công", Toast.LENGTH_SHORT).show()
                    } else {
                        scope.launch {
                            FirebaseUtils.removeFavouriteHotelByUserIdAndHotelId(FirebaseAuth.getInstance().currentUser!!.uid, hotelModel.id)
                            hotelViewModel.favouriteHotelModels.removeIf { it!!.userId == FirebaseAuth.getInstance().currentUser!!.uid && it.hotelId == hotelModel.id }
                        }.onJoin
                        Toast.makeText(context, "Xóa khỏi danh sách yêu thích thành công", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.9f), CircleShape)
            ) {
                Icon(
                    if(isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = Color.Black
                )
            }
//
//            Spacer(modifier = Modifier.width(15.dp))
//
//            IconButton(
//                onClick = { },
//                modifier = Modifier
//                    .size(40.dp)
//                    .background(Color.White.copy(alpha = 0.9f), CircleShape)
//            ) {
//                Icon(
//                    Icons.Default.Share,
//                    contentDescription = "Share",
//                    tint = Color.Black
//                )
//            }
        }
    }
}
