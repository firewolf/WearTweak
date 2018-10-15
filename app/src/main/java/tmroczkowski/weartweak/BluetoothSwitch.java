package tmroczkowski.weartweak;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

public class BluetoothSwitch {

    public BluetoothSwitch (Activity activity) {

        Switch switchBT = activity.findViewById(R.id.switchBT);

        final BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (btAdapter != null) {

            switchBT.setChecked(btAdapter.isEnabled());

            switchBT.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        btAdapter.enable();
                    } else {
                        btAdapter.disable ();
                    }
                }
            });
        } else {
            ((ViewGroup) switchBT.getParent ()).removeView(switchBT);
            //remove switch
        }
    }
}
