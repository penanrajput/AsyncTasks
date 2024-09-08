package com.example.asynctasks;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "ASYNC";
    public String fruits[] = new String[]{
            "Banana",
            "Apple",
            "Strawberry",
            "Avocado",
            "Pineapple",
            "Watermelon",
            "Mango",
            "Kiwi",
            "Orange",
            "Berry",
            "Blueberry",
            "Cherry",
            "Lemon",
            "Apricot",
            "Figs",
            "Plum",
            "Papaya",
            "Grapefruit"
    };

    Button btnChangeColor;
    ConstraintLayout clBackground;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.clBackground), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnChangeColor = findViewById(R.id.btnChangeColor);
        clBackground = findViewById(R.id.clBackground);
        lvItems = findViewById(R.id.lvItems);

        //  public ArrayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<T> objects)
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                fruits
        );

        // public void setAdapter(ListAdapter adapter)
        lvItems.setAdapter(arrayAdapter);

        //        System.currentTimeMillis(1000);
        btnChangeColor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                Log.d(TAG, "onClick: System.currentTimeMillis() = " + System.currentTimeMillis());
//                waitNSec(10);
//                clBackground.setBackgroundColor(Color.RED);
//                Log.d(TAG, "onClick: System.currentTimeMillis() = " + System.currentTimeMillis());

                // using Handler of android.os.Handler
                Handler h = new Handler();

                // Runnable is interface that has method called "run"
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        Log.d("ASYNC", "run : we have waited for 5 seconds");
                        clBackground.setBackgroundColor(Color.RED);
                    }
                };
                // 5000 milliseconds = 5 seconds
                h.postDelayed(r, 5000);
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
}