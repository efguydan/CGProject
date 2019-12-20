package com.r4sh33d.cgproject.cube

import android.content.Context
import android.opengl.GLSurfaceView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager

class CubeActivity : AppCompatActivity() {

    companion object {
        const val CUBE_TYPE = "cube_type"
        const val MOVEMENT_TYPE = "movement_type"
    }

    private lateinit var glView: GLSurfaceView
    private var cubeType = ""
    private var movementType = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cubeType = intent.getStringExtra(CUBE_TYPE)
        movementType = intent.getStringExtra(MOVEMENT_TYPE)

        glView = CubeSurfaceView(this, cubeType, movementType)
        setContentView(glView)

        if (movementType == "Key Controlled Cube") {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0)
        }
    }

    override fun onPause() {
        super.onPause()
        glView.onPause()
    }

    override fun onResume() {
        super.onResume()
        glView.onResume()
    }
}
