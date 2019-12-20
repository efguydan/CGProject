package com.r4sh33d.cgproject.cube.shapes

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10


class GradientCube: Shape {

    private val vertexBuffer: FloatBuffer
    private var colorBuffer: FloatBuffer
    private val numFaces = 6

    val colors = floatArrayOf(
        1.0f, 0.0f, 0.0f, 1.0f,   // 4. red
        0.0f, 0.0f, 1.0f, 1.0f,  // 0. blue
        0.0f, 1.0f, 0.0f, 1.0f,  // 1. green
        0.0f, 0.0f, 1.0f, 1.0f,  // 2. blue
        1.0f, 0.0f, 0.0f, 1.0f,   // 4. red
        0.0f, 0.0f, 1.0f, 1.0f,  // 0. blue
        0.0f, 1.0f, 0.0f, 1.0f,  // 1. green
        0.0f, 0.0f, 1.0f, 1.0f,  // 2. blue
        0.0f, 1.0f, 0.0f, 1.0f,  // 3. green
        1.0f, 0.0f, 0.0f, 1.0f,   // 4. red
        0.0f, 0.0f, 1.0f, 1.0f,  // 0. blue
        0.0f, 1.0f, 0.0f, 1.0f,  // 1. green
        0.0f, 0.0f, 1.0f, 1.0f,  // 2. blue
        0.0f, 1.0f, 0.0f, 1.0f,  // 3. green
        1.0f, 0.0f, 0.0f, 1.0f,   // 4. red
        0.0f, 0.0f, 1.0f, 1.0f,  // 0. blue
        0.0f, 1.0f, 0.0f, 1.0f,  // 1. green
        0.0f, 0.0f, 1.0f, 1.0f,  // 2. blue
        0.0f, 1.0f, 0.0f, 1.0f,  // 3. green
        1.0f, 0.0f, 0.0f, 1.0f,   // 4. red
        0.0f, 0.0f, 1.0f, 1.0f,  // 0. blue
        0.0f, 1.0f, 0.0f, 1.0f,  // 1. green
        0.0f, 0.0f, 1.0f, 1.0f,  // 2. blue
        0.0f, 1.0f, 0.0f, 1.0f
    )

    private val vertices = floatArrayOf(// Vertices of the 6 faces
        // FRONT
        -1.0f, -1.0f, 1.0f, // 0. left-bottom-front
        1.0f, -1.0f, 1.0f, // 1. right-bottom-front
        -1.0f, 1.0f, 1.0f, // 2. left-top-front
        1.0f, 1.0f, 1.0f, // 3. right-top-front
        // BACK
        1.0f, -1.0f, -1.0f, // 6. right-bottom-back
        -1.0f, -1.0f, -1.0f, // 4. left-bottom-back
        1.0f, 1.0f, -1.0f, // 7. right-top-back
        -1.0f, 1.0f, -1.0f, // 5. left-top-back
        // LEFT
        -1.0f, -1.0f, -1.0f, // 4. left-bottom-back
        -1.0f, -1.0f, 1.0f, // 0. left-bottom-front
        -1.0f, 1.0f, -1.0f, // 5. left-top-back
        -1.0f, 1.0f, 1.0f, // 2. left-top-front
        // RIGHT
        1.0f, -1.0f, 1.0f, // 1. right-bottom-front
        1.0f, -1.0f, -1.0f, // 6. right-bottom-back
        1.0f, 1.0f, 1.0f, // 3. right-top-front
        1.0f, 1.0f, -1.0f, // 7. right-top-back
        // TOP
        -1.0f, 1.0f, 1.0f, // 2. left-top-front
        1.0f, 1.0f, 1.0f, // 3. right-top-front
        -1.0f, 1.0f, -1.0f, // 5. left-top-back
        1.0f, 1.0f, -1.0f, // 7. right-top-back
        // BOTTOM
        -1.0f, -1.0f, -1.0f, // 4. left-bottom-back
        1.0f, -1.0f, -1.0f, // 6. right-bottom-back
        -1.0f, -1.0f, 1.0f, // 0. left-bottom-front
        1.0f, -1.0f, 1.0f   // 1. right-bottom-front
    )

    init {
        // Setup vertex-array buffer. Vertices in float. An float has 4 bytes
        val vbb = ByteBuffer.allocateDirect(vertices.size * 4)
        vbb.order(ByteOrder.nativeOrder())
        vertexBuffer = vbb.asFloatBuffer()
        vertexBuffer.put(vertices)
        vertexBuffer.position(0)

        // Setup color-array buffer. Colors in float. An float has 4 bytes
        val cbb = ByteBuffer.allocateDirect(colors.size * 4)
        cbb.order(ByteOrder.nativeOrder())
        colorBuffer = cbb.asFloatBuffer()
        colorBuffer.put(colors)
        colorBuffer.position(0)
    }

    //Draw the shape
    override fun draw(gl: GL10) {
        gl.glFrontFace(GL10.GL_CCW)    // Front face in counter-clockwise orientation
        gl.glEnable(GL10.GL_CULL_FACE) // Enable cull face
        gl.glCullFace(GL10.GL_BACK)    // Cull the back face (don't display)

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer)

        // Render all the faces
        for (face in 0 until numFaces) {
            // Set the color for each of the faces

//            gl.glColor4f(colors[face][0], colors[face][1], colors[face][2], colors[face][3])
//            // Draw the primitive from the vertex-array directly

            gl.glEnableClientState(GL10.GL_COLOR_ARRAY)
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer)

            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, face * 4, 4)

            gl.glDisableClientState(GL10.GL_COLOR_ARRAY)
        }
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glDisable(GL10.GL_CULL_FACE)
    }

}