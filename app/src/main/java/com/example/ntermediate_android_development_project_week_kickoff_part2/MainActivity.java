package com.example.ntermediate_android_development_project_week_kickoff_part2;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.ntermediate_android_development_project_week_kickoff_part2.PlayerActivity.mediaPlayer;

public class MainActivity extends AppCompatActivity  {

    ListView myListViewForSongs;
    String[] items;
    EditText searchBar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /** searchBar = (EditText) findViewById(R.id.search_bar);

        searchBar.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!s.toString().isEmpty())
                {
                    search(s.toString());
                }
                else
                {
                    search("");
                }

            }
        }); **/


        myListViewForSongs = (ListView) findViewById(R.id.mySongListView);




        runtimePermission();

    }

   /** private void search(String s) {
        DownloadManager.Query query = databaseReference.orderByChild("name")
                .startAt(s)
                .endAt(s + "\uf8ff");

        query.
    }
**/


            public void runtimePermission(){
            Dexter.withActivity(this)
                    .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {

                            display();

                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {

                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                            token.continuePermissionRequest();
                        }
                    }).check();


    }



    public ArrayList<File> findSong(File file) {

        ArrayList<File> arrayList = new ArrayList<> ();

        File[] files= file.listFiles();

        for(File singleFile: files){
            if(singleFile.isDirectory() && !singleFile.isHidden()){
                arrayList.addAll(findSong(singleFile));
            }
            else {
                 if(singleFile.getName().endsWith (".mp3")||
                singleFile.getName().endsWith(".wav")) {

                     arrayList.add(singleFile);
                }

            }

        }
        return arrayList;



    }

    void display(){

        final ArrayList<File> mySongs = findSong(Environment.getExternalStorageDirectory());

        items = new String[mySongs.size()];

        for(int i=0;i<mySongs.size();i++) {
            items[i] = mySongs.get(i).getName().toString().replace(".mp3", "").replace(".wav", "");
        }

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        myListViewForSongs.setAdapter(myAdapter);

        myListViewForSongs.setOnItemClickListener(new AdapterView.OnItemClickListener()


        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                String songName = myListViewForSongs.getItemAtPosition(i).toString();

                startActivity(new Intent(getApplicationContext(),PlayerActivity.class)
                .putExtra("songs",mySongs).putExtra("songname", songName)
                .putExtra("pos",i));
            }

        });


    }
}

/** public void play_song(View v)
 {
 MediaPlayer  mediaPlayer = new MediaPlayer();
 try
 {
 mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/mediaplayer-53af3.appspot.com/o/im_yours.mp3?alt=media&token=01b8c61d-b54c-4b82-942d-4428d9faeaf9");
 mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
@Override
public void onPrepared(MediaPlayer mp) {
mp.start();
}
});
 mediaPlayer.prepare();
 }catch (IOException e)
 {
 e.printStackTrace();
 }
 *

 }**/
