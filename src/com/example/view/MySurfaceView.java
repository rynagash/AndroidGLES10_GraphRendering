package com.example.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class MySurfaceView extends GLSurfaceView {
    private MyRenderer renderer = null;

    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attr) {
        super(context, attr);
        init();
    }

    private void init() {
        renderer = new MyRenderer();
        setRenderer(renderer);
        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    public MyRenderer getRenderer() {
        return renderer;
    }
}
