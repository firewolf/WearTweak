package tmroczkowski.weartweak.view;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import tmroczkowski.weartweak.R;

public class BluetoothSwitch {

    public BluetoothSwitch (Activity activity) {

        Switch switchBT = activity.findViewById(R.id.switchBT);

        final BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (btAdapter != null) {

            switchBT.setChecked(btAdapter.isEnabled());
            switchBT.setOnCheckedChangeListener ((buttonView, isChecked) -> setOnCheckedChangeListener (isChecked, btAdapter));
        } else {
            ((ViewGroup) switchBT.getParent ()).removeView(switchBT);
        }
    }

    private void setOnCheckedChangeListener (boolean isChecked, BluetoothAdapter btAdapter) {
        if (isChecked) {
            btAdapter.enable ();
        } else {
            btAdapter.disable();
        }
    }
}
