package com.mishobu.acelerometro;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

    SensorManager mySensorManager;
    TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        myTextView = (TextView) findViewById(R.id.texto);
    }

    @Override
    protected void onPause() {

        super.onPause();
        mySensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mySensorManager.registerListener(this,mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent e) {
        synchronized (this) {
            if (e.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                myTextView.setText("x= " + e.values[0] + "\ny= " + e.values[1] + "\nz= " + e.values[2]);
            }
        }


    }

}
