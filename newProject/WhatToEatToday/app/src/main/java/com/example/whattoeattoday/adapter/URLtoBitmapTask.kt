package com.example.whattoeattoday.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.View
import android.widget.ImageView
import com.example.whattoeattoday.R
import java.net.URL

class URLtoBitmapTask() : AsyncTask<Void, Void, Bitmap>() {
    //액티비티에서 설정해줌
    lateinit var url: URL
    override fun doInBackground(vararg params: Void?): Bitmap {
        val bitmap = BitmapFactory.decodeStream(url.openStream())
        return bitmap
    }

    fun imageBitmap(a: String, imageView: ImageView, isLogo: Boolean = false) {

        if (a != "noImage") {
            val imageTask = this.apply {
                url = URL(a)
            }
            val bitmap: Bitmap = imageTask.execute().get()

            imageView.setImageBitmap(bitmap)
        } else {
            println("—————— a ———————— $a")

            if (isLogo) {
                imageView.setImageResource(R.drawable.default_restarant_logo)
            } else {
                imageView.visibility = View.GONE

            }
        }
    }

}