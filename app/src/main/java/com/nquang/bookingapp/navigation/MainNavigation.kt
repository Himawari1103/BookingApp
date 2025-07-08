package com.nquang.bookingapp.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.nquang.bookingapp.MainActivity
import com.nquang.bookingapp.login.viewmodel.LoginState
import com.nquang.bookingapp.mainapp.screens.account.AccountScreen
import com.nquang.bookingapp.mainapp.screens.bookingdetail.BookingDetailScreen
import com.nquang.bookingapp.mainapp.screens.bookings.BookingsScreen
import com.nquang.bookingapp.mainapp.screens.home.HomeScreen
import com.nquang.bookingapp.mainapp.screens.hoteldetail.HotelDetailScreen
import com.nquang.bookingapp.mainapp.screens.hotellist.HotelListScreen
import com.nquang.bookingapp.mainapp.screens.payment.PaymentScreen
import com.nquang.bookingapp.mainapp.screens.payment.PaymentMethodScreen
import com.nquang.bookingapp.mainapp.screens.reviews.ReviewsScreen
import com.nquang.bookingapp.mainapp.screens.roomdetail.RoomDetailScreen
import com.nquang.bookingapp.mainapp.screens.roomlist.RoomListScreen
import com.nquang.bookingapp.mainapp.screens.profile.ProfileScreen
import com.nquang.bookingapp.mainapp.screens.transportation.TransportationScreen
import com.nquang.bookingapp.mainapp.screens.busdetail.BusDetailScreen
import com.nquang.bookingapp.mainapp.screens.tourism.TourismScreen
import com.nquang.bookingapp.mainapp.screens.tourismdetail.TourismDetailScreen
import com.nquang.bookingapp.mainapp.viewmodel.AccountViewModel
import com.nquang.bookingapp.utils.GoogleSignInUtils
import com.nquang.bookingapp.viewmodel.HotelViewModel
import com.nquang.bookingapp.viewmodel.UserViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
    accountViewModel: AccountViewModel = viewModel(),
    userViewModel: UserViewModel,
    hotelViewModel: HotelViewModel,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context: Context = LocalContext.current
    val scope = rememberCoroutineScope()
    accountViewModel.userModel.value = userViewModel.userModel.value

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.fillMaxSize()
    ) {
        // Màn hình chính
        composable("home") {
            HomeScreen(
                navController = navController,
                hotelViewModel = hotelViewModel
            )
        }

        composable("bookings") {
            BookingsScreen(navController = navController)
        }

        composable("account") {
            fun onLogout() {
                Log.d("MainNavigation", "onLogout called")
                FirebaseAuth.getInstance().signOut()
                try {
                    GoogleSignInUtils.doGoogleSignOut(context, scope)
                    context.startActivity(Intent(context, MainActivity::class.java))
                    (context as? Activity)?.finish()
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        "Google logout failed: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("MainNavigation", "Google logout failed: ${e.message}", e)
                }
            }
            AccountScreen(
                navController = navController,
                onLogout = { onLogout() },
                userViewModel = userViewModel
            )
        }

        // Màn hình danh sách khách sạn
        composable("hotel_list") {
            HotelListScreen(navController = navController)
        }

        // Màn hình chi tiết khách sạn
        composable("hotel_detail/{hotelId}") { backStackEntry ->
            val hotelId = backStackEntry.arguments?.getString("hotelId") ?: ""
            HotelDetailScreen(
                navController = navController,
                hotelId = hotelId,
                hotelViewModel = hotelViewModel
            )
        }

        // Màn hình danh sách phòng
        composable("room_list/{hotelId}") { backStackEntry ->
            val hotelId = backStackEntry.arguments?.getString("hotelId") ?: ""
            RoomListScreen(
                navController = navController,
                hotelId = hotelId,
                hotelViewModel = hotelViewModel
            )
        }

        // Màn hình chi tiết phòng
        composable("room_detail/{roomId}") { backStackEntry ->
            val roomId = backStackEntry.arguments?.getString("roomId") ?: ""
            RoomDetailScreen(
                navController = navController,
                roomId = roomId
            )
        }

        // Màn hình thanh toán
        composable("payment/{roomId}") { backStackEntry ->
            val roomId = backStackEntry.arguments?.getString("roomId") ?: ""
            PaymentScreen(
                navController = navController,
                roomId = roomId
            )
        }

        // Màn hình phương thức thanh toán
        composable("payment_method") {
            PaymentMethodScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onPaymentMethodSelected = { paymentMethod ->
                    // Xử lý khi chọn phương thức thanh toán
                    navController.popBackStack()
                }
            )
        }

        // Màn hình chi tiết booking
        composable("booking_detail/{bookingId}") { backStackEntry ->
            val bookingId = backStackEntry.arguments?.getString("bookingId") ?: ""
            BookingDetailScreen(
                navController = navController,
                bookingId = bookingId
            )
        }

        // Màn hình đánh giá
        composable("reviews/{hotelId}") { backStackEntry ->
            val hotelId = backStackEntry.arguments?.getString("hotelId") ?: ""
            ReviewsScreen(
                navController = navController,
                hotelId = hotelId
            )
        }

        // Màn hình profile
        composable("profile") {
            ProfileScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                userViewModel = userViewModel
            )
        }

        composable("transportation") {
            TransportationScreen(navController = navController)
        }

        // Màn hình chi tiết xe khách
        composable("bus_detail/{busId}") { backStackEntry ->
            val busId = backStackEntry.arguments?.getString("busId") ?: ""
            BusDetailScreen(
                navController = navController,
                serviceId = busId
            )
        }

        // Màn hình du lịch
        composable("tourism") {
            TourismScreen(navController = navController)
        }

        // Màn hình chi tiết du lịch
        composable("tourism_detail/{destination}") { backStackEntry ->
            val destination = backStackEntry.arguments?.getString("destination") ?: ""
            TourismDetailScreen(
                navController = navController,
                destination = destination
            )
        }
    }


}
