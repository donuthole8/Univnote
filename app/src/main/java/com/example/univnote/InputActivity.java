package com.example.univnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class InputActivity extends AppCompatActivity {
    SoundPool soundPool;    // 効果音を鳴らす本体（コンポ）
    int mp3;          // 効果音データ（mp3）

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

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

    /*public void Smoking(View view) {
        soundPool.play(mp3, 1f, 1f, 0, 0, 1f);
        TextView tv = (TextView)findViewById(R.id.myMoney);
        money += 25;
        tv.setText("￥" + String.valueOf(money));
    }*/

    public void Return(View view) {
        soundPool.play(mp3, 1f, 1f, 0, 0, 1f);
        EditText et = (EditText)findViewById(R.id.usedMoney);;
        TextView tv = (TextView)findViewById(R.id.myMoney);
        Intent intent = new Intent();
        int len = et.getText().toString().length();

        if (len == 0 ) {
        //if (et.getText() == null) {
            TextView warning = (TextView)findViewById(R.id.warning);
            warning.setText("使用金額を入力してください");
        } else {
            //MainActivity.money += Integer.valueOf(et.getText().toString());
            //tv.setText("￥" + String.valueOf(MainActivity.money));
            String data = et.getText().toString();
            intent.putExtra(MainActivity.EXTRA_MESSAGE, data);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
