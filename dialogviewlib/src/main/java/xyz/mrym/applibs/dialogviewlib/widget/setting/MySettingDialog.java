package xyz.mrym.applibs.dialogviewlib.widget.setting;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.List;

import xyz.mrym.applibs.dialogviewlib.R;


public class MySettingDialog extends Dialog {
    private Button yesButton;//确定按钮
    private RadioGroup settingRadioGroup;
    private TextView titleTextView;//消息标题文本
    private TextView messageTextView;//消息提示文本
    private TextView descriptionTextView;//消息提示文本
    private List<RadioButton> radioButtonList;

    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    private onRadioOnclickListenerApi onRadioOnclickListenerApi;//确定按钮被点击了的监听器

    private int defaultRadioButtonId;
    private String titleStr;//从外界设置的title文本
    private String messageStr;//从外界设置的消息文本
    private String descriptionStr;//从外界设置的消息文本

    public void setDefaultRadioButton(int defaultRadioButtonText) {
        this.defaultRadioButtonId = defaultRadioButtonId;
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_setting);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        titleTextView = findViewById(R.id.title);
        messageTextView = findViewById(R.id.message);
        descriptionTextView = findViewById(R.id.description);
        yesButton = findViewById(R.id.yes);
        //初始化界面控件的事件，设置确定按钮被点击后，向外界提供监听
        yesButton.setOnClickListener(v -> {
            if (yesOnclickListener != null) {
                yesOnclickListener.onYesClick();
            }
        });
        settingRadioGroup = findViewById(R.id.daoyin_setting);
        // 配置默认 RadioButton 点击监听
        settingRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (onRadioOnclickListenerApi != null) {
                onRadioOnclickListenerApi.onRadioClickApi(checkedId);
            }
        });
        // 配置默认 RadioButton
        if(defaultRadioButtonId != -1) {
            for (RadioButton radioButton : radioButtonList) {
                RadioGroup parent = (RadioGroup) radioButton.getParent();
                if (parent != null) {
                    parent.removeView(radioButton);
                }
                settingRadioGroup.removeView(radioButton);
                settingRadioGroup.addView(radioButton, radioButtonList.indexOf(radioButton));
                int radioButtonId = radioButton.getId();
                if (radioButtonId == defaultRadioButtonId) {
                    settingRadioGroup.check(radioButtonId);
                    break;
                }
            }
        }
        // 初始化界面数据
        initData();
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnclickListener {
        void onYesClick();
    }

    /**
     * 设置确定Radio被点击的接口
     */
    public interface onRadioOnclickListenerApi {
        void onRadioClickApi(int checkedId);
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message
        if (titleStr != null) {
            titleTextView.setText(titleStr);
        }
        if (messageStr != null) {
            messageTextView.setText(messageStr);
        }
        if (descriptionStr != null) {
            descriptionTextView.setText(descriptionStr);
        }
    }

    /**
     * 从外界Activity为Dialog设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        titleStr = title;
    }

    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param message
     */
    public void setMessage(String message) {
        messageStr = message;
    }

    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param description
     */
    public void setDescription(String description) {
        descriptionStr = description;
    }

    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param radioButtons
     */
    public void setRadioButtonList(List<RadioButton> radioButtons) {
        radioButtonList = radioButtons;
    }
}
