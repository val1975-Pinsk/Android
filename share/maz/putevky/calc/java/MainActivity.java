package maz.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.text.TextWatcher;
import android.text.Editable;
import android.view.View;
import android.content.Intent;

public class MainActivity extends Activity {

    Double distanceByPinsk = 0.0;
    Double distanceByCity = 0.0;
    Double distanceByRout = 0.0;
    Double lineNormaCity = 0.0;
    Double webasto = 0.0;
    String cityName = "";

    Double consumptionPinsk = 0.0;
    Double consumptionCity = 0.0;
    Double consumptionRout = 0.0;
    String str;
    Double totalDistance = 0.0;
    Double totalConsumption = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText et_Direct = findViewById(R.id.etDirect);
        et_Direct.addTextChangedListener(new TextWatcher(){

            public void afterTextChanged(Editable s) {};

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {};

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView tv_distanceByCity = findViewById(R.id.distanceByCity);
                tv_distanceByCity.setText(s);
                cityName = s.toString();
                // TextView tv_consumptionCity = findViewById(R.id.consumptionCity);
                // tv_consumptionCity.setText("Расход по городу " + s);
                // tv_consumptionCityText = tv_consumptionCity.getText() + " ";
            }
        });

        EditText et_DistanceByPinsk = findViewById(R.id.etDistanceByPinsk);
        et_DistanceByPinsk.addTextChangedListener(new TextWatcher(){

            public void afterTextChanged(Editable s) {
                Double value;
                // TextView tv_consumptionPinsk = findViewById(R.id.consumptionPinsk);
                str = s.toString();
                if(str.length() == 0){
                    value = 0.0;
                }else{
                    value = Double.parseDouble(str);
                }
                distanceByPinsk = value;
                // value = value * 33.0 / 100;
                // consumptionPinsk = value;
                // str = "Расход по Пинску " + String.format("%.2f",value);
                // tv_consumptionPinsk.setText(str);
            };

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {};

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        EditText et_DistanceByRout = findViewById(R.id.etDistanceByRout);
        et_DistanceByRout.addTextChangedListener(new TextWatcher(){

            public void afterTextChanged(Editable s) {
                Double value;
                // TextView tv_consumptionRout = findViewById(R.id.consumptionRout);
                str = s.toString();
                if(str.length() == 0){
                    value = 0.0;
                }else{
                    value = Double.parseDouble(str);
                }
                distanceByRout = value;
                // value = value * 30.4 / 100;
                // consumptionRout = value;
                // str = "Расход по трассе " + String.format("%.2f",value);
                // tv_consumptionRout.setText(str);
            };

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {};

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        RadioGroup rg_popularChoice = findViewById(R.id.popularChoice);
        rg_popularChoice.setOnCheckedChangeListener((radiogroup, id)->{
            RadioButton rb = findViewById(id);
            switch(rb.getText().toString()){
                case "население 100-300 тыс.":
                    lineNormaCity = 33.0;
                    break;
                case "население 300тыс-1млн.":
                    lineNormaCity = 33.6;
                    break;
                case "население 1-3 млн":
                    lineNormaCity = 35.2;
                    break;
            }
            // Double value;
            // TextView tv_consumptionCity = findViewById(R.id.consumptionCity);
            // value = distanceByCity * lineNormaCity / 100;
            // consumptionCity = value;
            // str = String.format("%.2f",value);
            // str = tv_consumptionCityText + String.format("%.2f",value);
            // tv_consumptionCity.setText(str);
            //
            // TextView tv_total = findViewById(R.id.totalDistance);
            // tv_total.setText("Общий пробег: " + String.format("%.2f", distanceByPinsk + distanceByRout + distanceByCity));
            //
            // tv_total = findViewById(R.id.totalConsumption);
            // tv_total.setText("Ощий расход: " + String.format("%.2f", consumptionPinsk + consumptionRout + consumptionCity));
        });

        EditText et_DistanceByCity = findViewById(R.id.etDistanceByCity);
        et_DistanceByCity.addTextChangedListener(new TextWatcher(){

            public void afterTextChanged(Editable s) {
                Double value;
                str = s.toString();
                if(str.length() == 0){
                    value = 0.0;
                }else{
                    value = Double.parseDouble(str);
                }
                distanceByCity = value;
            };

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {};

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        EditText et_webasto = findViewById(R.id.etWebasto);
        et_webasto.addTextChangedListener(new TextWatcher(){

            public void afterTextChanged(Editable s) {
                Double value;
                str = s.toString();
                if(str.length() == 0){
                    value = 0.0;
                }else{
                    value = Double.parseDouble(str);
                }
                webasto = value;
            };

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {};

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
    }

    public void onClick(View v){
        Intent intent = new Intent(this, ReportActivity.class);
        intent.putExtra("distByPinsk", distanceByPinsk);
        intent.putExtra("distByRout", distanceByRout);
        intent.putExtra("distByCity", distanceByCity);
        intent.putExtra("webasto", webasto);
        intent.putExtra("cityName", cityName);
        intent.putExtra("lineNormaCity", lineNormaCity);
        startActivity(intent);
    }
}
