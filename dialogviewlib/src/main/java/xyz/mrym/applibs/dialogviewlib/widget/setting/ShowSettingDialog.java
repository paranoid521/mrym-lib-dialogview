package xyz.mrym.applibs.dialogviewlib.widget.setting;

import android.content.Context;

public class ShowSettingDialog {

    private MySettingDialog mySettingDialog;

    public MySettingDialog getMySettingDialog() {
        return mySettingDialog;
    }

    private volatile static ShowSettingDialog INSTANCE = null;

    public static ShowSettingDialog getInstance() {
        if (INSTANCE == null) {
            synchronized (ShowSettingDialog.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ShowSettingDialog();
                }
            }
        }
        return INSTANCE;
    }

    public void showSettingDialog(Context context,
                                  MySettingDialog.onRadioOnclickListenerApi onRadioOnclickListenerApi,
                                  MySettingDialog.onYesOnclickListener onYesOnclickListener,
                                  String defaultRadioButton) {
        mySettingDialog = new MySettingDialog(context);
        mySettingDialog.setRadioOnclickListener(onRadioOnclickListenerApi);
        mySettingDialog.setYesOnclickListener(onYesOnclickListener);
        mySettingDialog.setDefaultRadioButton(defaultRadioButton);
        mySettingDialog.show();
    }

}
