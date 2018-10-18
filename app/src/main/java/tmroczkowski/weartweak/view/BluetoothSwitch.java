package tmroczkowski.weartweak.view;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
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
            imageButton.setPressed(getState());
            imageButton.setOnClickListener(v -> {
                setState(!getState());
                v.setPressed(getState());
                setOnCheckedChangeListener(getState(), btAdapter);
            });
        } else {
            ((ViewGroup) imageButton.getParent ()).removeView(imageButton);
        }
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
