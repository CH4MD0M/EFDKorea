package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

public class ListActivity extends AppCompatActivity {


    ArrayList<ListStore> LiStore= new ArrayList<ListStore>();
    ArrayList<String> arr = new ArrayList<String>();

    VideoFragment videoFragment = new VideoFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_list);


        Intent intent = getIntent();
        final String GradeNumber = intent.getStringExtra("R_GradeNumber");
        final String SubjectNumber = intent.getStringExtra("SubjectNumber");





        //TODO-------------------------------------------------------------------------------------------------------------------------------------------------------


        // [태블릿용] 동영상 경로
        String VideoPath= "storage/0000-0000/" + GradeNumber + "/" + SubjectNumber;
        // [태블릿용] 강의명(subtitle) 경로
        String SubtitlePath= "storage/0000-0000/" + GradeNumber + "/" + SubjectNumber + "/" + "subtitle";

        //TODO-------------------------------------------------------------------------------------------------------------------------------------------------------


        File directory = new File(VideoPath);
        File[] files = directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().toLowerCase(Locale.US).endsWith(".mp4");
            }
        });

        final String[] Testchar = {GradeNumber, SubjectNumber};
        String gradename, subjectname = "";

        if(Testchar[0].equals("GRADE1")){ gradename = "1st"; } // equal 함수는 한꺼번에 검색 contains 함수는 일부분 검색
        else if (Testchar[0].contains("GRADE2")){ gradename = "2nd"; }
        else if (Testchar[0].contains("GRADE3")){ gradename = "3rd"; }
        else if (Testchar[0].contains("GRADE4")){ gradename = "4th"; }
        else if (Testchar[0].contains("GRADE5")){ gradename = "5th"; }
        else { gradename = "6th"; }

        if(Testchar[1].contains("MATHMATHICS")){ subjectname="matE"; }
        else if (Testchar[1].contains("SCIENCE")){ subjectname="sciE"; }
        else {subjectname="engE";}

        String Synopurl = SubtitlePath+"/"+gradename+"_"+subjectname+"_";


        for (int num=1; num<=files.length; num++)
        {
            LiStore.add(new ListStore("Chapter "+ num, ""+ReadTextFile(Synopurl+num+".txt"),""+num,""+SubjectNumber));
        }

        MyAdapter adapter = new MyAdapter(
                getApplicationContext(), //현재화면의 제어권자
                R.layout.row,
                LiStore);
        final ListView lvmovies = findViewById(R.id.listView1);
        lvmovies.setAdapter(adapter);
        lvmovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //상세정보 화면으로 이동하기 (Intent 기재 필수)
                // 1. 다음화면 만든다.
                // 2. AndroidManifest.xml 에 화면을 등록할 것.
                // 3. Intent 객체를 생성하여 만든다.
                Intent intent = new Intent(
                        getApplicationContext(),
                        TabActivity.class
                );


                intent.putExtra("MediaNumber", LiStore.get(position).title);
                intent.putExtra("L_Subject",SubjectNumber);
                intent.putExtra("L_Grade",GradeNumber);
                startActivity(intent);

            }
        });

    }
    class MyAdapter extends BaseAdapter{ // 리스트뷰 응용을 위해서는 추상클래스인 BaseAdapter 클래스를 필수로 상속시켜야함.
        Context context;
        int layout;
        ArrayList <ListStore> LiStore;
        LayoutInflater inf;
        public MyAdapter(Context context, int layout, ArrayList<ListStore> LiStore){
            this.context = context;
            this.layout = layout;
            this.LiStore = LiStore;
            inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount(){ // 데이터의 총 개수를 검수한다.
            return LiStore.size();
        }
        @Override
        public Object getItem(int position){ // 해당 postion의 데이터를 반환해준다.
            return LiStore.get(position);
        }
        @Override
        public long getItemId(int position){
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if (convertView==null){
                convertView = inf.inflate(layout, null);
            }
            TextView Title=(TextView)convertView.findViewById(R.id.title);
            TextView Subtitle=(TextView)convertView.findViewById(R.id.subtitle);
            TextView ImageNum=(TextView)convertView.findViewById(R.id.imagenum);
            TextView ImageSub=(TextView)convertView.findViewById(R.id.imagesub);

            ListStore m=LiStore.get(position);
            Title.setText(m.title);
            Subtitle.setText(m.subtitle);
            ImageNum.setText(m.ImageNum);
            ImageSub.setText(m.ImageSub);



            return  convertView;
        }

    }

    class ListStore{ // 리스트 추가하면 여기서 클래스가 만들어져서 추가됨.
        String title = ""; // 메인리스트 이름
        String subtitle = ""; //시놉시스 서브타이틀
        String ImageNum = "";
        String ImageSub = "";
        public ListStore(String title, String subtitle, String ImageNum, String ImageSub){
            this.title = title;
            this.subtitle = subtitle;
            this.ImageNum = ImageNum;
            this.ImageSub = ImageSub;
        }




    }
    public String ReadTextFile(String path){
        StringBuffer strBuffer = new StringBuffer();
        try {
            InputStream is = new FileInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line="";
            while((line=reader.readLine())!=null){
                strBuffer.append(line+"\n");
            }

            reader.close();
            is.close();
        }
        catch (IOException e){
            e.printStackTrace();
            return "";
        }
        return  strBuffer.toString();
    }




}


