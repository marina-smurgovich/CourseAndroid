package com.yandex.smur.marina.customview;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private CustomView customView;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customView = ((CustomView) findViewById(R.id.customView));

        customView.setTouchActionListener(new CustomView.TouchActionListener() {
            @Override
            public void onTouchDown(final int x, final int y) {
                radioGroup = findViewById(R.id.radioGroup);
                int checkedId = radioGroup.getCheckedRadioButtonId();
                if (checkedId == -1) {
                } else {
                    switch (checkedId) {
                        case R.id.toast:
                            Toast.makeText(MainActivity.this, "X: " + x + " Y: " + y, Toast.LENGTH_SHORT).show();
                            ;
                            break;
                        case R.id.snackBar:
                            Snackbar.make(customView, "X - " + x + " Y - " + y, Snackbar.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                }

                if (x <= CustomView.widthCenter + Math.sqrt(Math.pow(CustomView.radius2, 2) -
                        Math.pow((y - CustomView.heightCenter), 2)) && y <= CustomView.heightCenter
                        + Math.sqrt(Math.pow(CustomView.radius2, 2) -
                        Math.pow((x - CustomView.widthCenter), 2))) {
                    customView.swapAllColor();
                }

                if (x < CustomView.widthCenter - CustomView.radius2 &&
                        x < CustomView.widthCenter + Math.sqrt(Math.pow(CustomView.radius1, 2) -
                                Math.pow((y - CustomView.heightCenter), 2)) &&
                        y < CustomView.heightCenter - CustomView.radius2 &&
                        y < CustomView.heightCenter + Math.sqrt(Math.pow(CustomView.radius1, 2) -
                                Math.pow((x - CustomView.widthCenter), 2))) {
                    customView.swapColorFirstSector();
                }

                if (x <= CustomView.widthCenter + Math.sqrt(Math.pow(CustomView.radius1, 2) -
                        Math.pow((y - CustomView.heightCenter), 2)) && y <= CustomView.heightCenter
                        + Math.sqrt(Math.pow(CustomView.radius1, 2) -
                        Math.pow((x - CustomView.widthCenter), 2)) &&
                        x > CustomView.widthCenter + CustomView.radius2
                        && y < CustomView.heightCenter + CustomView.radius2) {
                    customView.swapColorSecondSector();
                }

                if (x <= CustomView.widthCenter + Math.sqrt(Math.pow(CustomView.radius1, 2) -
                        Math.pow((y - CustomView.heightCenter), 2)) &&
                        x > CustomView.widthCenter + CustomView.radius2 &&
                        y > CustomView.heightCenter + CustomView.radius2 &&
                        y < CustomView.heightCenter + Math.sqrt(Math.pow(CustomView.radius1, 2) -
                                Math.pow((x - CustomView.widthCenter), 2))) {
                    customView.swapColorThirdSector();
                }

                if (x <= CustomView.widthCenter + Math.sqrt(Math.pow(CustomView.radius1, 2) -
                        Math.pow((y - CustomView.heightCenter), 2)) &&
                        x < CustomView.widthCenter - CustomView.radius2 &&
                        y > CustomView.heightCenter + CustomView.radius2 &&
                        y < CustomView.heightCenter + Math.sqrt(Math.pow(CustomView.radius1, 2) -
                                Math.pow((x - CustomView.widthCenter), 2))) {
                    customView.swapColorForthSector();
                }
            }
        });
    }
}
