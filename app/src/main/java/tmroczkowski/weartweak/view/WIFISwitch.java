package tmroczkowski.weartweak.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import tmroczkowski.weartweak.R;

public class WIFISwitch {

    private boolean state;

    private WifiManager wifiManager;

    public WIFISwitch (Context context) {

        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        ImageButton imageButton = ((Activity) context).findViewById(R.id.buttonWifi);

        state = wifiManager.isWifiEnabled();

        changeImage(state, imageButton, context);
        imageButton.setOnClickListener(v -> setOnClickListener (getState (), imageButton, context));
    }

    private boolean getState () {
        return state;
    }

    private void setOnClickListener (boolean state, ImageButton imageButton, Context context) {
        this.state = !state;
        changeImage(this.state, imageButton, context);
        wifiManager.setWifiEnabled(this.state);
    }

    private void changeImage (boolean state, ImageButton imageButton, Context context) {

        int drawable = state ? R.drawable.ic_outline_wifi_24px : R.drawable.ic_outline_wifi_off_24px;
        int color = state ? R.color.titleButtonCheckedBorder : R.color.semitransparent_grey;

        ((GradientDrawable) imageButton.getBackground()).setStroke(1, context.getColor (color));

        imageButton.setImageDrawable(context.getDrawable(drawable));
    }
}
