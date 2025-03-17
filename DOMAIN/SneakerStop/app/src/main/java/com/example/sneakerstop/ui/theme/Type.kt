package com.example.sneakerstop.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sneakerstop.R

val Raleway = FontFamily(
    Font(R.font.raleway_regular),
    Font(R.font.raleway_bold, FontWeight.Bold),
    Font(R.font.raleway_italic, FontWeight.Normal, FontStyle.Italic)
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle( // Heading Regular 34
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp, // 34px
        letterSpacing = 0.sp,
        lineHeight = 44.sp // 44px
    ),
    titleMedium = TextStyle( // Heading Regular 26
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp, // 26px
        letterSpacing = 0.sp
    ),
    titleSmall = TextStyle( // Heading Regular 20
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp, // 20px
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle( // Body Regular 24
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
        fontSize = 24.sp, // 24px
    ),
    bodySmall = TextStyle( // Body Regular 16
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
        fontSize = 16.sp, // 16px
    )


    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val TitleLargeSmall = TextStyle(
    fontFamily = Raleway,
    fontWeight = FontWeight.Normal,
    fontSize = 32.sp, // 32px
    letterSpacing = 0.sp,
)