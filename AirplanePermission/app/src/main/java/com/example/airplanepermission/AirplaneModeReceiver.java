package com.example.airplanepermission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class AirplaneModeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Avião sem asa, fogueira sem brasa, sou eu assim sem você.", Toast.LENGTH_LONG).show();

        SharedPreferences sp = context.getSharedPreferences("people", Context.MODE_PRIVATE);
        float peopleAverage = sp.getFloat("people_average", 0);

        Intent it = new Intent(context, PeopleAverageActivity.class);
        it.putExtra("average", peopleAverage);
        context.startActivity(it);
    }
}
