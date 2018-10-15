package tmroczkowski.weartweak;

import java.util.Observable;
import java.util.Observer;

public class RadioButtonObserver implements Observer {

    WakeManager wakeManager;

    public RadioButtonObserver (WakeManager wakeManager) {
        this.wakeManager = wakeManager;
    }

    @Override
    public void update(Observable o, Object arg) {
        wakeManager.setTimeout ((long) arg);
    }
}
