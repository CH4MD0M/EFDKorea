package com.example.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;


public class PdfFragment extends Fragment
{
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {

        }
        else if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {

        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_pdf, container, false);

        Intent intent = getActivity().getIntent();
        PDFView pdfView = (PDFView) rootView.findViewById(R.id.pdfview);

        final String Language = intent.getStringExtra("L_Language"); //Language 호출
        final String GradeCode = intent.getStringExtra("L_Grade"); // GradeNumber 호출
        final String SubjectCode = intent.getStringExtra("L_Subject"); // SubjectNumber 호출
        final String MediaNumber = intent.getStringExtra("MediaNumber"); // MediaNumber 호출

        final String[] Testchar = {GradeCode, SubjectCode,  MediaNumber};
        String gradecode, medianame  = " ";

        if(Testchar[0].equals("GRADE1")){ gradecode = "1"; }
        else if(Testchar[0].equals("GRADE2")){gradecode = "2";}
        else if(Testchar[0].equals("GRADE3")){gradecode = "3";}
        else if(Testchar[0].equals("GRADE4")){gradecode = "4";}
        else if(Testchar[0].equals("GRADE5")){gradecode = "5";}
        else  {gradecode = "6";}

        medianame = MediaNumber.replaceAll("[^0-9]","");

        //TODO-------------------------------------------------------------------------------------------------------------------------------------------------------

        //TODO [PDF] 경로
        String PdfPath = "/storage/0000-0000/" + Language + "/" + GradeCode + "/" + SubjectCode + "/" + "PDF" + "/" + "Lesson" + gradecode + "-" + medianame + ".pdf";

        //TODO-------------------------------------------------------------------------------------------------------------------------------------------------------

        File file = new File(PdfPath);
        Uri uri = Uri.fromFile(file);
        pdfView.fromUri(uri).load();

        return rootView;
    }


}