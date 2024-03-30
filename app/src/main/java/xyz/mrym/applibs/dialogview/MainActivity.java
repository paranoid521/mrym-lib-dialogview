package xyz.mrym.applibs.dialogview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import xyz.mrym.applibs.dialogviewlib.ProgressUI.MyBaseProgress;
import xyz.mrym.applibs.dialogviewlib.widget.MyBaseDialog;
import xyz.mrym.applibs.dialogviewlib.widget.ShowDialog;
import xyz.mrym.applibs.dialogviewlib.widget.setting.ShowSettingDialog;

public class MainActivity extends AppCompatActivity {

    @SuppressLint({"ResourceType", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 确认信息对话框
        Button getProgress = findViewById(R.id.progress);
        getProgress.setOnClickListener(v -> ShowDialog.getInstance().showNewDialog(this,
                "升级提醒",
                "有新版本 1.0.1 " + "\n" + "\n\n是否立即下载更新？",
                "稍后更新", "立即更新",
                () -> ShowDialog.getInstance().getMyBaseDialog().dismiss(),
                () -> Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_LONG).show()));
        // 进度对话框
        Button dialog = findViewById(R.id.dialog);
        dialog.setOnClickListener(v -> {
            MyBaseProgress myBaseProgress = new MyBaseProgress(this);
            myBaseProgress.show();
            myBaseProgress.setProgress(70);
        });
        // 设置对话框
        Button settingDialog = findViewById(R.id.settingDialog);
        RadioButton radioButton1 = new RadioButton(this);
        radioButton1.setButtonDrawable(xyz.mrym.applibs.dialogviewlib.R.drawable.selector_radio);
        radioButton1.setText("button1");
        radioButton1.setId(1);
        RadioButton radioButton2 = new RadioButton(this);
        radioButton2.setButtonDrawable(xyz.mrym.applibs.dialogviewlib.R.drawable.selector_radio);
        radioButton2.setText("button2");
        radioButton2.setId(2);
        settingDialog.setOnClickListener(v -> ShowSettingDialog.getInstance().showSettingDialog(this,
                checkedId -> {
                    //保存导引模式设置
                    Toast.makeText(getApplicationContext(), "点击了 " + checkedId, Toast.LENGTH_LONG).show();
                },
                () -> ShowSettingDialog.getInstance().getMySettingDialog().dismiss(),
                1, "title", "msg", "description",
                Arrays.asList(radioButton1, radioButton2)));

    }
}