package novellotus.com.myeasyloading;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

public class MainActivity extends BaseActivity implements MyHandler.HandlerInterface{

    MyHandler myHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLoading();
    }


    /**
     * 模拟加载数据
     */
    public void load(View view){
        loadingView.loading("拼命加载中,请稍等");
        myHandler=new MyHandler(this,this);
        Message message=new Message();
        message.what=2;
        myHandler.sendMessageDelayed(message,5000);
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case 1:
                loadingView.loadEmpty("当前没有数据");
                break;
            case 2:
                loadingView.loadFail(true,"网络请求失败");
                break;
        }
    }

    @Override
    public void reload() {

        switch (loadingView.getLoadingState()){
            case LOADING:
                break;
            case SUCCESS:
                break;
            case EMPTY:
                break;
            case FAIL:
                loadingView.loading("拼命加载中,请稍等");
                Message message=new Message();
                message.what=1;
                myHandler.sendMessageDelayed(message,3000);
                break;
        }


    }
}
