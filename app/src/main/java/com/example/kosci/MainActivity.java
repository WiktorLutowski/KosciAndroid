package com.example.kosci;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random rand;
    private TextView resultTextView;
    private TextView singleResultTextView;
    private ImageView[] images;
    private boolean[] isDisabled;

    private final String singleResultText = "Wynik tego losowania: ";
    private final String resultText = "Wynik gry: ";

    private int result = 0;
    private int singleResult = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rand = new Random();

        resultTextView = findViewById(R.id.resultTextView);
        singleResultTextView = findViewById(R.id.singleResultTextView);

        images = new ImageView[5];
        isDisabled = new boolean[5];

        for (int i = 0; i < isDisabled.length; i++)
            isDisabled[i] = false;

        images[0] = findViewById(R.id.image1);
        images[1] = findViewById(R.id.image2);
        images[2] = findViewById(R.id.image3);
        images[3] = findViewById(R.id.image4);
        images[4] = findViewById(R.id.image5);

        for (int i = 0; i < images.length; i++)
        {
            int ii = i;
            images[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isDisabled[ii] = !isDisabled[ii];

                    if(isDisabled[ii])
                    {
                        images[ii].setImageResource(R.drawable.question);
                        images[ii].setImageAlpha(150);
                    }
                    else
                        images[ii].setImageAlpha(255);
                }
            });
        }


        findViewById(R.id.throwButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashSet<Integer> throwenNumbers = new HashSet<Integer>();
                ArrayList<Integer> choosenNumbers = new ArrayList<Integer>();

                singleResult = 0;

                for (int i = 0; i < images.length; i++)
                {
                    if(isDisabled[i])
                        continue;

                    int randInt = rand.nextInt(6);

                    if(throwenNumbers.contains(randInt))
                    {
                        if(!choosenNumbers.contains(randInt))
                            singleResult += randInt + 1;

                        choosenNumbers.add(randInt);
                        singleResult += randInt + 1;
                    }
                    else
                        throwenNumbers.add(randInt);


                    switch (randInt)
                    {
                        case 0:
                            images[i].setImageResource(R.drawable.k1);
                            break;
                        case 1:
                            images[i].setImageResource(R.drawable.k2);
                            break;
                        case 2:
                            images[i].setImageResource(R.drawable.k3);
                            break;
                        case 3:
                            images[i].setImageResource(R.drawable.k4);
                            break;
                        case 4:
                            images[i].setImageResource(R.drawable.k5);
                            break;
                        case 5:
                            images[i].setImageResource(R.drawable.k6);
                            break;
                    }

                }

                result += singleResult;

                resultTextView.setText(resultText + result);
                singleResultTextView.setText(singleResultText + singleResult);

            }
        });

        findViewById(R.id.resetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < images.length; i++)
                {
                    images[i].setImageResource(R.drawable.question);
                }

                singleResult = 0;
                result = 0;

                resultTextView.setText(resultText + result);
                singleResultTextView.setText(singleResultText + singleResult);
            }
        });
    }
}