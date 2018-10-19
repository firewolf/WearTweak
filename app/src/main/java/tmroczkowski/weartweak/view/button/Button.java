package tmroczkowski.weartweak.view.button;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.wifi.WifiManager;
import android.widget.ImageButton;

import tmroczkowski.weartweak.R;

public abstract class Button {

    protected interface Listener {
        void onClickListener (boolean state);
    }

    protected boolean state;

    protected int[][] drawables;

    protected ImageButton button;

    protected Context context;

    public Button (Context context, int buttonId, int [][] drawables) {
        this.context = context;
        this.drawables = drawables;
        this.button = ((Activity) context).findViewById(buttonId);
    }

    protected boolean isState () {
        return state;
    }

    protected void setState (boolean state) {
        this.state = state;
    }

    protected void setOnClickListener (Listener listener) {
        boolean state = !this.isState();

        this.setState(state);
        this.updateView ();
        listener.onClickListener(state);
    }

    protected void updateView () {

        int index = this.isState() ? 0 : 1;

        button.setImageDrawable(context.getDrawable(drawables [index][0]));
        ((GradientDrawable) button.getBackground()).setStroke(1, context.getColor (drawables [index][1]));

    }
}
