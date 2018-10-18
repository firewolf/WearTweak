package tmroczkowski.weartweak.view;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import tmroczkowski.weartweak.R;

public class WIFISwitch {

    public WIFISwitch (Context context) {

        final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        ImageButton imageButton = ((Activity) context).findViewById(R.id.buttonWifi);

        imageButton.setPressed(wifiManager.isWifiEnabled());
        imageButton.setOnClickListener(v -> {
            v.setPressed (!v.isPressed());
            wifiManager.setWifiEnabled(v.isPressed());
        });
    }
}
