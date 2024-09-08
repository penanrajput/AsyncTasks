package com.example.asynctasks;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class AsyncTaskActivity extends AppCompatActivity {

    Button btnRandom;
    Button btnStart;
    TextView tvCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_async_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnRandom = findViewById(R.id.btnRandom);
        btnStart = findViewById(R.id.btnStart);
        tvCounter = findViewById(R.id.tvCounter);

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                tvCounter.setText(String.valueOf(r.nextInt(100)));
            }
        });


        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                CountTask cTask = new CountTask();
                cTask.execute(5);
            }
        });
    }

    void waitNSec(int n) {
        for (int i = 0; i < n; i++) {
            wait1Sec();
        }
    }

    private void wait1Sec() {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < startTime + 1000) ;
    }

    class CountTask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tvCounter.setText(String.valueOf(values[0]));
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            Log.d("ASYNC", "doInBackground : started");

            int n = integers[0];
            for (int i = 0; i < n; i++) {
                wait1Sec();
                publishProgress(i);
            }

            Log.d("ASYNC", "doInBackground : ended");
            return null;
        }
    }
}