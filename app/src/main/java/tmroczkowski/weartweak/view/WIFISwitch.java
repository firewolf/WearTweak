package tmroczkowski.weartweak.view;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.widget.CompoundButton;
import android.widget.Switch;

import tmroczkowski.weartweak.R;

public class WIFISwitch {

    public WIFISwitch (Activity activity) {

        Switch switchWI = activity.findViewById(R.id.switchWI);

        final WifiManager wifiManager = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);

        switchWI.setChecked(wifiManager.isWifiEnabled());


        switchWI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                wifiManager.setWifiEnabled(isChecked);
            }
        });

    }
}
