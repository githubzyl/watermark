package com.zylsite.watermark.text;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;

public class SingleTextWaterMark extends TextWaterMark {

	/**
	 * 期望横坐标
	 */
	protected int expectX;
	/**
	 * 期望纵坐标
	 */
	protected int expectY;

	public SingleTextWaterMark(String markText, String fontName, Integer fontStyle, Integer fontSize, Color fontColor,
			float markAlpha, int expectX, int expectY) {
		super(markText, fontName, fontStyle, fontSize, fontColor, markAlpha);
		this.expectX = expectX;
		this.expectY = expectY;
	}

	@Override
	protected void drawMark() {
		// 4 使用绘图工具对象将水印（文字/图片）绘制到缓存图片
		g.setFont(new Font(fontName, fontStyle, fontSize));
		g.setColor(fontColor);

		// 获取文字水印的宽度和高度值
		int width1 = fontSize * getTextLength(markText);// 文字水印宽度
		int height1 = fontSize; // 文字水印高度

		// 计算原图和文字水印的宽度和高度之差
		int widthDiff = originalImageWidth - width1; // 宽度之差
		int heightDiff = originalImageHeight - height1; // 高度之差

		int x = this.expectX; // 横坐标
		int y = this.expectY; // 纵坐标

		// 保证文字水印在右下方显示
		if (x > widthDiff) {
			x = widthDiff;
		}
		if (y > heightDiff) {
			y = heightDiff;
		}

		// 水印透明度的设置
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, markAlpha));
		// 绘制文字
		g.drawString(markText, x, y + fontSize);
		// 释放工具
		g.dispose();
	}

}
