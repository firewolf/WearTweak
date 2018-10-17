package tmroczkowski.weartweak.view;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import tmroczkowski.weartweak.R;

public class WIFISwitch {

    private WifiManager wifiManager;

    public WIFISwitch (Activity activity) {

        wifiManager = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);

        Switch switchWI = activity.findViewById(R.id.switchWI);

        switchWI.setChecked(wifiManager.isWifiEnabled());
        switchWI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d (this.getClass().toString(), "onCheckedChanged: [" + isChecked + "]");

                getWifiManager().setWifiEnabled(isChecked);
            }
        });

    }

    private WifiManager getWifiManager () {
        return wifiManager;
    }
}
