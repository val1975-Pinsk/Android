package maz.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ReportActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        // TextView tv_reportHeader = findViewById(R.id.reportHeader);
        // tv_reportHeader.setTextSize(40);

        Double distPinsk, distRoute, distCity, lineNormaCity, webasto;
        Bundle arguments = getIntent().getExtras();

        distPinsk = arguments.getDouble("distByPinsk");
        distRoute = arguments.getDouble("distByRout");
        distCity = arguments.getDouble("distByCity");
        lineNormaCity = arguments.getDouble("lineNormaCity");
        webasto = arguments.getDouble("webasto");
        String cityName = arguments.getString("cityName");

        double consumptionPinsk = distPinsk * 33.0 / 100;
        double consumptionRout = distRoute* 30.4 / 100;
        double consumptionCity = distCity * lineNormaCity / 100;
        double consumptionWebasto = webasto * 2;

        TextView tv_distByPinsk = findViewById(R.id.distanceByPinsk);
        tv_distByPinsk.setText(tv_distByPinsk.getText() + String.format("%.2f", distPinsk));

        TextView tv_distByRout = findViewById(R.id.distanceByRout);
        tv_distByRout.setText(tv_distByRout.getText() + String.format("%.2f", distRoute));

        TextView tv_distByCity = findViewById(R.id.distanceByCity);
        tv_distByCity.setText(cityName + " " + String.format("%.2f", distCity));


        Double total = distPinsk + distRoute + distCity;
        TextView tv_totalDistance = findViewById(R.id.totalDistance);
        tv_totalDistance.setText(tv_totalDistance.getText() + String.format("%.2f", total));

        TextView tv_consumptionPinsk = findViewById(R.id.consumptionPinsk);
        tv_consumptionPinsk.setText(tv_consumptionPinsk.getText() + String.format("%.2f", consumptionPinsk));

        TextView tv_consumptionRout = findViewById(R.id.consumptionRout);
        tv_consumptionRout.setText(tv_consumptionRout.getText() + String.format("%.2f", consumptionRout));

        TextView tv_consumptionCity= findViewById(R.id.consumptionCity);
        tv_consumptionCity.setText(cityName + " "  + String.format("%.2f", consumptionCity));

        TextView tv_consumptionWebasto = findViewById(R.id.consumptionWebasto);
        tv_consumptionWebasto.setText(tv_consumptionWebasto.getText() + String.format("%.2f", consumptionWebasto));

        total = consumptionCity + consumptionPinsk + consumptionRout + consumptionWebasto;
        TextView tv_totalConsumption = findViewById(R.id.totalConsumption);
        tv_totalConsumption.setText(tv_totalConsumption.getText()  + String.format("%.2f", total));
    }
}
