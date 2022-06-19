package xyz.mrym.applibs.dialogviewlib.widget.setting;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import xyz.mrym.applibs.dialogviewlib.R;


public class MySettingDialog extends Dialog {
    private Button yes;//确定按钮
    private RadioGroup settingRadioGroup;
    private String defaultRadioButton;
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    private onRadioOnclickListenerApi onRadioOnclickListenerApi;//确定按钮被点击了的监听器

    public void setDefaultRadioButton(String defaultRadioButton) {
        this.defaultRadioButton = defaultRadioButton;
    }

    /**
     * 设置确定按钮的显示内容和监听
     *
     * @param onYesOnclickListener
     */
    public void setYesOnclickListener(onYesOnclickListener onYesOnclickListener) {
        this.yesOnclickListener = onYesOnclickListener;
    }

    /**
     * 设置确定按钮的显示内容和监听
     *
     * @param onRadioOnclickListener
     */
    public void setRadioOnclickListener(onRadioOnclickListenerApi onRadioOnclickListener) {
        this.onRadioOnclickListenerApi = onRadioOnclickListener;
    }

    public MySettingDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_setting);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        yes = (Button) findViewById(R.id.yes);
        //初始化界面控件的事件，设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }
        });
        settingRadioGroup = (RadioGroup) findViewById(R.id.daoyin_setting);
        settingRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (onRadioOnclickListenerApi != null) {
                    onRadioOnclickListenerApi.onRadioClickApi(
                            group.getCheckedRadioButtonId() == R.id.fast_pattern? "fast_pattern" : "detail_pattern");
                }
            }
        });
        if(defaultRadioButton != null) {
            settingRadioGroup.check(defaultRadioButton.equals("fast_pattern") ? R.id.fast_pattern : R.id.detail_pattern);
        }
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnclickListener {
        public void onYesClick();
    }

    /**
     * 设置确定Radio被点击的接口
     */
    public interface onRadioOnclickListenerApi {
        public void onRadioClickApi(String checkedId);
    }
}
