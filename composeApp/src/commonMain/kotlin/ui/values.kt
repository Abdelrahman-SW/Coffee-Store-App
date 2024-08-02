package ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import coffeeapp.composeapp.generated.resources.Res
import coffeeapp.composeapp.generated.resources.poppins_bold
import coffeeapp.composeapp.generated.resources.poppins_regular
import org.jetbrains.compose.resources.Font


val primaryColor =  Color(0xFFce6214)
val primaryColorAlpha =  Color(0x20ce6214)

@Composable
fun GetPoppinsFamily() = FontFamily(
    Font(Res.font.poppins_regular , FontWeight.Normal),
    Font(Res.font.poppins_bold, FontWeight.Bold)
)