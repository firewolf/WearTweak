package tmroczkowski.weartweak;

import android.os.PowerManager;

import java.util.Observable;
import java.util.Observer;

public class RadioButtonsObserver implements Observer {

    WakeManager wakeManager;

    public RadioButtonsObserver (WakeManager wakeManager) {
        this.wakeManager = wakeManager;
    }


    @Override
    public void update(Observable o, Object arg) {
        this.wakeManager.setTimeout (RadioButtonsManager.mapper.get((Integer) arg));
    }
}
