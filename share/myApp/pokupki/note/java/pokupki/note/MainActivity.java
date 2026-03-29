package pokuki.note;

import android.content.Intent;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.text.Editable;
import android.view.View;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.util.Log;
import java.io.*;

public class MainActivity extends Activity {

    ArrayList<String> myShopping = new ArrayList<String>(20);
    private final static String TAG = "MainActivity";
    private final static String FILE_NAME = "Merchandises.txt";
    boolean saved = false;
    View item;
    String merchandise;
    CheckBox cbName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myShopping = ReadShoppingList(FILE_NAME);
        int size = myShopping.size();
        if(size != 0){
            for(int i =0; i < size; i++){
                addItemToScreen(myShopping.get(i));
            }
        }

    }

    public void onClick(View view) {
        Log.d(TAG, "onClick");

        String merchandise;

        EditText etMerchandise = (EditText)findViewById(R.id.etMerchandise);
        if(view.getId() == R.id.add){
            /*
             * Нажата кнопка "Добавить"
             */
            Log.d(TAG, "ADD button");
            LinearLayout newMerchandise = (LinearLayout) findViewById(R.id.newMerchandise);
            newMerchandise.setVisibility(View.VISIBLE);
            newMerchandise.requestFocus();
            etMerchandise.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            /*
                Нажата кнопка "Done" или "Enter" на клавиатуре.
            */
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Log.d(TAG, "IME_ACTION_DONE");
                    LinearLayout newMerchandise = (LinearLayout) findViewById(R.id.newMerchandise);
                    newMerchandise.setVisibility(View.GONE);
                }
                return false;
            }
        });
        }else if(view.getId() == R.id.ok){
            /*
             * Нажата кнопка "Ok"
             */
            Log.d(TAG, "OK button");
            // LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
            // LayoutInflater ltInflater = getLayoutInflater();
            merchandise = etMerchandise.getText().toString();
            myShopping.add(merchandise);
            etMerchandise.setText("");
            Log.d(TAG, "merchandise: " + merchandise);
            // item = ltInflater.inflate(R.layout.item, linLayout, false);
            // cbName = (CheckBox) item.findViewById(R.id.itemName);
            // cbName.setText(merchandise);
            // item.getLayoutParams().width = LayoutParams.MATCH_PARENT;
            // linLayout.addView(item);
            addItemToScreen(merchandise);
            Toast toast = Toast.makeText(this, "Добавлено в список", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }else if(view.getId() == R.id.clear){
            /*
             * Нажата кнопка "Очистить"
             */
            Log.d(TAG, "CLEAR button");
            setContentView(R.layout.activity_main);
            deleteFile(FILE_NAME);
            int size = myShopping.size();
            for(int i = 0; i < size; i ++){
                myShopping.remove((size - 1) - i);
            }
        }

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        Log.d(TAG, "size: " + myShopping.size());
        // deleteFile(FILE_NAME);
        if(myShopping.size() !=0 ){
            saveListMerchandise(myShopping);
        }

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
        // Log.d(TAG, "size: " + myShopping.size());
        // myShopping = ReadShoppingList(FILE_NAME);
        // int size = myShopping.size();
        // if(size != 0){
        //     for(int i =0; i < size; i++){
        //         addItemToScreen(myShopping.get(i));
        //     }
        // }
        // if(!saved) return;

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
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "onRestart");
        Log.d(TAG, "size: " + myShopping.size());
    }

    /*
        Сохраняем данные в файл.
    */
    public void saveListMerchandise(ArrayList<String> listMerchandise){

        Log.d(TAG, "saved text");

        FileOutputStream fos = null;
        int size = listMerchandise.size();
        String str;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            for(int i =0; i < size; i++){
                str = listMerchandise.get(i) + ":";
                fos.write(str.getBytes());
            }

            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
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

    /*
        Добавляем товар на экран.
    */
    public void addItemToScreen(String merchandise){
        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        LayoutInflater ltInflater = getLayoutInflater();
        item = ltInflater.inflate(R.layout.item, linLayout, false);
        cbName = (CheckBox) item.findViewById(R.id.itemName);
        cbName.setText(merchandise);
        item.getLayoutParams().width = LayoutParams.MATCH_PARENT;
        linLayout.addView(item);
    }

    /*
        Открываем файл для чтения списка товаров.
        Функция возвращает список ArrayList<String>.
    */
    ArrayList<String> ReadShoppingList(String fileName){
        FileInputStream fin = null;
        String text = null;
        ArrayList<String> arrList = new ArrayList<String>(20);
        try {
            fin = openFileInput(fileName);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            text = new String (bytes);
            String[] words = text.split(":");
            for(String word : words){
                arrList.add(word);
                // addItemToScreen(word);
            }
        //     // Log.d(TAG, "openText: " + text);
        }
        catch(IOException ex) {

            // Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Нет сохранненного списка", Toast.LENGTH_SHORT).show();
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
        return arrList;
    }
}
