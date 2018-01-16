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
    data class ScreenImage(var bitmap:Bitmap) {
        val state = ScreenImageState()
        fun draw(canvas:Canvas, paint:Paint, x:Float, y:Float) {
            val w = bitmap.width.toFloat()
            val h = bitmap.height.toFloat()
            paint.strokeWidth = Math.min(w,h)/50
            paint.strokeCap = Paint.Cap.ROUND
            canvas.save()
            canvas.translate(x,y)
            canvas.save()
            val path = Path()
            val tw = (w/2)*state.scale
            path.addRect(RectF(-tw,-h/2,tw,h/2),Path.Direction.CW)
            canvas.clipPath(path)
            canvas.drawBitmap(bitmap,-w/2,-h/2,paint)
            canvas.restore()
            paint.color = Color.WHITE
            for(i in 0..1) {
                canvas.save()
                val px = w/10+(w/2-w/10)*state.scale
                val sx = px*(i*2-1)
                canvas.drawLine(sx,0f,sx-w/10,-w/10,paint)
                canvas.drawLine(sx,0f,sx-w/10,w/10,paint)
                canvas.restore()
            }
            canvas.restore()
        }
        fun update(stopcb:(Float) -> Unit) {
            state.update(stopcb)
        }
        fun startUpdating(startcb:() -> Unit) {
            state.startUpdating(startcb)
        }
    }
    data class ScreenImageState(var scale:Float = 0f,var dir:Float = 0f,var prevScale:Float = 0f) {
        fun update(stopcb:(Float)->Unit) {
            scale += 0.1f*dir
            if(Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                stopcb(scale)
            }
        }
        fun startUpdating(startcb:()->Unit) {
            dir = 1 - 2*scale
            startcb()
        }
    }
    data class ScreenImageAnimator(var view:ScreenImageView,var animated:Boolean = false) {
        fun animate(updatecb:()->Unit) {
            if(animated) {
                updatecb()
                try {
                    Thread.sleep(50)
                    view.invalidate()
                }
                catch(ex:Exception) {

                }
            }
        }
        fun start() {
            if(!animated) {
                animated = true
                view.postInvalidate()
            }
        }
        fun stop() {
            if(animated) {
                animated = false
            }
        }
    }
    data class ScreenImageRenderer(var view:ScreenImageView,var time:Int = 0) {
        val animator = ScreenImageAnimator(view)
        val screenImage = ScreenImage(view.bitmap)
        fun render(canvas:Canvas,paint:Paint) {
            val w = canvas.width.toFloat()
            val h = canvas.height.toFloat()
            canvas.drawColor(Color.parseColor("#212121"))
            screenImage.draw(canvas,paint,w/2,h/2)
            time++
            animator.animate {
                screenImage.update {
                    animator.stop()
                }
            }
        }
        fun handleTap() {
            animator.start()
        }
    }
}