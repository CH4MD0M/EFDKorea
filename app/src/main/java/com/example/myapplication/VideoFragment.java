package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static android.content.Context.AUDIO_SERVICE;

/**
 * Created by swjtw on 2018-05-21.
 */

public class VideoFragment extends Fragment {


    FrameLayout frameLayout;
    boolean isPageOpen = false;
    Animation translateUpAnim;
    Animation translateDownAnim;
    Animation translateVupAnim;
    LinearLayout page;
    ArrayList<String> a1;
    ArrayAdapter<String> aa;
    public boolean mShowing = false;
    public double startTime = 0;
    public double finalTime = 0;
    private Handler myHandler = new Handler();
    private  int forwardTime = 5000;
    private int backwardTime = 5000;
    public static int oneTimeOnly = 0;
    boolean isPlaying = false;
    private Dialog getDialog;

    public void memory_clean(){
        System.exit(0);
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_video, container, false);
        final VideoView videoView = (VideoView) rootView.findViewById(R.id.videoview);


        videoView.setBackgroundColor(Color.WHITE);


        // http://thdev.net/382 참고 할것.
        // http://cloudylab.blogspot.com/2015/02/android-full-screen.html 참고 할것.
        // TODO 버튼들 기능 입력.....
        final Button pButton = (Button)rootView.findViewById(R.id.playpauseButton);
        final Button prevButton = (Button)rootView.findViewById(R.id.prevButton);
        final Button nextButton = (Button)rootView.findViewById(R.id.nextButton);
        final SeekBar seekBar = (SeekBar)rootView.findViewById(R.id.SeekBar);
        final TextView endText = (TextView)rootView.findViewById(R.id.EndText);
        final TextView settingText = (TextView)rootView.findViewById(R.id.textView8);

        final Intent intent = getActivity().getIntent();
        // TODO 전 LIST에서 받은 변수....
        final String GradeNumber = intent.getStringExtra("L_Grade"); // GradeNumber 호출
        final String SubjectNumber = intent.getStringExtra("L_Subject"); // SubjectNumber 호출
        final String MediaNumber = intent.getStringExtra("MediaNumber");


        // TODO 배열로 받은 함수들 처리...

        final String[] Testchar = {GradeNumber, SubjectNumber,  MediaNumber};
        String gradename, subjectname, medianame  = "";

        if(Testchar[0].equals("GRADE1")){ gradename = "1st"; } // equal 함수는 한꺼번에 검색 contains 함수는 일부분 검색
        else if (Testchar[0].contains("GRADE2")){ gradename = "2nd"; }
        else if (Testchar[0].contains("GRADE3")){ gradename = "3rd"; }
        else if (Testchar[0].contains("GRADE4")){ gradename = "4th"; }
        else if (Testchar[0].contains("GRADE5")){ gradename = "5th"; }
        else { gradename = "6th"; }

        if(Testchar[1].contains("MATHMATHICS")){ subjectname="matE"; }
        else if (Testchar[1].contains("SCIENCE")){ subjectname="sciE"; }
        else {subjectname="engE";}
        medianame = MediaNumber.replaceAll("[^0-9]","");

        //TODO-------------------------------------------------------------------------------------------------------------------------------------------------------

        //TODO 테스트 경로
        String handtitle="storage/0000-0000/" + GradeNumber + "/" + SubjectNumber + "/" +gradename + "_" + subjectname + "_" + medianame + ".mp4";


        //TODO-------------------------------------------------------------------------------------------------------------------------------------------------------


        seekBar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

