package com.example.univnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class MainActivity extends AppCompatActivity {
    public static int money = 0;
    SoundPool soundPool;    // 効果音を鳴らす本体（コンポ）
    int mp3;          // 効果音データ（mp3）
    public static final String EXTRA_MESSAGE = "Your PackageName.MESSAGE";
    static final int RESULT_SUBACTIVITY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        } else {
            AudioAttributes attr = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(attr)
                    .setMaxStreams(5)
                    .build();
        }

        // ③ 読込処理(CDを入れる)
        mp3 = soundPool.load(this, R.raw.mp3money, 1);
    }

    public void Smoking(View view) {
        soundPool.play(mp3, 1f, 1f, 0, 0, 1f);

        /*Button rTime5;
        rTime5 = findViewById(R.id.smoking);
        rTime5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                if(v.isSelected()){
                    //・・・選択時に行いたい処理・・・
                }
            }
        });*/

        TextView tv = (TextView)findViewById(R.id.myMoney);
        money += 25;
        tv.setText("￥" + String.valueOf(money));
    }

    public void Drinking(View view) {
        Intent intent = new Intent(getApplication(), InputActivity.class);
        startActivityForResult(intent, RESULT_SUBACTIVITY);
    }

    public void Gambling(View view) {

        Intent intent = new Intent(getApplication(), InputActivity.class);
        startActivityForResult(intent, RESULT_SUBACTIVITY);
    }

    public void ResetMoney(View view) {
        soundPool.play(mp3, 1f, 1f, 0, 0, 1f);
        TextView tv = (TextView)findViewById(R.id.myMoney);
        money = 0;
        tv.setText("￥" + String.valueOf(money));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK && requestCode == RESULT_SUBACTIVITY && null != intent) {
            int res = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));

            TextView tv = (TextView)findViewById(R.id.myMoney);
            money += res;
            tv.setText("￥" + (String.valueOf(money)));
        }
    }
}
