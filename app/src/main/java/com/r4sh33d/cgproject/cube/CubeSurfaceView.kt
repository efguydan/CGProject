package com.r4sh33d.cgproject.cube

import android.content.Context
import android.opengl.GLSurfaceView
import android.view.KeyEvent
import android.view.MotionEvent

class CubeSurfaceView(
        context: Context,
        cubeType: String,
        movementType: String
): GLSurfaceView(context) {

    private var renderer: CubeRenderer = CubeRenderer(context, cubeType, movementType)

    private val TOUCH_SCALE_FACTOR = 180.0f / 320.0f
    private var previousX: Float = 0.0f
    private var previousY: Float = 0.0f

    init {
        setRenderer(renderer)
        requestFocus()
        isFocusableInTouchMode = true
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode) {
            KeyEvent.KEYCODE_I -> {
                renderer.speedY -= 0.1f
                return true
            }
            KeyEvent.KEYCODE_P -> {
                renderer.speedY += 0.1f
                return true
            }
            KeyEvent.KEYCODE_O -> {
                renderer.speedX -= 0.1f
                return true
            }
            KeyEvent.KEYCODE_K -> {
                renderer.speedX += 0.1f
                return true
            }
            KeyEvent.KEYCODE_A -> {
                renderer.z -= 0.2f
                return true
            }
            KeyEvent.KEYCODE_Z -> {
                renderer.z += 0.2f
                return true
            }
        }
        return false
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val currentX = event!!.x
        val currentY = event.y
        var deltaX = 0.0f
        var deltaY = 0.0f
        when(event?.action) {
            MotionEvent.ACTION_MOVE -> {
                deltaX = currentX - previousX
                deltaY = currentY - previousY
                renderer.angleX += deltaY * TOUCH_SCALE_FACTOR
                renderer.angleY += deltaX * TOUCH_SCALE_FACTOR
            }
        }
        previousX = currentX
        previousY = currentY
        return true
    }

}