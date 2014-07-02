package com.example.view;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;

import com.example.exception.InitException;
import com.example.object.CLine;

public class MyRenderer implements Renderer {
    private CLine line = null;
    // @formatter:off
    private float[] rect = {
            -1.0f,  // left
             1.0f, // right
            -1.0f,  // bottom
             1.0f, // top
    };
    // @formatter:on
    private int viewSize[] = { -1, -1 };

    public MyRenderer() {
        line = new CLine();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        try {
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
            gl.glEnable(GL10.GL_DEPTH_TEST);

            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();

            line.draw(gl);

            gl.glDisable(GL10.GL_DEPTH_TEST);
        } catch (InitException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        viewSize[0] = width;
        viewSize[1] = height;

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(rect[0], rect[1], rect[2], rect[3], 10.f, -10.f);

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
    }

    public CLine getLine() {
        return line;
    }

    public void setRectLeft(float value) {
        rect[0] = value;
    }

    public void setRectRight(float value) {
        rect[1] = value;
    }

    public void setRectBottom(float value) {
        rect[2] = value;
    }

    public void setRectTop(float value) {
        rect[3] = value;
    }

    public void setRect(float left, float right, float buttom, float top) {
        rect[0] = left;
        rect[1] = right;
        rect[2] = buttom;
        rect[3] = top;
    }

}
