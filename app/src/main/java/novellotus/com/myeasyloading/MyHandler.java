package novellotus.com.myeasyloading;


import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by Rich on 2019/4/30.
 */
public class MyHandler extends Handler {

    private WeakReference<Activity> activityWeakReference;
    private HandlerInterface handlerInterface;

    public MyHandler(Activity activity, HandlerInterface handlerInterface){
        this.handlerInterface=handlerInterface;
        activityWeakReference=new WeakReference<>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if(activityWeakReference.get()!=null){
            handlerInterface.handleMessage(msg);
        }
    }

    public interface HandlerInterface{
        void handleMessage(Message msg);
    }
}
