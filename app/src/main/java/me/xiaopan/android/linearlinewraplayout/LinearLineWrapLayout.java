package me.xiaopan.android.linearlinewraplayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 线性自动换行布局
 */
public class LinearLineWrapLayout extends ViewGroup{
    private boolean adjustChildWidthWithParent;

    public LinearLineWrapLayout(Context context, AttributeSet attrs){
        super(context, attrs);
    }
    
    public LinearLineWrapLayout(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY){
            setMeasuredDimension(widthSize, heightSize);
            return;
        }

        if(widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST){
            measureWidthByLimited(widthMeasureSpec, heightMeasureSpec, widthSize, heightMode, heightSize);
        }else{
            measureWidthByUnlimited(widthMeasureSpec, heightMeasureSpec, heightMode, heightSize);
        }
    }

    private void measureWidthByLimited(int widthMeasureSpec, int heightMeasureSpec, int widthSize, int heightMode, int heightSize){
        int needHeight = 0; // 实际需要的高度
        int rowWidth = 0;  // 实际需要的宽度
        int rowHeight = 0;
        int availableWidth = widthSize - getPaddingLeft() - getPaddingRight();   // 可用宽度
        LinkedList<View> rowViews = new LinkedList<View>();
        View child;
        for (int position = 0; position < getChildCount(); position++) {
            child = getChildAt(position);
            if(child.getVisibility() == View.GONE) continue;

            // 测量并计算当前View需要的宽高
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            int currentChildNeedWidth = lp.leftMargin+child.getMeasuredWidth()+lp.rightMargin;
            int currentChildNeedHeight = lp.topMargin+child.getMeasuredHeight()+lp.bottomMargin;

            // 如果加上当前View就超过了最大宽度，那么就处理之前的View
            if(rowWidth + currentChildNeedWidth > availableWidth){
                if(adjustChildWidthWithParent){
                    adjustChildWidthWithParent(rowViews, availableWidth, widthMeasureSpec, heightMeasureSpec);
                }
                rowViews.clear();
                rowWidth = 0;   //  清空行宽
                needHeight += rowHeight;    // 增加View高度
                rowHeight = 0;  // 清空行高
            }

            rowViews.add(child);
            rowWidth += currentChildNeedWidth;  // 增加行宽
            if(currentChildNeedHeight > rowHeight){ // 更新行高
                rowHeight = currentChildNeedHeight;
            }
        }

        if(!rowViews.isEmpty()){
            if(adjustChildWidthWithParent){
                adjustChildWidthWithParent(rowViews, availableWidth, widthMeasureSpec, heightMeasureSpec);
            }
            rowViews.clear();
            needHeight += rowHeight;    // 增加View高度
        }

        int finalHeight = 0;
        switch(heightMode){
            case MeasureSpec.EXACTLY :
                finalHeight = heightSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                finalHeight = needHeight + getPaddingTop() + getPaddingBottom();
                break;
        }
        setMeasuredDimension(widthSize, finalHeight);
    }

    private void measureWidthByUnlimited(int widthMeasureSpec, int heightMeasureSpec, int heightMode, int heightSize){
        int rowWidth = 0;  // 实际需要的宽度
        int rowHeight = 0;
        View child;
        for (int position = 0; position < getChildCount(); position++) {
            child = getChildAt(position);
            if(child.getVisibility() == View.GONE) continue;

            // 测量并计算当前View需要的宽高
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int currentChildNeedWidth = child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
            int currentChildNeedHeight = child.getMeasuredHeight()+lp.topMargin+lp.bottomMargin;

            rowWidth += currentChildNeedWidth;  // 增加行宽
            if(currentChildNeedHeight > rowHeight){ // 更新行高
                rowHeight = currentChildNeedHeight;
            }
        }
        rowWidth += getPaddingLeft() + getPaddingRight();
        int finalHeight = 0;
        switch(heightMode){
            case MeasureSpec.EXACTLY :
                finalHeight = heightSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                finalHeight = rowHeight + getPaddingTop() + getPaddingBottom();
                break;
        }
        setMeasuredDimension(rowWidth, finalHeight);
    }

    /**
     * 调整views集合中的View，让所有View的宽度加起来正好等于parentViewWidth
     * @param views 子View集合
     * @param parentViewWidth 父Vie的宽度
     * @param parentWidthMeasureSpec 父View的宽度规则
     * @param parentHeightMeasureSpec 父View的高度规则
     */
    private void adjustChildWidthWithParent(LinkedList<View> views, int parentViewWidth, int parentWidthMeasureSpec, int parentHeightMeasureSpec){
        // 先去掉所有子View的外边距
        for(View view : views){
            if(view.getLayoutParams() instanceof MarginLayoutParams){
                MarginLayoutParams lp = (MarginLayoutParams) view.getLayoutParams();
                parentViewWidth -= lp.leftMargin + lp.rightMargin;
            }
        }

        // 去掉宽度大于平均宽度的View后再次计算平均宽度
        int averageWidth = parentViewWidth /views.size();
        int bigTabCount = views.size();
        while(true){
            Iterator<View> iterator = views.iterator();
            while(iterator.hasNext()){
                View view = iterator.next();
                if(view.getMeasuredWidth() > averageWidth){
                    parentViewWidth -= view.getMeasuredWidth();
                    bigTabCount--;
                    iterator.remove();
                }
            }
            averageWidth = parentViewWidth /bigTabCount;
            boolean end = true;
            for(View view : views){
                if(view.getMeasuredWidth() > averageWidth){
                    end = false;
                }
            }
            if(end){
                break;
            }
        }

        // 修改宽度小于新的平均宽度的View的宽度
        for(View view : views){
            if(view.getMeasuredWidth() < averageWidth){
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.width = averageWidth;
                view.setLayoutParams(layoutParams);
                // 再次测量让新宽度生效
                if(layoutParams instanceof MarginLayoutParams){
                    measureChildWithMargins(view, parentWidthMeasureSpec, 0, parentHeightMeasureSpec, 0);
                }else{
                    measureChild(view, parentWidthMeasureSpec, parentHeightMeasureSpec);
                }
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int viewWidth = r - l;
        int leftOffset = getPaddingLeft();
        int topOffset = getPaddingTop();
        int rowMaxHeight = 0;
        View childView;
        for( int w = 0, count = getChildCount(); w < count; w++ ){
            childView = getChildAt(w);
            LayoutParams lp = (LayoutParams) childView.getLayoutParams();

            // 如果加上当前子View的宽度后超过了ViewGroup的宽度，就换行
            int occupyWidth = lp.leftMargin + childView.getMeasuredWidth() + lp.rightMargin;
            if(leftOffset + occupyWidth + getPaddingRight() > viewWidth){
                leftOffset = getPaddingLeft();  // 回到最左边
                topOffset += rowMaxHeight;  // 换行
                rowMaxHeight = 0;
            }

            int left = leftOffset + lp.leftMargin;
            int top = topOffset + lp.topMargin;
            int right = leftOffset+ lp.leftMargin + childView.getMeasuredWidth();
            int bottom =  topOffset + lp.topMargin + childView.getMeasuredHeight();
            childView.layout(left, top, right, bottom);

            // 横向偏移
            leftOffset += occupyWidth;

            // 试图更新本行最高View的高度
            int occupyHeight = lp.topMargin + childView.getMeasuredHeight() + lp.bottomMargin;
            if(occupyHeight > rowMaxHeight){
                rowMaxHeight = occupyHeight;
            }
        }
    }

    /**
     * Returns a set of layout parameters with a width of
     * {@link android.view.ViewGroup.LayoutParams#MATCH_PARENT},
     * and a height of {@link android.view.ViewGroup.LayoutParams#MATCH_PARENT}.
     */
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    /**
     * 设置当一行的子View宽度没有充满LineWrapLayout时是否调整这一行所有子View的宽度以充满LineWrapLayout
     * @param adjustChildWidthWithParent 当一行的子View宽度没有充满LineWrapLayout时是否调整这一行所有子View的宽度以充满LineWrapLayout
     */
    public void setAdjustChildWidthWithParent(boolean adjustChildWidthWithParent) {
        this.adjustChildWidthWithParent = adjustChildWidthWithParent;
        requestLayout();
    }

    public static class LayoutParams extends MarginLayoutParams {

        /**
         * {@inheritDoc}
         */
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        /**
         * {@inheritDoc}
         */
        public LayoutParams(int width, int height) {
            super(width, height);
        }

        /**
         * {@inheritDoc}
         */
        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        /**
         * {@inheritDoc}
         */
        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        /**
         * Copy constructor. Clones the width, height, margin values, and
         * gravity of the source.
         *
         * @param source The layout params to copy from.
         */
        public LayoutParams(LayoutParams source) {
            super(source);
        }
    }
}
