package tmroczkowski.weartweak.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import tmroczkowski.weartweak.helper.Common;

public class Timeout {

    public static long DEFAULT_TIMEOUT = 5 * 1000L;

    Context context;

    public Timeout (Context context) {
        this.context = context;
    }

    public long read () {
        return Common.getPreferences(context).getLong("timeout", Timeout.DEFAULT_TIMEOUT);
    }

    private SharedPreferences.Editor write (long timeout) {

        SharedPreferences.Editor editor = Common.getPreferences(context).edit();
        editor.putLong ("timeout", timeout);
        return editor;
    }

    public void applyWrite (long timeout) {
        this.write (timeout).apply ();
    }

    public void commitWrite (long timeout) {
        this.write(timeout).commit();
    }

}
