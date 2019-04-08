package edu.upb.progra3demo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    private ImageView pause;
    private ImageView coverImage;

    private TextView songName;
    private TextView songArtist;

    private AppCompatSeekBar songTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initViews();
        addEvents();
        modifyContent();
    }

    private void initViews() {
        pause = findViewById(R.id.pause);
        coverImage = findViewById(R.id.coverImage);
        songName = findViewById(R.id.songName);
        songArtist = findViewById(R.id.songArtist);
        songTime = findViewById(R.id.songTime);
    }

    private void addEvents() {
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Pause image", "Clicked");

                String songPlaying = songName.getText().toString();
                Toast.makeText(mContext, "Estamos reproduciendo: " + songPlaying,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        songTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.e("Song time", "Progreso: " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void modifyContent() {
        songArtist.setText("Barak");
        songName.setText("Cantaremos");
        coverImage.setImageResource(R.drawable.barak);
    }
}
