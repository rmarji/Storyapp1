package com.jogeeks.common;

import android.widget.TextView;

public class TextSpan {
//
//	public Spannable applySpans(String s, Spannable sp, TextView tv) {
//		String[] words = s.split(" ");
//		int startIndex, endIndex;
//		
//		for (String word : words) {
//			startIndex = s.indexOf(word);
//			endIndex = startIndex + word.length();
//			
//			sp.s
//			sp.setSpan(new ClickableSpan() {
//				
//				@Override
//				public void onClick(View arg0) {
//					
//					String word = s.substring(startIndex, endIndex);
//
//					sp.setSpan(new ForegroundColorSpan(Color.GREEN), startIndex,
//							endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//					tv.setText(sp);
//					
//				}
//			} ,  startIndex, endIndex , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//			sp.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex, start
//					+ word.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//		}
//		return sp;
//	}
//
//	private final class IndexedClickableSpan extends ClickableSpan {
//
//		TextView tv;
//		String s;
//		Spannable sp;
//
//		public IndexedClickableSpan(int startIndex, int endIndex, TextView tv,
//				String s, Spannable sp) {
//			this.startIndex = startIndex;
//			this.endIndex = endIndex;
//			this.tv = tv;
//			this.s = s;
//			this.sp = sp;
//
//		}



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
		x = Math.min(textView2.getWidth() - textView2.getTotalPaddingRight()
				- 1, x);
		x += textView2.getScrollX();
		return x;
	}

	private int getLineAtCoordinate(TextView textView2, float y) {
		y -= textView2.getTotalPaddingTop();
		// Clamp the position to inside of the view.
		y = Math.max(0.0f, y);
		y = Math.min(textView2.getHeight() - textView2.getTotalPaddingBottom()
				- 1, y);
		y += textView2.getScrollY();
		return textView2.getLayout().getLineForVertical((int) y);
	}

}
