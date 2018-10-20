package tmroczkowski.weartweak.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import tmroczkowski.weartweak.R;
import tmroczkowski.weartweak.helper.Common;

public class TimeoutRadioButtons {

    public final static Map<Integer, Long> mapper = new HashMap<Integer, Long>() {{
        put(R.id.radioButton5, (long) 5 * 1000);
        put(R.id.radioButton30, (long) 30 * 1000);
        put(R.id.radioButton60, (long) 60 * 1000);
        put(R.id.radioButton8, (long) 0);
    }};

    public TimeoutRadioButtons(Context context, long defaultTimeout) {

        int defaultRadioButton = getDefaultRadioButton (defaultTimeout);

        RadioGroup radioGroup = ((Activity) context).findViewById (R.id.radioGroup1);
        radioGroup.setOnCheckedChangeListener((group, checkedId)-> saveTimeout (checkedId, context));
        radioGroup.check(defaultRadioButton);
    }

    private int getDefaultRadioButton(long defaultTimeout) {
        Set <Integer> keys = mapper.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), defaultTimeout))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        return keys.size() > 0 ? keys.iterator().next() : R.id.radioButton5;
    }

    private void saveTimeout (int checkedId, Context context) {
        SharedPreferences.Editor editor = Common.getPreferences(context).edit();
        editor.putLong ("timeout", mapper.get (checkedId));
        editor.apply();
    }
}
