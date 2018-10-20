package tmroczkowski.weartweak.view;

import android.app.Activity;
import android.content.Context;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import tmroczkowski.weartweak.R;
import tmroczkowski.weartweak.preferences.Timeout;

public class TimeoutRadioButtons {

    public interface ChangeListener {
        void action (long timeout);
    }

    Timeout timeout;

    ChangeListener changeListener;

    public final static Map<Integer, Long> mapper = new HashMap<Integer, Long>() {{
        put(R.id.radioButtonOff, (long) -1);
        put(R.id.radioButton30, (long) 30 * 1000);
        put(R.id.radioButton60, (long) 60 * 1000);
        put(R.id.radioButton120, (long) 120 * 1000);
        put(R.id.radioButton8, (long) 0);
    }};

    public TimeoutRadioButtons(Context context, long defaultTimeout, ChangeListener changeListener) {

        int defaultRadioButton = getDefaultRadioButton (defaultTimeout);
        this.changeListener = changeListener;

        RadioGroup radioGroup1 = ((Activity) context).findViewById (R.id.radioGroup1);
        RadioGroup radioGroup2 = ((Activity) context).findViewById (R.id.radioGroup2);

        timeout = new Timeout(context);

        radioGroup1.setOnCheckedChangeListener((group, checkedId)-> setOnCheckedChangeListener (group, radioGroup2, checkedId));
        radioGroup2.setOnCheckedChangeListener((group, checkedId)-> setOnCheckedChangeListener (group, radioGroup1, checkedId));

        if (IntStream.of(new int [] {R.id.radioButton120, R.id.radioButton8}).anyMatch(x -> x == defaultRadioButton)){
            radioGroup2.check(defaultRadioButton);
        } else {
            radioGroup1.check(defaultRadioButton);
        }
    }

    private void setOnCheckedChangeListener (RadioGroup group, RadioGroup radioGroup, int checkedId) {
        if (checkedId != -1) {
            radioGroup.clearCheck();
            group.check(checkedId);
            long timeoutVal = mapper.get(checkedId);
            timeout.applyWrite(timeoutVal);
            this.changeListener.action (timeoutVal);
        }
    }

    private int getDefaultRadioButton(long defaultTimeout) {

        Set <Integer> keys = mapper.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), defaultTimeout))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        return keys.size() > 0 ? keys.iterator().next() : R.id.radioButtonOff;
    }
}
