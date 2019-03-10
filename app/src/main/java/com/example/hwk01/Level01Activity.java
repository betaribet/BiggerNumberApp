package com.example.hwk01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.Collections;


public class Level01Activity extends AppCompatActivity {
    private static int count = 1;
    private TextView scoreLabel;
    Score score = new Score();
    Button b1, b2;
    ArrayList<Integer> values = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level01);

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        scoreLabel.setText("Score : " + score.getScore());
        createNewRandom(0, 10, 2);

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
        values.add(list.get(0));
        values.add(list.get(1));


    }


    public void onClickButton(View view) {
        Button btn = (Button) view;
        int value = Integer.parseInt(btn.getText().toString());
        //pass the level
        if (count == 5 && value == Collections.max(values)) {
            score.up();

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Level01Activity.this);
            alertDialogBuilder
                    .setMessage("Level-01 is Completed \n" + "Score: " + count)
                    .setCancelable(false)
                    .setPositiveButton("NEXT LEVEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), Level02Activity.class);
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

            count = 1;
        } else if (count != 5 && value == Collections.max(values)) {
            Intent intent = new Intent(getApplicationContext(), Level01Activity.class);
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