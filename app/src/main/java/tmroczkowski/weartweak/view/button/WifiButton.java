package tmroczkowski.weartweak.view.button;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.widget.ImageButton;

import tmroczkowski.weartweak.R;

public class WifiButton extends Button {

    public WifiButton(Context context) {

        super(context, R.id.buttonWifi, new int [][]{
                {R.drawable.ic_outline_wifi_24px, R.color.titleButtonCheckedBorder},
                {R.drawable.ic_outline_wifi_off_24px, R.color.semitransparent_grey}
        });

        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        this.setState(wifiManager.isWifiEnabled());
        this.updateView();

        this.button.setOnClickListener(
                v -> setOnClickListener (
                        state -> wifiManager.setWifiEnabled(state)));
    }
}
