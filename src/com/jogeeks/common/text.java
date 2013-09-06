package com.jogeeks.common;

import android.widget.TextView;

public class text {
	
	public int getOffsetForPosition(TextView textView, float x, float y) {
	    if (textView.getLayout() == null) {
	        return -1;
	    }
	    final int line = getLineAtCoordinate(textView, y);
	    final int offset = getOffsetAtCoordinate(textView, line, x);
	    return offset;
	}

	private int getOffsetAtCoordinate(TextView textView2, int line, float x) {
	    x = convertToLocalHorizontalCoordinate(textView2, x);
	    return textView2.getLayout().getOffsetForHorizontal(line, x);
	}

	private float convertToLocalHorizontalCoordinate(TextView textView2, float x) {
	    x -= textView2.getTotalPaddingLeft();
	    // Clamp the position to inside of the view.
	    x = Math.max(0.0f, x);
	    x = Math.min(textView2.getWidth() - textView2.getTotalPaddingRight() - 1, x);
	    x += textView2.getScrollX();
	    return x;
	}

	private int getLineAtCoordinate(TextView textView2, float y) {
	    y -= textView2.getTotalPaddingTop();
	    // Clamp the position to inside of the view.
	    y = Math.max(0.0f, y);
	    y = Math.min(textView2.getHeight() - textView2.getTotalPaddingBottom() - 1, y);
	    y += textView2.getScrollY();
	    return textView2.getLayout().getLineForVertical((int) y);
	}

}
