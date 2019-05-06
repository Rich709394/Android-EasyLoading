package novellotus.com.myeasyloading;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.novellotus.easyloading.EasyLoading;
import com.novellotus.easyloading.EasyLoadingView;
import com.novellotus.easyloading.EasyReloadInterface;


/**
 * Created by Rich on 2019/5/5.
 */
public abstract class BaseActivity extends AppCompatActivity implements EasyReloadInterface {

    public EasyLoadingView loadingView;
    /**
     * 初始化加载布局
     */
    public void initLoading() {
        EasyLoading  easyLoading = new EasyLoading();
        loadingView = easyLoading.build(this);
        loadingView.setBackgroundResource(R.color.colorPrimaryDark);
        easyLoading.addLoadingView((ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), 0);
        loadingView.setReloadInterface(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loadingView=null;
    }
}
