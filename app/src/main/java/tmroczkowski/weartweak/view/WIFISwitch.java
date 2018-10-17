package tmroczkowski.weartweak.view;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import tmroczkowski.weartweak.R;

public class WIFISwitch {

    public WIFISwitch (Context context) {

        final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        Switch switchWI = ((Activity) context).findViewById(R.id.switchWI);

        switchWI.setChecked(wifiManager.isWifiEnabled());
        switchWI.setOnCheckedChangeListener((buttonView, isChecked) -> wifiManager.setWifiEnabled (isChecked));
    }
}
