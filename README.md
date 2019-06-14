View的事件分发其实就是点击事件的分发过程，点击事件的分发过程由三个很重要的方法完成:
**dispatchTouchEvent、onInterceptTouchEvent、onTouchEvent.**
```
    public boolean dispatchTounchEvent(MotionEvent ev)
```
用来进行事件分发。如果事件能够传递给当前View，那么此方法一定会被调用，返回结果受当前View的onTouchEvent及下级View的dispatchTouchEvent的方法影响，表示是否消耗当前事件。
```
public boolean onInterceptTouchEvent(MotionEvent ev)
```
在上述内部调用，用来判断是否拦截某个时间，如果当前View拦截了某个事件，那么在同一个事件序列当中，此方法不会被再次调用，返回结果表示是否拦截当前事件。
```
public boolean onTouchEvent(MotionEvent ev)
```
再dispatchTouchEvent方法中调用，用来处理点击事件，返回结果表示是否消耗当前事件，如果不消耗，则在同一个事件序列中，当前View无法再次接收到事件。
**上面三个方法到底有什么区别呢？它们是什么关系呢？**
```
    public boolean dispatchTouchEvent(MotionEvent ev){
            boolean consume=false;
            if(onInterceptTouchEvent(ev)){//拦截判断当前View是否处理
                consume=onTouchEvent(ev);//处理事件
            } else {//如果当前View不处理事件 调用子类的分发事件
                consume=child.dispatchTouchEvent(ev);
            }
            return consume;
    }
```
为了验证View的分发流程我们创建一个Activity、一个自定义Layout、一个自定义View分别重写dispatchTouchEvent、onInterceptTouchEvent、onTouchEvent
```

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "ViewEvent";
    public final static String CURRENTTAG = "MainActivity:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, CURRENTTAG + "onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, CURRENTTAG + "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }
}
```
```
public class ViewEventGroup extends LinearLayout {
    public final static String CURRENTTAG = "ViewEventGroup:";

    public ViewEventGroup(Context context) {
        super(context);
    }

    public ViewEventGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewEventGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ViewEventGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(MainActivity.TAG, CURRENTTAG + "onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(MainActivity.TAG, CURRENTTAG + "onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(MainActivity.TAG, CURRENTTAG + "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }
}
```
```
public class ViewEventView extends View {
    public final static String CURRENTTAG = "ViewEventGroup:";
    public ViewEventView(Context context) {
        super(context);
    }

    public ViewEventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewEventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ViewEventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(MainActivity.TAG, CURRENTTAG + "onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(MainActivity.TAG, CURRENTTAG + "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }
}
```
输出结果如下：
![1](https://upload-images.jianshu.io/upload_images/11195468-d84cb0842a172fc5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

当一个事件产生后，它的传递过程遵循如下顺序：**Activity——>Window——>View.**即事件总是先传给Activity，Activity在传给Window，最后Window再传递给顶层的View，顶层的View收到事件后就会按照事件分发机制去分发事件。如果View的onTouchEvent返回false那么它父类的onTouchEvent就会被调用，依次类推当所以的元素都不处理事件是，那么这个事件最终将会传递给Activity处理。即Activity的onTouchEvent方法会被调用。
对于一个根ViewGroup来说，点击事件产生后，首先会传递给它，这时它的dispatchTouchEvent就会被调用，如果这个ViewGroup的onInterceptTouchEvent方法返回true就表示它要拦截当前事件，接着事件就会交给这个ViewGroup处理，即它的onTouchEvent方法会被调用。如果这个ViewGroup的onInterceptTouchEvent方法返回false就表示它不对当前事件做处理，就会将事件继续传递给他的子元素，接着子元素的dispatchTouchEvent方法就会被调用，如此反复直到事件被处理。
可能大家对这里还有点模糊，那么接下来我们将代码中ViewGroup的
onInterceptTouchEvent方法返回true
```
 @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(MainActivity.TAG, CURRENTTAG + "onInterceptTouchEvent");
        return true;
    }
```
![2](https://upload-images.jianshu.io/upload_images/11195468-5a2932d2a6842fe2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

将View中的onTouchEvent方法返回true
```
 @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(MainActivity.TAG, CURRENTTAG + "onTouchEvent");
        return true;
    }
```
![3](https://upload-images.jianshu.io/upload_images/11195468-4308a4cd1f7bf53c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
我们可以看出整体流程:
**1.将事件传递给ViewGroup：dispatchTouchEvent
2.ViewGroup调用onInterceptTouchEvent。
3.ViewGroup将事件传递给View(即调用View的dispatchTouchEvent方法)。
4.View调用onTouchEvent 返回true**

**总结**
- 同一个事件序列是从手指接触屏幕的那一刻起，到手指离开屏幕的那一结束。在这个过程中产生的一系列事件，这个事件序列以down事件开始，中间含有数量不定的move，最终以up结束。
- 正常情况下，一个事件序列只能被一个View拦截且消耗。因为一旦一个元素拦截了某些事件，那么同一个事件序列内的所以事件都会直接交给它处理，因此同一个事件序列中事件不能分别由两个View同时处理，但是通过特殊手段可以做到，比如一个View将本该自己处理的事件通过onTouchEvnet强行传给其他View处理。
- 某个View一旦决定拦截，那么这一个事件序列都只能由它来处理，并且它的onInterceptTouchEvent不会再被调用。
- ViewGroup默认不拦截任何事件。
- View没有onInterceptTouchEvent方法，一旦有点击事件传递给他，那么它的onTouchEvent就会被调用。
