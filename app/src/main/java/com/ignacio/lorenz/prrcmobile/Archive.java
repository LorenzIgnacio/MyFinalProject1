package com.ignacio.lorenz.prrcmobile;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ami.fundapter.BindDictionary;
import com.ami.fundapter.FunDapter;
import com.ami.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.ArrayList;

public class Archive extends Fragment {

    private ArrayList<ClassListItems> userList;
    private ListView listView;
    private FunDapter<ClassListItems> adapter;


    public Archive() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_archive, container, false);
        listView =(ListView)view.findViewById(R.id.listView);

        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(getContext(),  new AsyncResponse(){
            @Override
            public void processFinish(String s)

            {
                userList = new JsonConverter<ClassListItems>().toArrayList(s, ClassListItems.class);
                BindDictionary<ClassListItems> dict = new BindDictionary<ClassListItems>();

                dict.addStringField(R.id.textSubject, new StringExtractor<ClassListItems>() {
                    @Override
                    public String getStringValue(ClassListItems item, int position) {
                        return "" + item.subject;
                    }
                });

                dict.addStringField(R.id.textStatus, new StringExtractor<ClassListItems>() {
                    @Override
                    public String getStringValue(ClassListItems item, int position) {
                        return "" + item.statuscode_id;
                    }
                });
                dict.addStringField(R.id.textDate, new StringExtractor<ClassListItems>() {
                    @Override
                    public String getStringValue(ClassListItems item, int position) {
                        return "" + item.final_action_date;
                    }
                });
                adapter = new FunDapter<ClassListItems>(getActivity(), userList, R.layout.fragment_archive_list, dict);
                listView.setAdapter(adapter);
            }
        });


        taskRead.execute("http://10.0.2.2:80/android/archive.php");
        return view;
    }
}