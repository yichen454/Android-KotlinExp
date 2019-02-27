package com.yichen.summer.widgets.flowlayout;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Line {
    // 子控件集合
    private List<View> childList = new ArrayList<>();
    // 行高
    private int height;
    // 当前行已经使用的宽度
    private int lineUsedSize = 0;

    /**
     * 添加childView
     *
     * @param childView 子控件
     */
    public void addChild(View childView) {
        childList.add(childView);
        // 更新行高为当前最高的一个childView的高度
        if (height < childView.getMeasuredHeight()) {
            height = childView.getMeasuredHeight();
        }
    }

    /**
     * 设置childView的绘制区域
     *
     * @param left 左上角x轴坐标
     * @param top  左上角y轴坐标
     */
    public void layout(int lineMode, int left, int top, int totalUsableWidth, int horizontalSpacing) {
        // 当前childView的左上角x轴坐标
        switch (lineMode) {
            case SQLFlowLayout.START_FROM_LEFT:
                for (View view : childList) {
                    // 设置childView的绘制区域
                    view.layout(left, top,
                            left + view.getMeasuredWidth(), top + view.getMeasuredHeight());
                    // 计算下一个childView的位置
                    left += view.getMeasuredWidth() + horizontalSpacing;
                }
                break;

            case SQLFlowLayout.START_FROM_CENTER:
                int square = (horizontalSpacing * (childList.size() - 1) + totalUsableWidth - lineUsedSize)
                        / (childList.size() + 1);
                for (View view : childList) {
                    // 设置childView的绘制区域
                    left += square;
                    view.layout(left, top,
                            left + view.getMeasuredWidth(), top + view.getMeasuredHeight());
                    // 计算下一个childView的位置
                    left += view.getMeasuredWidth();
                }
                break;

            case SQLFlowLayout.START_FROM_RIGHT:
                left += totalUsableWidth - lineUsedSize;
                if (childList.size() > 0) {
                    for (int index = childList.size() - 1; index >= 0; index --) {
                        // 设置childView的绘制区域
                        childList.get(index).layout(left, top,
                                left + childList.get(index).getMeasuredWidth(), top + childList.get(index).getMeasuredHeight());
                        // 计算下一个childView的位置
                        left += childList.get(index).getMeasuredWidth() + horizontalSpacing;
                    }
                }
                break;
        }

    }

    public int getHeight() {
        return height;
    }

    public int getChildCount() {
        return childList.size();
    }

    public void setUsedLineSize(int lineUsedSize) {
        this.lineUsedSize = lineUsedSize;
    }
}