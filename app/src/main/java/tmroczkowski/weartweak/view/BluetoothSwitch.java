package tmroczkowski.weartweak.view;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import tmroczkowski.weartweak.R;

public class BluetoothSwitch {

    boolean state;

    public BluetoothSwitch (Context context) {

        ImageButton imageButton = ((Activity) context).findViewById(R.id.buttonBluetooth);

        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (btAdapter != null) {

            setState(btAdapter.isEnabled());
            changeImage(getState(), imageButton, context);
            imageButton.setOnClickListener(v -> {
                setState(!getState());
                setOnCheckedChangeListener(getState(), btAdapter);
                changeImage(getState(), imageButton, context);
            });
        } else {
            ((ViewGroup) imageButton.getParent ()).removeView(imageButton);
        }
    }

    private void changeImage (boolean state, ImageButton imageButton, Context context) {

        int drawable = state ? R.drawable.ic_outline_bluetooth_24px : R.drawable.ic_outline_bluetooth_disabled_24px;
        int color = state ? R.color.titleButtonCheckedBorder : R.color.semitransparent_grey;

        ((GradientDrawable) imageButton.getBackground()).setStroke(1, context.getColor (color));

        imageButton.setImageDrawable(context.getDrawable(drawable));
    }

    private void setState (boolean state) {
        this.state = state;
    }

    private boolean getState () {
        return state;
    }

    private void setOnCheckedChangeListener (boolean isChecked, BluetoothAdapter btAdapter) {
        if (isChecked) {
            btAdapter.enable ();
        } else {
            btAdapter.disable();
        }
    }
}
