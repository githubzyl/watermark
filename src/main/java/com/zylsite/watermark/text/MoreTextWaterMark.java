package com.zylsite.watermark.text;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;

public class MoreTextWaterMark extends TextWaterMark {

	/**
	 * 水印旋转的角度
	 */
	protected double rotationAngle;

	public MoreTextWaterMark(String markText, String fontName, Integer fontStyle, Integer fontSize, Color fontColor,
			float markAlpha, double rotationAngle) {
		super(markText, fontName, fontStyle, fontSize, fontColor, markAlpha);
		this.rotationAngle = rotationAngle;
	}

	@Override
	protected void drawMark() {
		// 4 使用绘图工具对象将水印（文字/图片）绘制到缓存图片
		g.setFont(new Font(fontName, fontStyle, fontSize));
		g.setColor(fontColor);

		int width1 = fontSize * getTextLength(markText);// 文字水印宽度
		int height1 = fontSize; // 文字水印高度

		// 透明度的设置
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, markAlpha));

		// 旋转图片(30°)
		g.rotate(Math.toRadians(rotationAngle), bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);

		// 设置水印的坐标
		int x = -originalImageWidth / 2;
		int y = -originalImageHeight / 2;

		while (x < originalImageWidth * 1.5) {
			y = -originalImageHeight / 2;
			while (y < originalImageHeight * 1.5) {
				g.drawString(markText, x, y);
				y += height1 + 50;
			}
			x += width1 + 50; // 水印之间的间隔设为50
		}

		// 释放工具
		g.dispose();
	}

}
