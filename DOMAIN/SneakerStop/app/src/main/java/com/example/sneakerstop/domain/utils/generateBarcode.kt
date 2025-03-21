package com.example.sneakerstop.domain.utils

import android.graphics.Bitmap
import android.graphics.Color
import com.example.sneakerstop.ui.theme.Background
import com.example.sneakerstop.ui.theme.Text
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix

fun generateBarcode(uuid: String, width: Int = 300, height: Int = 50): Bitmap? {
    return try {
        val writer = MultiFormatWriter()
        val bitMatrix: BitMatrix = writer.encode(
            uuid,
            BarcodeFormat.CODE_128,
            width,
            height
        )

        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            for (x in 0 until width) {
                pixels[y * width + x] = if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
            }
        }

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        bitmap
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}