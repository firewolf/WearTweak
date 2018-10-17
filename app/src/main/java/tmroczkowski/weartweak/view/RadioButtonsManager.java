package tmroczkowski.weartweak.view;

import android.app.Activity;
import android.content.Intent;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import tmroczkowski.weartweak.R;
import tmroczkowski.weartweak.WearTweakData;
import tmroczkowski.weartweak.service.WakeManager;

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

        radioGroup.setOnCheckedChangeListener((RadioGroup group, int checkedId)-> {

            setCheckedId(checkedId);

            WearTweakData wearTweakData = (WearTweakData) activity.getApplicationContext();
            wearTweakData.setTimeout(mapper.get (checkedId));
        });

    }

    private void setCheckedId (int checkedId) {
        this.checkedId = checkedId;
    }

    public int getCheckedId () {
        return checkedId;
    }

}
