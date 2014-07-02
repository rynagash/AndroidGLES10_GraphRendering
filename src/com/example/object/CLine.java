package com.example.object;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.opengles.GL10;

import com.example.exception.InitException;

public class CLine extends CObject {
    private int numOfValues = 0;

    public int setNumOfValues() {
        return numOfValues;
    }

    public void setNumOfValues(int num_of_values) {
        this.numOfValues = num_of_values;
    }

    private float line_width = 1.0f;

    public void init(int num_of_points) {
        setNumOfPoints(num_of_points);
    }

    public void setNumOfPoints(int numOfPints) {
        try {
            setBufferSize(numOfPints * 3 * 4);
            setVertexBuffer();
        } catch (InitException e) {
            e.printStackTrace();
        }
    }

    public void addPoint(float x, float y, float z) throws InitException {
        if (!isInit) {
            throw new InitException();
        }
        if ((numOfValues + 3) * 4 < bufferSize) {
            vertexBuffer.put(numOfValues++, x);
            vertexBuffer.put(numOfValues++, y);
            vertexBuffer.put(numOfValues++, z);
            vertexBuffer.position(0);
        }
    }

    public void clearPoints() throws InitException {
        if (!isInit) {
            throw new InitException();
        }
        vertexBuffer.clear();
        numOfValues = 0;
    }

    
    /**
     * vertexBufferをpointsで埋める。
     * 参照先が変わるので注意！
     * 大量のデータを何度もセットするとGC呼ばれまくる。
     * @param points
     * @throws InitException
     */
    public void setVertex(float[] points) throws InitException {
        if (!isInit) {
            throw new InitException();
        }
        if (bufferSize >= points.length * 4) {
            clearPoints();
            vertexBuffer.put(points);
            vertexBuffer.position(0);
            numOfValues = points.length;
        }
    }

    public float getLineWidth() {
        return line_width;
    }

    public void setLineWidth(float lineWidth) {
        this.line_width = lineWidth;
    }

    @Override
    public void setBufferSize(int bufferWize) {
        this.bufferSize = bufferWize;
        isInit = true;
    }

    @Override
    public void draw(GL10 gl) throws InitException {
        if (!isInit) {
            throw new InitException();
        }
        if (visible) {
            gl.glPushMatrix();

            gl.glLineWidth(line_width);
            gl.glColor4f(color[0], color[1], color[2], color[3]);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
            gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, numOfValues / 3);

            gl.glPopMatrix();
        }
    }

    @Override
    protected void setVertexBuffer() throws InitException {
        if (!isInit) {
            throw new InitException();
        }
        ByteBuffer vbb1 = ByteBuffer.allocateDirect(bufferSize);
        vertexBuffer = vbb1.order(ByteOrder.nativeOrder()).asFloatBuffer();
    }
}
