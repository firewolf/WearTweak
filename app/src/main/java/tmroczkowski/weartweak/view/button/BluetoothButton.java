package tmroczkowski.weartweak.view.button;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.view.ViewGroup;

import tmroczkowski.weartweak.R;

public class BluetoothButton extends Button {

    public BluetoothButton(Context context) {

        super(context, R.id.buttonBluetooth, new int [][]{
                {R.drawable.ic_outline_bluetooth_24px, R.color.titleButtonCheckedBorder},
                {R.drawable.ic_outline_bluetooth_disabled_24px, R.color.semitransparent_grey}
        });

        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (btAdapter != null) {
            this.setState(btAdapter.isEnabled());
            this.updateView();

            this.button.setOnClickListener(
                    v -> setOnClickListener (
                            state -> setBlutetoothEnabled(btAdapter, state)));
        } else {
            ((ViewGroup) this.button.getParent ()).removeView(this.button);
        }
    }

    private void setBlutetoothEnabled (BluetoothAdapter btAdapter, boolean state) {
        if (state) {
            btAdapter.enable ();
        } else {
            btAdapter.disable();
        }
    }
}
