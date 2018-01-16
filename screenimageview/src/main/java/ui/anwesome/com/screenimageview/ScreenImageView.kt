package ui.anwesome.com.screenimageview

/**
 * Created by anweshmishra on 17/01/18.
 */
import android.graphics.*
import android.content.*
import android.view.*
class ScreenImageView(ctx:Context):View(ctx) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas:Canvas) {

    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}