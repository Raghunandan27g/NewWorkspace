package com.in.commons.util;
import java.util.Base64;
import java.util.Map;
import java.util.Set;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.in.commons.dao.UserTableDetailsModel;

public class ImageFileCustom {
	public static void storeImageOnServer(String imageData,String imgTypeExt, String imgName) {
		String base64Data = imageData.split(",")[1];
		byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
		ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
		BufferedImage image;
		int count=1;
		try {
			image = ImageIO.read(bis);
			if(new File(UserConstants.FILE_PATH+"\\images\\"+imgName+"."+imgTypeExt).exists()) {
				imgName=imgName+""+count;
			}
			File outputFile = new File(UserConstants.FILE_PATH+"\\images\\"+imgName+"."+imgTypeExt);
			outputFile.exists();
			ImageIO.write(image, imgTypeExt, outputFile); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void storeImageOnServerMap(Map imageDataMap, UserTableDetailsModel objUserTab) {
		try {
				//System.out.println("Inside Map"+(String)imageDataMap.get("dataURL"));
				String mapSplit=(String)imageDataMap.get("dataURL");
				String imgTypeExt=mapSplit.split(";")[0].split("/")[1];
				String imgType=mapSplit.split(";")[0].split("/")[0].replace("data:", "");
				int imgWidth=(int)imageDataMap.get("width");
				int imgHeight=(int)imageDataMap.get("height");
				String imgName=objUserTab.getTxtUrl();
//				System.out.println(imgTypeExt);
//				System.out.println(imgType);
//				System.out.println(imgWidth);
//				System.out.println(imgHeight);
				storeImageOnServer(mapSplit,imgTypeExt,imgName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
