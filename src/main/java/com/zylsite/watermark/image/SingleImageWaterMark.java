package com.zylsite.watermark.image;

import java.awt.AlphaComposite;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SingleImageWaterMark extends ImageWaterMark {
	
	/**
	 * 期望横坐标
	 */
	protected int expectX;
	/**
	 * 期望纵坐标
	 */
	protected int expectY;

	public SingleImageWaterMark(File markImage, float markAlpha, int expectX, int expectY) {
		super(markImage);
		this.markAlpha = markAlpha;
		this.expectX = expectX;
		this.expectY = expectY;
	}

	@Override
	protected void drawMark() {
		Image imageLogo = null;
		try {
			imageLogo = ImageIO.read(markImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(null == imageLogo){
			return;
		}

		// 分析图片文件的高度和宽度
		int width1 = imageLogo.getWidth(null);
		int height1 = imageLogo.getHeight(null); // 图片水印高度

		// 计算原图与水印图片的宽度、高度之差
		int widthDiff = originalImageWidth - width1; // 宽度之差
		int heightDiff = originalImageHeight - height1; // 高度之差

		int x = expectX; // 横坐标
		int y = expectY; // 纵坐标

		// 保证图片水印在右下方显示
		if (x > widthDiff) {
			x = widthDiff;
		}
		if (y > heightDiff) {
			y = heightDiff;
		}

		// 水印透明度的设置
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, markAlpha));// 透明度的设置
		// 绘制图片水印
		g.drawImage(imageLogo, x, y, null);
		// 释放工具
		g.dispose();

	}

}
