package tmroczkowski.weartweak.observer;

import java.util.Observable;
import java.util.Observer;

import tmroczkowski.weartweak.service.WakeManager;

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
