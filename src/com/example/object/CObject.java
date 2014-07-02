package com.example.object;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.example.exception.InitException;

public abstract class CObject {
    protected boolean visible = true;
    protected float color[] = { 1.f, 1.f, 1.f, 1.f };
    protected FloatBuffer vertexBuffer;
    protected int bufferSize;
    protected boolean isInit = false;

    public abstract void setBufferSize(int buffer_size);

    public abstract void draw(GL10 gl) throws InitException;

    protected abstract void setVertexBuffer() throws InitException;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setColor(float r, float g, float b, float a) {
        color[0] = r;
        color[1] = g;
        color[2] = b;
        color[3] = a;
    }

    public FloatBuffer getVertexBuffer() {
        return vertexBuffer;
    }
}
