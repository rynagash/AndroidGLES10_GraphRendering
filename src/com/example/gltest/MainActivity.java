package com.example.gltest;

import java.nio.FloatBuffer;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.view.MySurfaceView;

public class MainActivity extends Activity {

    private MySurfaceView view = null;
    private Timer timer = null;
    private static final int NUM_OF_POINTS = 2048;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        view = (MySurfaceView) findViewById(R.id.mySurfaceView1);
        view.getRenderer().getLine().init(NUM_OF_POINTS);
        view.getRenderer().getLine().setLineWidth(2.0f);
        view.getRenderer().setRect(0.0f, (float) NUM_OF_POINTS, 0.0f, 1.0f);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (view != null) {
            view.onResume();
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(new DrawTask(), 0, 200);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (view != null) {
            view.onPause();
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private class DrawTask extends TimerTask {
        @Override
        public void run() {
            FloatBuffer buffer = view.getRenderer().getLine().getVertexBuffer();
            for (int i = 0; i < NUM_OF_POINTS; i++) {
                buffer.put(3 * i, (float) i);
                buffer.put(3 * i + 1, (float) Math.random());
                buffer.put(3 * i + 2, 0.0f);
            }
            buffer.position(0);
            view.getRenderer().getLine().setNumOfValues(NUM_OF_POINTS * 3);

            view.requestRender();
        }
    }
}
