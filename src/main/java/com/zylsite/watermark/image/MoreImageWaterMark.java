package com.zylsite.watermark.image;

import java.awt.AlphaComposite;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MoreImageWaterMark extends ImageWaterMark {

	/**
	 * 水印旋转的角度
	 */
	protected double rotationAngle;

	public MoreImageWaterMark(File markImage, float markAlpha, double rotationAngle) {
		super(markImage);
		this.markAlpha = markAlpha;
		this.rotationAngle = rotationAngle;
	}

	@Override
	protected void drawMark() {
		Image logoImage = null;
		try {
			logoImage = ImageIO.read(markImage);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(null == logoImage){
			return;
		}
		
		int width1 = logoImage.getWidth(null);
		int height1 = logoImage.getHeight(null);

		// 透明度的设置
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, markAlpha));

		// 旋转图片(30°)
		g.rotate(Math.toRadians(30), bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);

		int x = -originalImageWidth / 2;
		int y = -originalImageHeight / 2;

		while (x < originalImageWidth * 1.5) {
			y = -originalImageHeight / 2;
			while (y < originalImageHeight * 1.5) {
				g.drawImage(logoImage, x, y, null);
				y += height1 + 50;
			}
			x += width1 + 50;
		}
		g.dispose();
	}

}
