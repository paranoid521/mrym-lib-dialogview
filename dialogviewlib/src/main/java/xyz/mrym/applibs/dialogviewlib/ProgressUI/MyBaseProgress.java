package xyz.mrym.applibs.dialogviewlib.ProgressUI;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import xyz.mrym.applibs.dialogviewlib.R;

public class MyBaseProgress extends Dialog {

    private TextView totalSize;
    private TextView percent;
    private TextView title;
    private ProgressBar progressBar;

    private String titleStr;      //标题文本
    private String totalSizeStr, percentStr; //文件下载大小、文件下载百分比
    private int progress;           //下载进度

    public MyBaseProgress(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_base);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
    }

    /**
     * 更新界面控件的显示数据
     */
    public void refreshData() {
        //如果用户自定了title
        if (null != titleStr && ! titleStr.equals("")) {
            title.setText(titleStr);
        }
        //如果设置按钮的文字
        if (null != totalSizeStr && ! totalSizeStr.equals("")) {
            totalSize.setText(totalSizeStr);
        }
        if (null != percentStr && ! percentStr.equals("")) {
            percent.setText(percentStr);
        }
        if (progress != 0) {
            progressBar.setProgress(progress);
        }
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        totalSize = (TextView) findViewById(R.id.totalSize_progress);
        percent = (TextView) findViewById(R.id.percent_progress);
        title = (TextView) findViewById(R.id.title_progress);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    /**
     * 从外界Activity为Dialog标题、文件大小、下载百分比、当前进度
     *
     * @param titleStr
     */
    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public void setTotalSizeStr(String totalSizeStr) {
        this.totalSizeStr = totalSizeStr;
    }

    public void setPercentStr(String percentStr) {
        this.percentStr = percentStr;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
