package com.fenglianhai.todolist;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/4/27.
 */
public class ToDoListItemView extends TextView {

    public ToDoListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ToDoListItemView(Context context) {
        super(context);
        init();
    }

    public ToDoListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint marginPaint;
    private Paint linePaint;
    private int pagerColor;
    private float margin;

    private void init() {
        Resources r = this.getResources();

        marginPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        marginPaint.setColor(r.getColor(R.color.notepad_margin));

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(r.getColor(R.color.notepad_lines));

        pagerColor = r.getColor(R.color.notepad_pager);
        margin = r.getDimension(R.dimen.notepad_margin);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制页面的颜色
        canvas.drawColor(pagerColor);

        //绘制边缘
        canvas.drawLine(0,0, 0, getMeasuredHeight(), linePaint);
        canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), linePaint);

        //绘制分割线
        canvas.drawLine(margin, 0, margin, getMeasuredHeight(), marginPaint);

        //移动文本，让它跨过分割线
        canvas.save();
        canvas.translate(margin, 0);

        //使用TextView渲染文本
        super.onDraw(canvas);
        canvas.restore();
    }
}
