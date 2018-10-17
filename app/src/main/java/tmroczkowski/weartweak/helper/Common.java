package tmroczkowski.weartweak.helper;

import android.content.Context;
import android.content.SharedPreferences;

import tmroczkowski.weartweak.R;

public class Common {

    public static SharedPreferences getPreferences (Context context) {
        return context.getSharedPreferences(context.getString (R.string.preferencesFile), Context.MODE_PRIVATE);
    }
}
