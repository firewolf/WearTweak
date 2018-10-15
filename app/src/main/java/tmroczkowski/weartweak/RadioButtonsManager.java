package tmroczkowski.weartweak;

import android.app.Activity;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class RadioButtonsManager extends Observable {

    int checkedId;

    public final static Map<Integer, Long> mapper = new HashMap<Integer, Long>() {{
        put(R.id.radioButton5, (long) 5 * 1000);
        put(R.id.radioButton30, (long) 30 * 1000);
        put(R.id.radioButton60, (long) 60 * 1000);
    }};

    public RadioButtonsManager (Activity activity, int defaultRadioButton) {

        RadioGroup radioGroup = activity.findViewById (R.id.radioGroup1);
        radioGroup.check (defaultRadioButton);
        this.checkedId = defaultRadioButton;

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //setChanged();
                //notifyObservers(mapper.get (checkedId));
                setCheckedId(checkedId);
            }
        });

    }

    private void setCheckedId (int checkedId) {
        this.checkedId = checkedId;
    }

    public int getCheckedId () {
        return checkedId;
    }

}
