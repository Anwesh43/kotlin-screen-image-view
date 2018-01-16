package ui.anwesome.com.kotlinscreenimageview

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ui.anwesome.com.screenimageview.ScreenImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ScreenImageView.create(this,BitmapFactory.decodeResource(resources,R.drawable.nature))
    }
}
