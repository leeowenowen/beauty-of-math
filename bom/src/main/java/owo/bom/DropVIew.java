package owo.bom;


        import android.app.Activity;
        import android.content.res.Resources;
        import android.graphics.Canvas;
        import android.graphics.drawable.Drawable;
        import android.util.DisplayMetrics;
        import android.view.MotionEvent;
        import android.view.View;


public class DropVIew extends View{

    private final String TAG = "dewdrop";

    //View的宽和高
    private int width;
    private int height;

    //是否正在拖拽
    private boolean isDraging = false;

    //手指触摸位置
    private int touchPX;
    private int touchPY;

    //上一次统计的位置
    private int mLastX = 0;
    private int mLastY = 0;

    //露珠效果大小
    private final int rate = 2;

    //要绘制的图片资源
    private Resources mRes;
    private Drawable mDrawable;

    //View的位置描述：Left、Top、Right、Bottom
    private int l,t,r,b;

    //手机屏幕尺寸
    private int mScrrenWidth;
    private int mScrrenHeight;

    public DropVIew(Activity context) {
        super(context);
        // TODO Auto-generated constructor stub
        init(context);
    }

    /**
     * @title init
     * @author liuzw01@126.com
     * @description TODO 初始化
     * @param context
     * @date 2013-11-05 4:03:05 PM
     */
    private void init(Activity context){
        mRes = context.getResources();
        getScrrenSize(context);
    }

    /**
     * @title getScrrenSize
     * @author liuzw01@126.com
     * @description TODO 获得手机屏幕大小
     * @param context
     * @date 2013-11-05 4:03:24 PM
     */
    private void getScrrenSize(Activity context){
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        mScrrenWidth = dm.widthPixels;
        mScrrenHeight = dm.heightPixels;
    }

    /**
     * @title setSize
     * @author liuzw01@126.com
     * @description TODO 设置View的显示大小
     * @param w
     * @param h
     * @date 2013-11-05 4:03:46 PM
     */
    public void setSize(int w, int h){
        if(w > mScrrenWidth){
            w = mScrrenWidth;
        }
        if(h > mScrrenHeight){
            h = mScrrenHeight;
        }
        width = w;
        height = h;
        r = l + width;
        b = t + height;
        validPos();
    }

    /**
     * @title onDraging
     * @author liuzw01@126.com
     * @description TODO 拖拽事件处理
     * @date 2013-11-05 4:08:56 PM
     */
    private void onDraging(){
        if(!isDraging){
            return;
        }
        l = touchPX - width/2;
        t = touchPY - height/2;
        r = l + width;
        b = t + height;

        int speedX = getSpeedX(touchPX)*rate;
        mLastX = touchPX;
        int speedY = getSpeedY(touchPY)*rate;
        mLastY = touchPY;

        boolean isXValid = false;
        if(speedX > speedY){
            isXValid = true;
        }

        if(isXValid){
            t = t + speedY;
            b = b - speedY;

            l = l - speedX;
            r = r + speedX;
        }else{
            t = t - speedY;
            b = b + speedY;

            l = l + speedX;
            r = r - speedX;
        }
        validPos();
        layout(l, t, r, b);

    }

    /**
     * @title getSpeedX
     * @author liuzw01@126.com
     * @description TODO 获得X方向滑动速度
     * @param x
     * @return
     * @date 2013-11-05 4:09:41 PM
     */
    private int getSpeedX(int x){
        if(mLastX == 0){
            return 0;
        }
        return Math.abs(x - mLastX);
    }

    /**
     * @title getSpeedY
     * @author liuzw01@126.com
     * @description TODO 获得Y方向滑动速度
     * @param y
     * @return
     * @date 2013-11-05 4:10:07 PM
     */
    private int getSpeedY(int y){
        if(mLastY == 0){
            return 0;
        }
        return Math.abs(y - mLastY);
    }

    /**
     * @title setImageResource
     * @author liuzw01@126.com
     * @description TODO 要显示的图片资源
     * @param id
     * @date 2013-11-05 4:10:28 PM
     */
    public void setImageResource(int id){
        if(mRes != null){
            mDrawable = mRes.getDrawable(id);
        }
    }

    /**
     * @title validPos
     * @author liuzw01@126.com
     * @description TODO 使得View一直显示在屏幕之内
     * @date 2013-11-04 8:19:51 PM
     */
    private void validPos(){
        if(l < 0){
            l = 0;
            r = width;
        }else if(r > mScrrenWidth){
            r = mScrrenWidth;
            l = r - width;
        }

        if(t < 0){
            t = 0;
            b = height;
        }else if(b > mScrrenHeight){
            b = mScrrenHeight;
            t = b - height;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        if(mDrawable == null) return;
        mDrawable.setBounds(0, 0, getWidth(), getHeight());
        mDrawable.draw(canvas);
    }

    /**
     * @title touch
     * @author liuzw01@126.com
     * @description TODO 传递进来的touch事件处理
     * @param event
     * @date 2013-11-05 4:11:20 PM
     */
    public void touch(MotionEvent event) {
        // TODO Auto-generated method stub

        touchPX = (int)event.getX();
        touchPY = (int)event.getY();

        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                if(isInView()){
                    isDraging = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                isDraging = false;
                if(isInView()){
                    reset();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                onDraging();
                break;
        }
    }

    /**
     * @title isInView
     * @author liuzw01@126.com
     * @description TODO 判断点击位置是否在此View之上
     * @return
     * @date 2013-11-04 8:17:30 PM
     */
    private boolean isInView(){
        if(touchPX > l && touchPX < r && touchPY > t && touchPY < b){
            return true;
        }
        return false;
    }

    /**
     * @title reset
     * @author liuzw01@126.com
     * @description TODO 恢复图形正常大小和宽高比
     * @date 2013-11-04 8:16:35 PM
     */
    private void reset(){
        l = touchPX - width/2;
        t = touchPY - height/2;
        r = l + width;
        b = t + height;
        validPos();
        layout(l, t, r, b);
    }

}

