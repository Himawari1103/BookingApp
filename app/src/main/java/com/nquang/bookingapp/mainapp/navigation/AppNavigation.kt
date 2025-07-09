package com.example.mainapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mainapp.screens.account.AccountScreen
import com.example.mainapp.screens.bookingdetail.BookingDetailScreen
import com.example.mainapp.screens.bookings.BookingsScreen
import com.example.mainapp.screens.home.HomeScreen
import com.example.mainapp.screens.home.components.BottomNavigationComponent
import com.example.mainapp.screens.hoteldetail.HotelDetailScreen
import com.example.mainapp.screens.hotellist.HotelListScreen
import com.example.mainapp.screens.favorites.FavoritesScreen
import com.example.mainapp.screens.payment.PaymentScreen
import com.example.mainapp.screens.payment.PaymentMethodScreen
import com.example.mainapp.screens.reviews.ReviewsScreen
import com.example.mainapp.screens.roomdetail.RoomDetailScreen
import com.example.mainapp.screens.roomlist.RoomListScreen
import com.example.mainapp.screens.profile.ProfileScreen
import com.example.mainapp.screens.transportation.TransportationScreen
import com.example.mainapp.screens.busdetail.BusDetailScreen
import com.example.mainapp.screens.tourism.TourismScreen
import com.example.mainapp.screens.tourismdetail.TourismDetailScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.fillMaxSize()
    ) {
        // Màn hình chính
        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("bookings") {
            BookingsScreen(navController = navController)
        }

        composable("account") {
            AccountScreen(navController = navController)
        }

        // Màn hình yêu thích
        composable("favorites") {
            FavoritesScreen(navController = navController)
        }

        // Màn hình danh sách khách sạn
        composable("hotel_list/{destination}") { backStackEntry ->
            val destination = backStackEntry.arguments?.getString("destination") ?: "Việt Nam"
            HotelListScreen(
                navController = navController,
                destination = destination
            )
        }

        // Màn hình chi tiết khách sạn
        composable("hotel_detail/{hotelId}") { backStackEntry ->
            val hotelId = backStackEntry.arguments?.getString("hotelId") ?: ""
            HotelDetailScreen(
                navController = navController,
                hotelId = hotelId
            )
        }

        // Màn hình danh sách phòng
        composable("room_list/{hotelId}") { backStackEntry ->
            val hotelId = backStackEntry.arguments?.getString("hotelId") ?: ""
            RoomListScreen(
                navController = navController,
                hotelId = hotelId
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
                }
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
