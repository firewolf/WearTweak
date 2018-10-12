package tmroczkowski.weartweak;

import android.app.Activity;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Observable;

public class SwitchManager extends Observable {

    public SwitchManager (Activity main, boolean isChecked) {

        Switch switch1 = main.findViewById(R.id.switch1);
        switch1.setChecked(isChecked);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                notifyObservers(isChecked);
            }
        });
    }

}
