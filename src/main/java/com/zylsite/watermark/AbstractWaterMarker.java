package com.zylsite.watermark;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@SuppressWarnings("restriction")
public abstract class AbstractWaterMarker implements WaterMark {

	protected BufferedImage bufferedImage;// 缓存图片
	protected Graphics2D g;// Java绘图工具对象

	protected int originalImageWidth;// 原始图宽度
	protected int originalImageHeight;// 原始图高度
	
	protected float markAlpha;//水印透明度
	
	@Override
	public void addWaterMarker(File originalImage, File targetImage) {
		try {
			// 缓存原始图片到缓存对象
			this.cacheImage(originalImage);
			// 画水印
			this.drawMark();
			// 将水印画在原始图片上
			this.writeMarkToImage(targetImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 画水印
	protected abstract void drawMark();

	// 缓存原始图片到缓存对象
	private void cacheImage(File originalImage) throws IOException {
		// 1 创建图片缓存对象
		Image image2 = ImageIO.read(originalImage);
		originalImageWidth = image2.getWidth(null);
		originalImageHeight = image2.getHeight(null);
		bufferedImage = new BufferedImage(originalImageWidth, originalImageHeight, BufferedImage.TYPE_INT_RGB);
		// 2 创建Java绘图工具对象
		g = bufferedImage.createGraphics();
		// 3 使用绘图工具对象将原图绘制到缓存图片对象
		g.drawImage(image2, 0, 0, originalImageWidth, originalImageHeight, null);
	}

	// 将水印画在原始图片上
	private void writeMarkToImage(File targetImage) throws ImageFormatException, IOException {
		OutputStream os = null;
		try {
			os = new FileOutputStream(targetImage);// 最终目标文件
			// 5 创建图像文件编码工具类
			JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
			// 6 使用图像编码工具类，输出缓存图像到目标文件
			en.encode(bufferedImage);
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
