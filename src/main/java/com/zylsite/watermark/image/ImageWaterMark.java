package com.zylsite.watermark.image;

import java.io.File;

import com.zylsite.watermark.AbstractWaterMarker;

public abstract class ImageWaterMark extends AbstractWaterMarker{

	protected File markImage;//水印图片文件
	
	public ImageWaterMark(File markImage){
		this.markImage = markImage;
	}

}
