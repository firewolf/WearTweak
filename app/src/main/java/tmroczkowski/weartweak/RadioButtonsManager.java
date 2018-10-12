package tmroczkowski.weartweak;

import android.app.Activity;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class RadioButtonsManager extends Observable {

    public static Map <Integer, Integer> radioMapper = new HashMap<Integer, Integer>() {{
        put (R.id.radioButton5, 5 * 1000);
        put (R.id.radioButton20, 20 * 1000);
        put (R.id.radioButton60, 60 * 1000);
    }};

    public RadioButtonsManager (Activity main, int currentPosition) {

        RadioGroup radioGroup = main.findViewById (R.id.radioGroup1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                notifyObservers(checkedId);
            }
        });
    }
}
