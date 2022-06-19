package xyz.mrym.applibs.dialogviewlib.widget;

import android.content.Context;

public class ShowDialog {

    private MyBaseDialog myBaseDialog;

    private volatile static ShowDialog INSTANCE = null;

    public static ShowDialog getInstance() {
        if (INSTANCE == null) {
            synchronized (ShowDialog.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ShowDialog();
                }
            }
        }
        return INSTANCE;
    }

    public MyBaseDialog getMyBaseDialog() {
        return myBaseDialog;
    }

    public static void destoryShowDialog() {
        INSTANCE = null;
    }

    public void showNewDialog(Context context, String title, String alertMessage, String leftButton, String rightButton,
                              MyBaseDialog.onNoOnclickListener onNoOnclickListener,
                              MyBaseDialog.onYesOnclickListener onYesOnclickListener) {
        myBaseDialog = new MyBaseDialog(context);
        myBaseDialog.setTitle(title);
        myBaseDialog.setNoOnclickListener(leftButton, onNoOnclickListener);
        myBaseDialog.setMessage(alertMessage);
        myBaseDialog.setYesOnclickListener(rightButton, onYesOnclickListener);
        myBaseDialog.show();
    }

}
