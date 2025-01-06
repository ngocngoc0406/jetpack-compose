package com.mkrdeveloper.lazyrowonclickexamplejetpack
// chữ chi tiết bên ngoài
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType  
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mkrdeveloper.lazyrowonclickexamplejetpack.screens.DetailScreen
import com.mkrdeveloper.lazyrowonclickexamplejetpack.screens.MainScreen
import com.mkrdeveloper.lazyrowonclickexamplejetpack.ui.theme.LazyRowOnClickExampleJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyRowOnClickExampleJetpackTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Red
                ) {
                    val imageId = arrayOf(
                        R.drawable.sv1,
                        R.drawable.sv2,
                        R.drawable.sv3,
                        R.drawable.sv4,
                        R.drawable.sv5,
                        R.drawable.sv6
                        // m thay ảnh nãy gờ vô cỗ đây nè
                        //ảnh tên gìê, thay vô
                    )

                    val names = arrayOf(
                        "Nguyễn Hữu a",
                        "Nguyễn Văn b",
                        "Nguyễn Thị Ngọc",
                        "Trần Mạnh d",
                        "Phạm Thành e",
                        "Đỗ Hoàng f"
                    )

                    val sinh = arrayOf(
                        "2009  ",
                        "2004 ",
                        "2004 ",
                        "2006  ",
                        "2004"  ,
                        "2005  ",
                    )


                    val thongtin = arrayOf(
                        "vku",
                        "vku",
                        "vku",
                        "vku",
                        "vku",
                        "vku",
                    )

                    

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "MainScreen") {
                        composable(route = "MainScreen") {
                            MainScreen(imageId, names, sinh, thongtin, navController)
                        }
                        composable(route = "DetailScreen/{index}",
                            arguments = listOf(
                                navArgument(name = "index") {
                                    type = NavType.IntType
                                }
                            )
                        ) { index->
                            DetailScreen(
                                photos = imageId,
                                names = names,
                                sinh = sinh,
                                thongtin = thongtin,
                                itemIndex = index.arguments?.getInt("index")
                            )
                        }
                    }

                }
            }
        }
    }
}

