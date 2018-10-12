package tmroczkowski.weartweak;

import java.util.Observable;
import java.util.Observer;

public class SwitchObserver implements Observer {

    WakeManager wakeManager;

    public SwitchObserver (WakeManager wakeManager) {
        this.wakeManager = wakeManager;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.wakeManager.setEnabled ((boolean) arg);
    }
}
