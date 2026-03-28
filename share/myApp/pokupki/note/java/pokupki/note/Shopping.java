package pokuki.note;

import android.content.Intent;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CheckBox;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.text.Editable;
import android.view.View;
import android.util.Log;
import java.io.*;

public class Shopping extends Activity {

    private final static String TAG = "MainActivity_shop";
    String dataStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        createMerchandiseList();

        // dataStr = createActivity();

        // Bundle arguments = getIntent().getExtras();
        // // CheckBox cb_0 = (CheckBox)findViewById(R.id.chBox_0);
        // String str;
        // if(arguments!=null){
        //     int size = arguments.getInt("size");
        //     for(int i = 0; i < size; i ++){
        //         str = arguments.getString("str_"+i);
        //         View item = ltInflater.inflate(R.layout.item, linLayout, false);
        //         CheckBox cbName = (CheckBox) item.findViewById(R.id.itemName);
        //         cbName.setText(str);
        //         item.getLayoutParams().width = LayoutParams.MATCH_PARENT;
        //         linLayout.addView(item);
        //     }
        // }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        // saveText(dataStr);
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart");
        // createActivity();
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume");
        // setContentView(R.id.linLayout);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "onRestart");
        setContentView(R.id.linLayout);
        // setContentView(R.layout.activity_shopping);
    }

    public void createMerchandiseList(){
        Log.d(TAG, "in createMerchandiseList");
        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        LayoutInflater ltInflater = getLayoutInflater();
        View item;
        String merchandise;
        CheckBox cbName;

        item = ltInflater.inflate(R.layout.item, linLayout, false);
        Bundle arguments = getIntent().getExtras();
        int size = arguments.getInt("size");

        Log.d(TAG, "size: "+size);
        if(size != 0){
            for(int i = 0; i < size; i++){
                cbName = (CheckBox) item.findViewById(R.id.itemName);
                merchandise = arguments.getString("merchandise_" + i);
                Log.d(TAG, "merchandise: " + merchandise);
                cbName.setText(merchandise);
                item.getLayoutParams().width = LayoutParams.MATCH_PARENT;
                linLayout.addView(item);
            }
        }
        // for(int i = 0; i < size; i++){
        //     CheckBox cbName = (CheckBox) item.findViewById(R.id.itemName);
        //     merchandise = arguments.getString("merchandise_" + i);
        //     cbName.setText(merchandise);
        //     item.getLayoutParams().width = LayoutParams.MATCH_PARENT;
        //     linLayout.addView(item);
        // }
    }
    // public String createActivity(){
    //     LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
    //
    //     LayoutInflater ltInflater = getLayoutInflater();
    //
    //     // Bundle arguments = getIntent().getExtras();
    //     // Log.d(TAG, "size: " + arguments.getInt("size"));
    //     // CheckBox cb_0 = (CheckBox)findViewById(R.id.chBox_0);
    //     String str;
    //     View item;
    //     str = openText();
    //     String[] words = str.split(":");
    //     Log.d(TAG, "words size: " + words.length);
    //     for(String word : words){
    //         Log.d(TAG, "word: " + word);
    //         if(word != ""){
    //             item = ltInflater.inflate(R.layout.item, linLayout, false);
    //             CheckBox cbName = (CheckBox) item.findViewById(R.id.itemName);
    //             cbName.setText(word);
    //             item.getLayoutParams().width = LayoutParams.MATCH_PARENT;
    //             linLayout.addView(item);
    //         }
    //     }
    //     return str;
    // }

    // сохранение файла
    public void saveText(String str){

        Log.d(TAG, "saved text");

        // saved = true;
        FileOutputStream fos = null;
        // int size = listMerchandise.size();
        // String str;
        try {
            fos = openFileOutput("content.txt", MODE_PRIVATE);
            fos.write(str.getBytes());
            // for(int i =0; i < size; i++){
            //     str = listMerchandise.get(i) + ":";
            //     fos.write(str.getBytes());
            // }

            Toast.makeText(this, "Список сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String openText(){

        Log.d(TAG, "open text");

        FileInputStream fin = null;
        String text = null;

        try {
            fin = openFileInput("content.txt");
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            text = new String (bytes);
            // Log.d(TAG, "openText: " + text);
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return text;
    }

}
