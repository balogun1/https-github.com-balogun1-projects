package com.example.balogunal_amin.rrsfeeds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by balogunal-amin on 2016-04-13.
 */
public class OneFragment extends Fragment {
    EditText title,link,description;
    Button b1, b2;
    private HandleXml obj;
    private String url= "http://www.sciencemag.org/rss/news_current.xml";

    public OneFragment() {
        // Required empty public constructor
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);

    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        b1 = (Button)getActivity().findViewById(R.id.button6);
        title =(EditText)getActivity().findViewById(R.id.textView2);
        link = (EditText)getActivity().findViewById(R.id.textView3);
        description = (EditText) getActivity().findViewById(R.id.textView4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj = new HandleXml(url);
                obj.fetchXML();

                while(obj.parsingComplete);
                title.setText(obj.getTitle());
                link.setText(obj.getLink());
                description.setText(obj.getDescription());
            }
        });
        b2 = (Button)getActivity().findViewById(R.id.button7);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(in);


            }
        });
        /*Button newPage = (Button) getActivity().findViewById(R.id.button1);
        newPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),
                        MainActivity.class);
                getActivity().startActivity(intent);
            }
        });*/
    }
}
