# Android-EasyLoading

## 无入侵式添加加载布局

![效果](https://github.com/Rich709394/Android-EasyLoading/blob/master/img/GIF.gif)

## 保持你的布局清爽整洁,不额外新增组件。

![布局](https://github.com/Rich709394/Android-EasyLoading/blob/master/img/layout.png)

# 使用简单
   
1.在跟目录build.gradle文件中添加  
```
allprojects {  
		repositories {  
			...  
			maven { url 'https://jitpack.io' }  
		}  
	}
```
2.在项目build.gradle文件中添加依赖
```
dependencies {
	        implementation 'com.github.Rich709394:Android-EasyLoading:1.0'
	}
```

**推荐使用方式**  
在BaseActivity中添加初始化方法，在需要使用的时候才进行初始化 ,在 reload()方法中实现重新加载事件
```
public abstract class BaseActivity extends AppCompatActivity implements EasyReloadInterface {

    public EasyLoadingView loadingView;
    /**
     * 初始化加载布局
     */
    public void initLoading() {
        EasyLoading  easyLoading = new EasyLoading();
        loadingView = easyLoading.build(this);
        loadingView.setBackgroundResource(R.color.colorPrimaryDark);
        easyLoading.addLoadingView((ViewGroup)((ViewGroup)findViewById(android.R.id.content)).getChildAt(0), 0);  
        loadingView.setReloadInterface(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loadingView=null;
    }
}
```
在使用的地方直接控制EasyLoadingView改变加载的状态
```
public class MainActivity extends BaseActivity implements MyHandler.HandlerInterface{

    private MyHandler myHandler;
    
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
```
**说明**  
这个组件只是简书教程的一部分，只是很简单的实现了这个功能，没有考虑各种情况。而且组件里已经给出了setLoadingView(EasyLoadingView easyLoadingView)方法鼓励大家自定义合适自己项目主题的加载布局。

