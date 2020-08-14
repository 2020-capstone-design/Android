package com.example.project2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.net.URL

class URLtoBitmapTask() : AsyncTask<Void, Void, Bitmap>() {
    //액티비티에서 설정해줌
    lateinit var url: URL
    override fun doInBackground(vararg params: Void?): Bitmap {
        val bitmap = BitmapFactory.decodeStream(url.openStream())
        return bitmap
    }

}