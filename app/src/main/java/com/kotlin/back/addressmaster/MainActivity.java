package com.kotlin.back.addressmaster;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout constrainLayoutLeft;
    ConstraintLayout constrainLayoutRight;
    ConstraintLayout constrainLayoutSwitch;
    TextView tvDetailStartCity;
    TextView tvDetailEndCity;
    ObjectAnimator startAnimator;
    ObjectAnimator endAnimator;
    AnimatorSet animatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constrainLayoutLeft=findViewById(R.id.constrainLayoutLeft);
        constrainLayoutRight=findViewById(R.id.constrainLayoutRight);
        constrainLayoutSwitch=findViewById(R.id.constrainLayoutSwitch);
        tvDetailStartCity=findViewById(R.id.tvDetailStartCity);
        tvDetailEndCity=findViewById(R.id.tvDetailEndCity);
        constrainLayoutSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constrainLayoutSwitch.setClickable(false);
                ObjectAnimator iconAnimator = ObjectAnimator.ofFloat(
                        constrainLayoutSwitch,
                        View.ROTATION,
                        constrainLayoutSwitch.getRotation(),
                        constrainLayoutSwitch.getRotation() + 180
                );
                int leftMargin = 0;
                int rightMargin = 0;
                ConstraintLayout.LayoutParams leftLayoutParams = (ConstraintLayout.LayoutParams) tvDetailStartCity.getLayoutParams();
                leftMargin = leftLayoutParams.leftMargin;

                ConstraintLayout.LayoutParams rightLayoutParams = (ConstraintLayout.LayoutParams) tvDetailEndCity.getLayoutParams();
                rightMargin = rightLayoutParams.rightMargin;
                if (constrainLayoutLeft.getTranslationX() == 0) {
                    startAnimator = ObjectAnimator.ofFloat(
                            constrainLayoutLeft,
                            View.TRANSLATION_X,
                            constrainLayoutLeft.getTranslationX(),
                            constrainLayoutRight.getX() + (constrainLayoutLeft.getWidth() - constrainLayoutRight.getWidth()) - leftMargin
                    );
                    endAnimator = ObjectAnimator.ofFloat(
                            constrainLayoutRight,
                            View.TRANSLATION_X,
                            constrainLayoutRight.getTranslationX(),
                            -constrainLayoutRight.getX() + rightMargin
                    );
                } else {
                    startAnimator = ObjectAnimator.ofFloat(
                            constrainLayoutLeft,
                            View.TRANSLATION_X,
                            -constrainLayoutRight.getTranslationX(),
                                    - constrainLayoutRight.getX() + leftMargin
                    );
                    endAnimator = ObjectAnimator.ofFloat(
                            constrainLayoutRight,
                            View.TRANSLATION_X,
                            constrainLayoutRight.getTranslationX(),
                            constrainLayoutRight.getX() - rightMargin
                    );
                    constrainLayoutLeft.setTranslationX(0);
                }

                animatorSet =new AnimatorSet();
                animatorSet.addListener(new Animator.AnimatorListener(){

                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        constrainLayoutSwitch.setClickable(true);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animatorSet.playTogether(startAnimator, endAnimator, iconAnimator);
                animatorSet.setDuration(500) ;
                animatorSet.start();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}