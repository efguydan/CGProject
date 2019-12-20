package com.r4sh33d.cgproject.cube

import android.content.Context
import android.opengl.GLSurfaceView
import android.opengl.GLU
import com.r4sh33d.cgproject.cube.shapes.GradientCube
import com.r4sh33d.cgproject.cube.shapes.NormalCube
import com.r4sh33d.cgproject.cube.shapes.Shape
import com.r4sh33d.cgproject.cube.shapes.TextureCube
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class CubeRenderer(
        private val context: Context,
        private val cubeType: String,
        private val movementType: String
): GLSurfaceView.Renderer {

    private var cube: Shape = when (cubeType) {
        "Plain Colored Cube" -> NormalCube()
        "Gradient Colored Cube" -> GradientCube()
        else -> TextureCube()
    }

    var angleX = 0f
    var angleY = 0f
    var speedX = 0f
    var speedY = 0f
    var z = -6.0f

    init {
        speedX = if (movementType == "Rotating") 1.5f else 0f
        speedY = if (movementType == "Rotating") 1.5f else 0f
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        gl?.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        gl?.glClearDepthf(1.0f)
        gl?.glEnable(GL10.GL_DEPTH_TEST)
        gl?.glDepthFunc(GL10.GL_LEQUAL)
        gl?.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST)
        gl?.glShadeModel(GL10.GL_SMOOTH)
        gl?.glDisable(GL10.GL_DITHER)

        // Your OpenGL|ES initialization code here
        if (cubeType == "Textured Cube") {
            (cube as TextureCube).loadTexture(gl!!, context)    // Load image into Texture (NEW)
            gl.glEnable(GL10.GL_TEXTURE_2D)
        }
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        val aspect = width.toFloat() / height.toFloat()
        gl!!.glViewport(0, 0, width, height)
        gl.glMatrixMode(GL10.GL_PROJECTION)
        gl.glLoadIdentity()
        GLU.gluPerspective(gl, 45f, aspect, 0.1f, 100.0f)
        gl.glMatrixMode(GL10.GL_MODELVIEW)
        gl.glLoadIdentity()

        // Your OpenGL|ES display re-sizing code here
    }

    override fun onDrawFrame(gl: GL10?) {
        gl!!.glClear(GL10.GL_COLOR_BUFFER_BIT)
        gl.glClear(GL10.GL_DEPTH_BUFFER_BIT)

        // Your OpenGL|ES rendering code here
        gl.glLoadIdentity()
        gl.glTranslatef(0.0f, 0.0f, z)
        gl.glScalef(0.8f, 0.8f, 0.8f)
        gl.glRotatef(angleX, 1.0f, 0.0f, 0.0f)
        gl.glRotatef(angleY, 0.0f, 1.0f, 0.0f)
        cube.draw(gl)

        angleX += speedX
        angleY += speedY
    }

}