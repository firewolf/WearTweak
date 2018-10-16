package tmroczkowski.weartweak;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;

import tmroczkowski.weartweak.view.BluetoothSwitch;
import tmroczkowski.weartweak.view.RadioButtonsManager;
import tmroczkowski.weartweak.view.WIFISwitch;

public class MainActivity extends WearableActivity {

    RadioButtonsManager radioButtonsManager;

    private int defaultRadioButton = R.id.radioButton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        defaultRadioButton = sharedPref.getInt("defaultRadioButton", defaultRadioButton);

        //new DisplayActionListener(this, wakeManager);
        radioButtonsManager = new RadioButtonsManager(this, defaultRadioButton);
        //radioButtonsManager.addObserver(new RadioButtonObserver(wakeManager));

        new WIFISwitch(this);
        new BluetoothSwitch(this);


//        WakeManager wakeManager = new WakeManager((PowerManager) this.getSystemService(Context.POWER_SERVICE));
//
//        BroadcastReceiver broadcastReceiver = new FaceBroadcastReceiver(wakeManager);
//        IntentFilter intentFilter = new IntentFilter(WatchFaceService.ACTION_REQUEST_STATE);
//        this.registerReceiver(broadcastReceiver, intentFilter);
//        broadcastReceiver.goAsync();

        setAmbientEnabled(); // Enables Always-on
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {

        super.onEnterAmbient(ambientDetails);
    }

    private void saveData () {

        SharedPreferences.Editor editor = this.getPreferences(Context.MODE_PRIVATE).edit();
        editor.putInt("defaultRadioButton", radioButtonsManager.getCheckedId ());
        editor.apply ();
    }

    @Override
    protected void onDestroy () {

//        Intent wearTweakService = new Intent(this, WearTweakIntentService.class);
//        Log.d("MainActivity", "onDestroy value: [" + RadioButtonsManager.mapper.get (radioButtonsManager.getCheckedId()) + "]");
//        wearTweakService.putExtra("timeout", RadioButtonsManager.mapper.get (radioButtonsManager.getCheckedId()));
//
//        startService(wearTweakService);

        this.saveData();
        super.onDestroy();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }
}
