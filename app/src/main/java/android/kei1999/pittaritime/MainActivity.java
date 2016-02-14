package android.kei1999.pittaritime;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    TextView mTimeTextView;
    TextView mResultTextView;

    Timer mTimer;

    Handler mHandler;

    int mTime;
    boolean startStop;
    int num1;
    int num2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimeTextView = (TextView) findViewById(R.id.textView);
        mResultTextView = (TextView) findViewById(R.id.textView2);


        mHandler = new Handler();

        startStop = true;

        num1 = 11;
        num2 = 0;

    }

    public void start(View v) {

        if (startStop) {

            mTime = num1;

            mTimer = new Timer(false);
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {

                            mTime--;

                            Log.d("timeNumber", String.valueOf(mTime));

                        }
                    });
                }
            }, 0, 1000);
            ((TextView) findViewById(R.id.button)).setText("STOP");
            startStop = false;
        } else {

            mTimer.cancel();

            if (mTime == 0) {
                mTimeTextView.setText(String.valueOf(mTime));
                mResultTextView.setText("おめっとさん！");
                mTimeTextView.setTextColor(Color.GREEN);

            } else {
                mTimeTextView.setText(String.valueOf(mTime));
                mResultTextView.setText("残念！");
                mTimeTextView.setTextColor(Color.RED);
            }
            ((TextView) findViewById(R.id.button)).setText("START");
            startStop=true;

        }

    }

    public void numberChange(View v){
        Random random = new Random();
        num1 = random.nextInt(30);
        num2=num1++;
        mTimeTextView.setText(num2+"秒体感");
        mTimeTextView.setTextColor(Color.BLACK);
        Log.d("changeNumber",String.valueOf(num2));

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mTimer.cancel();

    }
}