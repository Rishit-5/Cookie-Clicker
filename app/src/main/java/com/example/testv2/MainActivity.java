package com.example.testv2;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        View.OnTouchListener,
        GestureDetector.OnGestureListener{

    private ImageView img1;//first image view for cookie
    private ImageView img2;//second image view for x2 bonus
    private ImageView img3;//third image view for power up bonus
    private int c = 0;//the variable storing user points
    private TextView txt;//text view that displays chocolate chip cookies
    private TextView txt2;//text view that displays money
    private TextView txt3;//text view that displays dark chocolate
    private TextView txt4;//text view that displays oatmeal
    private TextView mTextField;//text field displaying time left
    private boolean isRunning;//boolean verifying if the game is over
    private int multiplier = 1;//number that multiplies the swipe pointws
    private ArrayList<Cookie> myCookies = new ArrayList<>();
    private boolean hasDark;
    private boolean hasOat;
    private Button btn;
    private Button btn1;
    private Button btn2;
    private TextView txt5;//text view that displays price
    private TextView txt6;//text view that displays price
    private TextView txt7;//text view that displays price

    private double money = 0;
    private GestureDetector mGestureDetector;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myCookies.add(new Cookie("Chocolate Chip", 1, 0 ));
        myCookies.add(new Cookie("Dark Chocolate", 1.25, 0 ));
        myCookies.add(new Cookie("Oatmeal", 1.5, 0 ));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = findViewById(R.id.imageView);
        img2 = findViewById(R.id.imageView5);
        img3 = findViewById(R.id.imageView3);
        img2.setVisibility(View.INVISIBLE);
        img3.setVisibility(View.INVISIBLE);
        txt = findViewById(R.id.textView);
        mTextField = findViewById(R.id.mTextField);
        txt2 = findViewById(R.id.textView4);
        txt3 = findViewById(R.id.textView5);
        txt4 = findViewById(R.id.textView6);
        btn = findViewById(R.id.button);
        btn1 = findViewById(R.id.button2);
        btn2 = findViewById(R.id.button3);
        txt5 = findViewById(R.id.txt1);
        txt6 = findViewById(R.id.txt2);
        txt7 = findViewById(R.id.txt3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (money>=150 && !hasDark) {
                    hasDark = true;
                    money-=150;
                }

                txt3.setText("Dark Chocolate: " + myCookies.get(1).getAmount());
                txt4.setText("Oatmeal: " + myCookies.get(2).getAmount());
                txt.setText("Chocolate Chip Cookies: " + myCookies.get(0).getAmount());
                txt2.setText("money: $" + money);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (money>=250 && !hasOat) {
                    hasOat = true;
                    money-=250;
                }
                txt3.setText("Dark Chocolate: " + myCookies.get(1).getAmount());
                txt4.setText("Oatmeal: " + myCookies.get(2).getAmount());
                txt.setText("Chocolate Chip Cookies: " + myCookies.get(0).getAmount());
                txt2.setText("money: $" + money);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < myCookies.size(); i++) {
                    money+= myCookies.get(i).getPrice() * myCookies.get(i).getAmount();
                    myCookies.get(i).subtract(myCookies.get(i).getAmount());
                    txt3.setText("Dark Chocolate: " + myCookies.get(1).getAmount());
                    txt4.setText("Oatmeal: " + myCookies.get(2).getAmount());
                    txt.setText("Chocolate Chip Cookies: " + myCookies.get(0).getAmount());
                    txt2.setText("money: $" + money);

                }
            }
        });
        img1.setOnTouchListener(this);
        mGestureDetector = new GestureDetector(this,this);
        setImage();

        new CountDownTimer(60000, 1000) {//60 seconds and counting down by 1 second each time

            public void onTick(long millisUntilFinished) {
                isRunning = true;
                mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);//updates the textview with the remaining time
                doEvent();
                if (hasDark){
                    myCookies.get(1).add(15);
                }
                if (hasOat){
                    myCookies.get(2).add(15);
                }
                for (int i = 0; i < myCookies.size(); i++) {
                    myCookies.get(i).changePrice();
                }
                money*=100;
                money = Math.floor(money);
                money/=100;
                txt3.setText("Dark Chocolate: " + myCookies.get(1).getAmount());
                txt4.setText("Oatmeal: " + myCookies.get(2).getAmount());
                txt.setText("Chocolate Chip Cookies: " + myCookies.get(0).getAmount());
                txt2.setText("money: $" + money);
                txt5.setText("Chocolate Chip Price: " + myCookies.get(0).getPrice());
                txt6.setText("Dark Chocolate Price: " + myCookies.get(1).getPrice());
                txt7.setText("Oatmeal Price: " + myCookies.get(2).getPrice());
            }

            public void onFinish() {
                mTextField.setText("done!");
                isRunning = false;
            }
        }.start();


    }

    private void setImage(){
        Glide.with(this)
                .load(R.drawable.cookie)
                .into(img1);

    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }



    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if (isRunning) {
            double x = Math.sqrt((Math.pow(v, 2) + Math.pow(v1, 2))) * multiplier;//generates an amount of points based on the overall velocity of the
            // swipe that's calculated using pythagorean theorem, then multiplies by current multiplier
            myCookies.get(0).add((int) (x / 1000));//divides points by a thousand to make a readable amount
            txt.setText("Chocolate Chip Cookies: " + myCookies.get(0).getAmount());
        }
        return false;
    }



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        mGestureDetector.onTouchEvent(motionEvent);
        return view.getId() == R.id.imageView;
    }
    public void doEvent() {
        if (isRunning) {
            //if a random number is chosen, user will be able to click the image view for a x2 bonus
            if ((int) Math.floor(Math.random() * (20 - 0 + 1)) + 0 == 3 && multiplier ==1) {//verifies that it isn't already activated so it doesn't pop up again
                img2.setVisibility(View.VISIBLE);
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        multiplier = 2;
                        img2.setVisibility(View.INVISIBLE);
                    }

                });

            }
            if ((int) Math.floor(Math.random() * (7 - 0 + 1)) + 0 == 7) {
                new CountDownTimer(1000, 1000) {
                    //the user gets 1 second to click the power up before it disappears
                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {
                        img3.setVisibility(View.INVISIBLE);
                    }
                }.start();
                img3.setVisibility(View.VISIBLE);
                img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //if clicked, 400 points will be given to user
                        myCookies.get(0).add(100);
                        txt.setText("Chocolate Chip Cookies: " + myCookies.get(0).getAmount());
                        img3.setVisibility(View.INVISIBLE);
                    }

                });
            }

        }
    }
}