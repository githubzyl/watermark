package com.zylsite.watermark.text;

import java.awt.Color;

import com.zylsite.watermark.AbstractWaterMarker;

public abstract class TextWaterMark extends AbstractWaterMarker {

	/**
	 * 水印文字
	 */
	protected String markText;
	/**
	 * 文字字体
	 */
	protected String fontName;
	/**
	 * 字体分格，比如加粗
	 */
	protected Integer fontStyle;
	/**
	 * 字体大小
	 */
	protected Integer fontSize;
	/**
	 * 字体颜色
	 */
	protected Color fontColor;

	public TextWaterMark(String markText, String fontName, Integer fontStyle, Integer fontSize, Color fontColor,
			float markAlpha) {
		this.markText = markText;
		this.fontName = fontName;
		this.fontStyle = fontStyle;
		this.fontSize = fontSize;
		this.fontColor = fontColor;
		this.markAlpha = markAlpha;
	}

	// 文本长度的处理：文字水印的中英文字符的宽度转换
	protected int getTextLength(String text) {
		int length = text.length();
		for (int i = 0; i < text.length(); i++) {
			String s = String.valueOf(text.charAt(i));
			if (s.getBytes().length > 1) { // 中文字符
				length++;
			}
		}
		length = length % 2 == 0 ? length / 2 : length / 2 + 1; // 中文和英文字符的转换
		return length;
	}

}
