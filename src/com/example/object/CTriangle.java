package com.example.object;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.opengles.GL10;

import com.example.exception.InitException;

public class CTriangle extends CObject {
    // @formatter:off
    static final float points[] = {
        -1.f, .0f, .0f,
         0.f, .0f, .0f,
         .0f, 1.f, .0f
    };
    //@formatter:on

    public CTriangle() {
        init();
    }

    public void init() {
        try {
            setBufferSize(points.length * 4);
            setVertexBuffer();
        } catch (InitException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(GL10 gl) throws InitException {
        if (!isInit) {
            throw new InitException();
        }

        if (visible) {
            gl.glPushMatrix();

            gl.glColor4f(color[0], color[1], color[2], color[3]);

            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
            gl.glDrawArrays(GL10.GL_TRIANGLES, 0, points.length / 3);

            gl.glPopMatrix();
        }
    }

    @Override
    protected void setVertexBuffer() throws InitException {
        if (!isInit) {
            throw new InitException();
        }

        ByteBuffer vbb1 = ByteBuffer.allocateDirect(bufferSize);
        vbb1.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb1.asFloatBuffer();
        vertexBuffer.put(points);
        vertexBuffer.position(0);

    }

    @Override
    public void setBufferSize(int buffer_size) {
        this.bufferSize = buffer_size;
        isInit = true;
    }

}
