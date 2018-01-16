package ui.anwesome.com.screenimageview

/**
 * Created by anweshmishra on 17/01/18.
 */
import android.graphics.*
import android.content.*
import android.view.*
class ScreenImageView(ctx:Context,var bitmap:Bitmap):View(ctx) {
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
    data class ScreenImage(var bitmap:Bitmap,var x:Float,var y:Float) {
        fun draw(canvas:Canvas,paint:Paint) {
            val w = bitmap.width.toFloat()
            val h = bitmap.height.toFloat()
            paint.strokeWidth = Math.min(w,h)/50
            paint.strokeCap = Paint.Cap.ROUND
            canvas.save()
            canvas.translate(x,y)
            canvas.save()
            val path = Path()
            path.addRect(RectF(-w/2,-h/2,w/2,h/2),Path.Direction.CW)
            canvas.clipPath(path)
            canvas.drawBitmap(bitmap,-w/2,-h/2,paint)
            canvas.restore()
            paint.color = Color.WHITE
            for(i in 0..1) {
                canvas.save()
                val px = w/10+(w/2-w/10)
                val sx = px*(i*2-1)
                canvas.drawLine(sx,0f,sx-w/10,-w/10,paint)
                canvas.drawLine(sx,0f,sx-w/10,w/10,paint)
                canvas.restore()
            }
            canvas.restore()
        }
        fun update(stopcb:(Float) -> Unit) {

        }
        fun startUpdating(startcb:() -> Unit) {

        }
    }
}