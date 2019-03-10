package com.example.hwk01;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class Level03Activity extends AppCompatActivity {

    private static int count = 1;
    private TextView scoreLabel;
    Score score = new Score();
    Button b1, b2, b3;
    ArrayList<Integer> values = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level03);
        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        scoreLabel.setText("Score : " + score.getScore());
        createNewRandom(0, 30, 3);

    }

    private void createNewRandom(int min, int max, int number) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = min; i <= max; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i = 0; i < number; i++) {
            list.get(i);
        }


        b1 = (Button) findViewById(R.id.btn1);
        b1.setText(Integer.toString(list.get(0)));
        b2 = (Button) findViewById(R.id.btn2);
        b2.setText(Integer.toString(list.get(1)));
        b3 = (Button) findViewById(R.id.btn3);
        b3.setText(Integer.toString(list.get(2)));
        values.add(list.get(0));
        values.add(list.get(1));
        values.add(list.get(2));

    }


    public void onClickButton(View view) {
        Button btn = (Button) view;
        int value = Integer.parseInt(btn.getText().toString());
        //pass the level
        if (count == 5 && value == Collections.max(values)) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Level03Activity.this);
            alertDialogBuilder
                    .setMessage("Level-03 is Completed \n" + "Score: " + (count + 10))
                    .setCancelable(false)
                    .setPositiveButton("NEXT LEVEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), Level04Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("QUIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            score.up();
            count = 1;
        } else if (count != 5 && value == Collections.max(values)) {
            Intent intent = new Intent(getApplicationContext(), Level03Activity.class);
            startActivity(intent);
            finish();
            count++;
            score.up();

        } else if (value != Collections.max(values)) {
            //quit
            Intent intent = new Intent(getApplicationContext(), result.class);
            intent.putExtra("SCORE", score.getScore());

            startActivity(intent);
            finish();
            score.reset();
            count = 1;
        }

        scoreLabel.setText("Score : " + score.getScore());
    }


}