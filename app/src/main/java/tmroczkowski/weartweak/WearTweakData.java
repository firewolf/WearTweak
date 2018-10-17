package tmroczkowski.weartweak;

import android.app.Application;

public class WearTweakData extends Application {

    private long timeout;

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public long getTimeout() {
        return timeout;
    }
}
