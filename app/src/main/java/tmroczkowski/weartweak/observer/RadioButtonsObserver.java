package tmroczkowski.weartweak.observer;

import java.util.Observable;
import java.util.Observer;

import tmroczkowski.weartweak.service.WakeManager;
import tmroczkowski.weartweak.view.RadioButtonsManager;

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