        a1 = new ArrayList<String>();
        aa = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, a1);

        ListView listview = (ListView) rootView.findViewById(R.id.listView1);
        listview.setAdapter(aa);
        videoView.setVideoPath(handtitle);
        videoView.seekTo(0);
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        settingText.setText(String.format("%d : %d", 0,0));
        endText.setText(String.format("%d : %d", 0,0));



        final AudioManager audioManager;
        audioManager = (AudioManager)getActivity().getSystemService(AUDIO_SERVICE);
        int nMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int nCurrentVolumn = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);



        final Runnable UpdateSongTime = new Runnable() {
            @Override
            public void run(){

                startTime = videoView.getCurrentPosition();


                settingText.setText(

                        String.format("%d : %d ",
                                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                                toMinutes((long) startTime)))
                );

                seekBar.setProgress((int)startTime);
                myHandler.postDelayed(this,100);


            }
        };

        //TODO Seek Bar 관련 코드
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (seekBar.getMax() == progress) {
                    isPlaying = true;
                    videoView.seekTo(0);
                    videoView.pause();

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(videoView.isPlaying()){
                    videoView.start();
                }else{
                    videoView.pause();
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if(videoView.isPlaying()) {
                    int ttt = seekBar.getProgress(); // 사용자가 움직여놓은 위치
                    videoView.seekTo(ttt);
                    videoView.start();
                    new MyThread().start();
                }else{
                    int ttt = seekBar.getProgress(); // 사용자가 움직여놓은 위치
                    videoView.seekTo(ttt);
                    videoView.pause();
                }
                // 출처: http://bitsoul.tistory.com/28 [Happy Programmer~]
            }
        });




        //재생 및 일시정지
        pButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(videoView.isPlaying()){
                    videoView.pause();
                    pButton.setBackgroundResource(R.drawable.ic_play_circle_outline_black_24dp);
                    Toast.makeText(getActivity(), "Pausing ", Toast.LENGTH_SHORT).show();
                }else {
                    videoView.start();
                    Toast.makeText(getActivity(), "Playing", Toast.LENGTH_SHORT).show();
                    pButton.setBackgroundResource(R.drawable.ic_pause_circle_outline_black_24dp);
                    finalTime = videoView.getDuration();
                    startTime = videoView.getCurrentPosition();
                    if (oneTimeOnly == 0) {
                        seekBar.setMax((int) finalTime);
                        oneTimeOnly = 1;
                    }

                    endText.setText(String.format("%d : %d ",
                            TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                            TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime))));


                    seekBar.setProgress((int) startTime);

                    myHandler.postDelayed(UpdateSongTime, 100);

                }

            }
        });

        //빨리감기
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.seekTo(videoView.getCurrentPosition()+10000);
            }
        });
        //되감기
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.seekTo(videoView.getCurrentPosition()-10000);
            }
        });






//화면 터치시 버튼들 숨겨줌
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mShowing) {
                    settingText.setVisibility(View.VISIBLE);
                    pButton.setVisibility(View.VISIBLE);
                    prevButton.setVisibility(View.VISIBLE);
                    nextButton.setVisibility(View.VISIBLE);
                    seekBar.setVisibility(View.VISIBLE);
                    endText.setVisibility(View.VISIBLE);
                    mShowing = false;
                }else{
                    settingText.setVisibility(View.INVISIBLE);
                    pButton.setVisibility(View.INVISIBLE);
                    prevButton.setVisibility(View.INVISIBLE);
                    nextButton.setVisibility(View.INVISIBLE);
                    seekBar.setVisibility(View.INVISIBLE);
                    endText.setVisibility(View.INVISIBLE);
                    mShowing = true;
                }
                return false;
            }
        });



        page = (LinearLayout)rootView.findViewById(R.id.page);
        translateDownAnim = AnimationUtils.loadAnimation(getActivity(),R.anim.translate_down);
        translateUpAnim = AnimationUtils.loadAnimation(getActivity(),R.anim.translate_up);
        translateVupAnim = AnimationUtils.loadAnimation(getActivity(),R.anim.translate_videoup);


        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) videoView.getLayoutParams();
        params.width =  metrics.widthPixels;
        params.height = (int) (900 * metrics.density);
        params.leftMargin = 0;

        return rootView;


    }

}
