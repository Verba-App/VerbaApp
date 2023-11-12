package ru.nsu.ccfit.verba.core.designsystem


import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val PoppinsFont=
    FontFamily(
        Font(R.font.poppins_regular, weight = FontWeight.Normal),
        Font(R.font.poppins_bold, weight = FontWeight.Bold)
    )


// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge =TextStyle(
        fontFamily = PoppinsFont,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp
    ),
    titleMedium = TextStyle(
        fontFamily = PoppinsFont,
        fontWeight = FontWeight.Bold,
        fontSize = 35.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = PoppinsFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = PoppinsFont,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = PoppinsFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )

)