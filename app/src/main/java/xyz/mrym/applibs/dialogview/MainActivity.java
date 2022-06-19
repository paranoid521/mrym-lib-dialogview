package xyz.mrym.applibs.dialogview;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import xyz.mrym.applibs.dialogviewlib.ProgressUI.MyBaseProgress;
import xyz.mrym.applibs.dialogviewlib.widget.MyBaseDialog;
import xyz.mrym.applibs.dialogviewlib.widget.ShowDialog;
import xyz.mrym.applibs.dialogviewlib.widget.setting.ShowSettingDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getProgress = findViewById(R.id.progress);
        getProgress.setOnClickListener(v -> ShowDialog.getInstance().showNewDialog(this,
                "升级提醒",
                "有新版本 1.0.1 " + "\n" + "\n\n是否立即下载更新？",
                "稍后更新", "立即更新",
                (MyBaseDialog.onNoOnclickListener) () -> ShowDialog.getInstance().getMyBaseDialog().dismiss(),
                (MyBaseDialog.onYesOnclickListener) () -> Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_LONG).show()));

        Button dialog = findViewById(R.id.dialog);
        dialog.setOnClickListener(v -> {
            MyBaseProgress myBaseProgress = new MyBaseProgress(this);
            myBaseProgress.show();
            myBaseProgress.setProgress(70);
        });

        Button settingDialog = findViewById(R.id.settingDialog);
        settingDialog.setOnClickListener(v -> {
            ShowSettingDialog.getInstance().showSettingDialog(this,
                    checkedId -> {
                        //保存导引模式设置
                        Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_LONG).show();
                    },
                    () -> ShowSettingDialog.getInstance().getMySettingDialog().dismiss(),
                    "test");
        });

    }
}