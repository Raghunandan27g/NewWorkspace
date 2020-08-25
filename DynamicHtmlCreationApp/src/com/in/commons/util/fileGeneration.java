package com.in.commons.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class fileGeneration {
	
	public static void indexFileManipulate(String path) {
		// TODO Auto-generated method stub
		boolean fileCreatedStatus;
		try {
			
			String templateFilePath=path+"\\templateFiles";
			File htmlTemplateFile = new File(templateFilePath+"\\templateFile.html");
			
			String newFilePath=path+"\\questions";
			for(int i=1;i<10;i++) {
				File newHtmlFile = new File(newFilePath+"\\question"+i+".html");
				if(newHtmlFile.exists()) {
					System.out.println(newHtmlFile.delete());
				}
				fileCreatedStatus = newHtmlFile.createNewFile();
				if(fileCreatedStatus) {
					String htmlString = FileUtils.readFileToString(htmlTemplateFile);
					String title = "SarkariResult.com : Sarkari Results, Latest Online Form | Result 2020";
					String body = "This is Body";
					String admitCard="<ul>\r\n" + 
							"			<li><a href=\"https://www.sarkariresult.com/ssc/ssc-selection-vii-2019.php\" target=\"_blank\">SSC Selection Post VII Revised Result </a></li>\r\n" + 
							"		</ul>";
					htmlString = htmlString.replace("$description", "Sarkari Results, सरकारी रिजल्ट्स - SarkariResult.com provides you all the latest official Sarkari Result, Online Forms, Sarkari Naukri Jobs in various sectors such as Railway, Bank, SSC, Army, Navy, Police, UPPSC, UPSSSC & other sarkari job alerts at one place.");
					htmlString = htmlString.replace("$title", title);
					htmlString = htmlString.replace("$body", body);
					htmlString = htmlString.replace("$admitCard", admitCard);
					FileUtils.writeStringToFile(newHtmlFile, htmlString);
				}
				else
					System.out.println("File Created Status =>"+fileCreatedStatus);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void templateFileManipulate(String path) {
		// TODO Auto-generated method stub
		boolean fileCreatedStatus;
		try {
			
			String templateFilePath=path+"\\templateFiles";
			File htmlTemplateFile = new File(templateFilePath+"\\templateFile.html");
			
			String newFilePath=path+"\\questions";
			for(int i=1;i<10;i++) {
				File newHtmlFile = new File(newFilePath+"\\question"+i+".html");
				if(newHtmlFile.exists()) {
					System.out.println(newHtmlFile.delete());
				}
				fileCreatedStatus = newHtmlFile.createNewFile();
				if(fileCreatedStatus) {
					String htmlString = FileUtils.readFileToString(htmlTemplateFile);
					String title = "SarkariResult.com : Sarkari Results, Latest Online Form | Result 2020";
					String body = "This is Body";
					String admitCard="<ul>\r\n" + 
							"			<li><a href=\"https://www.sarkariresult.com/ssc/ssc-selection-vii-2019.php\" target=\"_blank\">SSC Selection Post VII Revised Result </a></li>\r\n" + 
							"		</ul>";
					htmlString = htmlString.replace("$description", "Sarkari Results, सरकारी रिजल्ट्स - SarkariResult.com provides you all the latest official Sarkari Result, Online Forms, Sarkari Naukri Jobs in various sectors such as Railway, Bank, SSC, Army, Navy, Police, UPPSC, UPSSSC & other sarkari job alerts at one place.");
					htmlString = htmlString.replace("$title", title);
					htmlString = htmlString.replace("$body", body);
					htmlString = htmlString.replace("$admitCard", admitCard);
					FileUtils.writeStringToFile(newHtmlFile, htmlString);
				}
				else
					System.out.println("File Created Status =>"+fileCreatedStatus);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void templateFileManipulate(String path) {
		// TODO Auto-generated method stub
		boolean fileCreatedStatus;
		try {
			
			String templateFilePath=path+"\\templateFiles";
			File htmlTemplateFile = new File(templateFilePath+"\\templateFile.html");
			
			String newFilePath=path+"\\questions";
			for(int i=1;i<10;i++) {
				File newHtmlFile = new File(newFilePath+"\\question"+i+".html");
				if(newHtmlFile.exists()) {
					System.out.println(newHtmlFile.delete());
				}
				fileCreatedStatus = newHtmlFile.createNewFile();
				if(fileCreatedStatus) {
					String htmlString = FileUtils.readFileToString(htmlTemplateFile);
					String title = "SarkariResult.com : Sarkari Results, Latest Online Form | Result 2020";
					String body = "This is Body";
					String admitCard="<ul>\r\n" + 
							"			<li><a href=\"https://www.sarkariresult.com/ssc/ssc-selection-vii-2019.php\" target=\"_blank\">SSC Selection Post VII Revised Result </a></li>\r\n" + 
							"		</ul>";
					htmlString = htmlString.replace("$description", "Sarkari Results, सरकारी रिजल्ट्स - SarkariResult.com provides you all the latest official Sarkari Result, Online Forms, Sarkari Naukri Jobs in various sectors such as Railway, Bank, SSC, Army, Navy, Police, UPPSC, UPSSSC & other sarkari job alerts at one place.");
					htmlString = htmlString.replace("$title", title);
					htmlString = htmlString.replace("$body", body);
					htmlString = htmlString.replace("$admitCard", admitCard);
					FileUtils.writeStringToFile(newHtmlFile, htmlString);
				}
				else
					System.out.println("File Created Status =>"+fileCreatedStatus);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
