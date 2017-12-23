package com.zylsite.watermark;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import org.junit.Test;

import com.zylsite.watermark.image.ImageWaterMark;
import com.zylsite.watermark.image.MoreImageWaterMark;
import com.zylsite.watermark.image.SingleImageWaterMark;
import com.zylsite.watermark.text.MoreTextWaterMark;
import com.zylsite.watermark.text.SingleTextWaterMark;
import com.zylsite.watermark.text.TextWaterMark;

public class TestWaterMark {
	
	private final String path = "F:/testfiles/images/";
	
	/**
	 * 单文字水印
	 */
	@Test
	public void test5(){
		File originalImage = new File(path+"2.jpg");
		File targetImage = new File(path+"text2.jpg");
		TextWaterMark textWaterMark = new SingleTextWaterMark("i私募", "微软雅黑", Font.BOLD, 20, Color.red, 0.3F, 10, 10);
		textWaterMark.addWaterMarker(originalImage, targetImage);
	}
	
	/**
	 * 多文字水印
	 */
	@Test
	public void test6(){
		File originalImage = new File(path+"2.jpg");
		File targetImage = new File(path+"text3.jpg");
		TextWaterMark textWaterMark = new MoreTextWaterMark("i私募", "微软雅黑", Font.BOLD, 20, Color.red, 0.3F, 30);
		textWaterMark.addWaterMarker(originalImage, targetImage);
	}
	
	/**
	 * 单图片水印
	 */
	@Test
	public void test7() {
		File originalImage = new File(path+"1.jpg");
		File targetImage = new File(path+"image1.jpg");
		File markImage = new File(path + "logo.gif");
		ImageWaterMark textWaterMark = new SingleImageWaterMark(markImage, 0.3F, 10, 10);
		textWaterMark.addWaterMarker(originalImage, targetImage);
	}
	
	/**
	 * 多图片水印
	 */
	@Test
	public void test8(){
		File originalImage = new File(path+"2.jpg");
		File targetImage = new File(path+"image2.jpg");
		File markImage = new File(path + "logo.gif");
		ImageWaterMark textWaterMark = new MoreImageWaterMark(markImage, 0.3F, 30);
		textWaterMark.addWaterMarker(originalImage, targetImage);
	}
	
}
