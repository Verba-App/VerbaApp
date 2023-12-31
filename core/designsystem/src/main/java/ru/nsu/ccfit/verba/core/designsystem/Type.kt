package ru.nsu.ccfit.verba.core.designsystem

import androidx.compose.material.Typography
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
    h1=TextStyle(
        fontFamily = PoppinsFont,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp
    ),
    h2=TextStyle(
        fontFamily = PoppinsFont,
        fontWeight = FontWeight.Bold,
        fontSize = 35.sp
    ),
    body1 = TextStyle(
        fontFamily = PoppinsFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = PoppinsFont,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = PoppinsFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )

)
