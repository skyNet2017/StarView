package yue.starview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import yue.util.StarPathUtil;

/**
 * Created by Yue on 2017/4/4.
 */

public class PathStarView extends View {
    private int starStrokeWidth;
    private float depth;
    private int hormCount;
    private int starColor;
    private float rotate;
    private Path starPath;
    private int flag;
    private Paint paint;


    public PathStarView(Context context) {
        this(context, null);
    }

    public PathStarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathStarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Star);
        hormCount = a.getInt(R.styleable.Star_hormCount, 5);
        depth = a.getFloat(R.styleable.Star_depth, 0.6f);
        starColor = a.getColor(R.styleable.Star_starColor, Color.YELLOW);
        starStrokeWidth = a.getDimensionPixelOffset(R.styleable.Star_strokeWidth, 0);
        rotate = a.getFloat(R.styleable.Star_rotate,0f);
        a.recycle();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(starColor);
    }

//    public PathStarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.rotate(rotate,canvas.getWidth()/2.0f,canvas.getHeight()/2);
        canvas.drawPath(starPath, paint);
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        final double r = w < h ? w / 2d : h / 2d;
        starPath = StarPathUtil.linePath(r, hormCount, depth);
    }

    public int getFlag() {
        return flag;
    }

    public void setStarColor(int starColor) {
        setStar(hormCount, starColor, depth, starStrokeWidth,rotate);
    }

    public int getStarColor() {
        return starColor;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getStarStrokeWidth() {
        return starStrokeWidth;
    }

    public double getDepth() {
        return depth;
    }

    public float getRotate() {
        return rotate;
    }

    public void setRotate(float rotate) {
        setStar(hormCount, starColor, depth, starStrokeWidth,rotate);
    }

    public void setStarStrokeWidth(int starStrokeWidth) {
        this.starStrokeWidth = starStrokeWidth;
    }

    public void setDepth(float depth) {
       setStar(hormCount, starColor, depth, starStrokeWidth,rotate);
    }

    public int getHormCount() {
        return hormCount;
    }

    public void setHormCount(int hormCount) {
       setStar(hormCount, starColor, depth, starStrokeWidth,rotate);
    }

    public Path getStarPath() {
        return starPath;
    }

    public void setStar(int hormCount, int starColor, float depth, int starStrokeWidth,float rotate) {
        boolean refresh = false;
        if (hormCount != this.hormCount) {
            this.starColor = starColor;
            refresh = true;
        }
        if (this.hormCount != hormCount) {
            this.hormCount = hormCount;
            refresh = true;
        }
        if (this.depth != depth) {
            this.depth = depth;
            refresh = true;
        }
        if (this.starStrokeWidth != starStrokeWidth) {
            this.starStrokeWidth = starStrokeWidth;
            refresh = true;
        }
        if (this.rotate != rotate){
            this.rotate = rotate;
            refresh = true;
        }
        if(this.starColor != starColor){
            this.starColor = starColor;
            paint.setColor(starColor);
            refresh = true;
        }
        updateStar(refresh);
    }
    private void updateStar(boolean refresh){
        if (refresh){
            int w = getWidth();
            int h = getHeight();
            final double r = w < h ? w / 2d : h / 2d;
            starPath = StarPathUtil.linePath(r, hormCount, depth);
            invalidate();
        }
    }


    public void setWidth(int width){
        ViewGroup.LayoutParams p = getLayoutParams();
        if (p==null){
            p = new ViewGroup.LayoutParams(0,0);
        }
        p.width = width;
        setLayoutParams(p);
    }
    public void setHeight(int height){
        ViewGroup.LayoutParams p = getLayoutParams();
        if (p==null){
            p = new ViewGroup.LayoutParams(0,0);
        }
        p.height = height;
        setLayoutParams(p);
    }
}
