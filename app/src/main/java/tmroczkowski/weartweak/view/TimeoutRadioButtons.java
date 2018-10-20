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

    int checkedId;

    SharedPreferences.Editor editor;

    public final static Map<Integer, Long> mapper = new HashMap<Integer, Long>() {{
        put(R.id.radioButton5, (long) 5 * 1000);
        put(R.id.radioButton30, (long) 30 * 1000);
        put(R.id.radioButton60, (long) 60 * 1000);
        put(R.id.radioButton8, (long) 0);
    }};

    public TimeoutRadioButtons(Context context, int defaultRadioButton) {

        this.checkedId = defaultRadioButton;
        this.editor = Common.getPreferences(context).edit();

        RadioGroup radioGroup = ((Activity) context).findViewById (R.id.radioGroup1);
        radioGroup.setOnCheckedChangeListener((group, checkedId)-> setOnCheckedChangeListener (checkedId));
        if (mapper.containsKey(defaultRadioButton)) {
            radioGroup.check(defaultRadioButton);
        }
    }

    private void setOnCheckedChangeListener (int checkedId) {
        this.setCheckedId (checkedId);
        this.saveTimeout (checkedId);
    }

    public void saveState() {
        editor.putInt ("timeoutId", checkedId);
        editor.apply ();
    }

    private void setCheckedId (int checkedId) {
        this.checkedId = checkedId;
    }

    private void saveTimeout (int checkedId) {
        editor.putLong ("timeout", mapper.get (checkedId));
        editor.apply();
    }
}
