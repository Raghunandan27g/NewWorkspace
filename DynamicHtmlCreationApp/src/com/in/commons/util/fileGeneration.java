package com.in.commons.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;

import com.in.commons.dao.UserTableDetailsModel;

import sun.nio.cs.StandardCharsets;

public class fileGeneration {
	
	public static boolean indexTemplateFileManipulate(String path,Map userTableModelMap) {
		boolean fileCreatedStatus=false;
		StringBuilder strAdmitCard = new StringBuilder();
		StringBuilder strLatestJobs = new StringBuilder();
		StringBuilder strLatestResults = new StringBuilder();
		StringBuilder strOtherImpLinks = new StringBuilder();
		try {
	        Set<String> keys = userTableModelMap.keySet();
	        for (String key : keys) {
	            System.out.println("Key = " + key);
	            ArrayList<UserTableDetailsModel> arrListUserTabModel=(ArrayList<UserTableDetailsModel>)userTableModelMap.get(key);
	            for(UserTableDetailsModel userTabObj : arrListUserTabModel){
	            	if(key.equals("admitCard")) {
	            		//AdmitCard related codes
	            		strAdmitCard.append(SimpleGenerationUtilities.ulBasicDesign(userTabObj));
	            		multiPageTemplateFileManipulate(path,userTabObj);
	            		//new file generate
	            	}
	            	else if(key.equals("latestJobs")) {
	            		//LatestJobs related codes
	            		strLatestJobs.append(SimpleGenerationUtilities.ulBasicDesign(userTabObj));
	            		multiPageTemplateFileManipulate(path,userTabObj);
	            	}
	            	else if(key.equals("latestResult")) {
	            		//LatestResults related codes
	            		strLatestResults.append(SimpleGenerationUtilities.ulBasicDesign(userTabObj));
	            		multiPageTemplateFileManipulate(path,userTabObj);
	            	}
	            	else if(key.equals("otherImpLinks")) {
	            		//otherImpLinks related codes
	            		strOtherImpLinks.append(SimpleGenerationUtilities.ulBasicDesign(userTabObj));
	            		multiPageTemplateFileManipulate(path,userTabObj);
	            	}
				}
	        }
	        
			File htmlTemplateFile = new File(path+"\\templateFiles\\HomeTemplate.html");
			String newFilePath=path;
				File newHtmlFile = new File(newFilePath+"\\Home.html");
				if(newHtmlFile.exists()) {
					FileUtils.writeStringToFile(newHtmlFile, "ServerDown","UTF-8");
					fileCreatedStatus = true;
					//System.out.println(newHtmlFile.delete());
				}
				else {
					fileCreatedStatus = newHtmlFile.createNewFile();
				}
				if(fileCreatedStatus) {
					String htmlString = FileUtils.readFileToString(htmlTemplateFile);
					String title = "SarkariResult.com : Sarkari Results, Latest Online Form | Result 2020";
					htmlString = htmlString.replace("$description", "Sarkari Results, सरकारी रिजल्ट्स - SarkariResult.com provides you all the latest official Sarkari Result, Online Forms, Sarkari Naukri Jobs in various sectors such as Railway, Bank, SSC, Army, Navy, Police, UPPSC, UPSSSC & other sarkari job alerts at one place.");
					htmlString = htmlString.replace("$title", title);
					htmlString = htmlString.replace("$admitCard$", strAdmitCard);
					htmlString = htmlString.replace("$latestJobs$", strLatestJobs);
					htmlString = htmlString.replace("$latestResult$", strLatestResults);
					htmlString = htmlString.replace("$otherImpLinks$", strOtherImpLinks);
					
					FileUtils.writeStringToFile(newHtmlFile, htmlString,"UTF-8");
				}
				else
					System.out.println("File Created Status =>"+fileCreatedStatus);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileCreatedStatus;
	}
	
	public static boolean multiPageTemplateFileManipulate(String path,UserTableDetailsModel userTabObj) {
		// TODO Auto-generated method stub
		boolean fileCreatedStatus=false;
		try {
			
			String templateFilePath=path+"\\templateFiles";
			File htmlTemplateFile = new File(templateFilePath+"\\templateFile.html");
			String newFilePath=path+"\\"+userTabObj.getTxtSection();

			File newHtmlFile = new File(newFilePath+"\\"+userTabObj.getTxtUrl()+".html");
				if(newHtmlFile.exists()) {
					FileUtils.writeStringToFile(newHtmlFile, "ServerDown","UTF-8");
					fileCreatedStatus = true;
					//System.out.println(newHtmlFile.delete());
				}
				else {
					fileCreatedStatus = newHtmlFile.createNewFile();
				}
				if(fileCreatedStatus) {
					String htmlString = FileUtils.readFileToString(htmlTemplateFile);
					String title = "SarkariResult.com : Sarkari Results, Latest Online Form | Result 2020";
					htmlString = htmlString.replace("$description", "Sarkari Results, सरकारी रिजल्ट्स - SarkariResult.com provides you all the latest official Sarkari Result, Online Forms, Sarkari Naukri Jobs in various sectors such as Railway, Bank, SSC, Army, Navy, Police, UPPSC, UPSSSC & other sarkari job alerts at one place.");
					htmlString = htmlString.replace("$title", title);
					htmlString = htmlString.replace("$content$", userTabObj.getTxtContent());
					FileUtils.writeStringToFile(newHtmlFile, htmlString,"UTF-8");
				}
				else
					System.out.println("File Created Status =>"+fileCreatedStatus);
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
				if(!fileCreatedStatus) {
					throw new ArithmeticException("TryAgain"); 
				}
		}
		return fileCreatedStatus;
	}
	
	public static boolean singlePageTemplateFileManipulate(String path) {
		// TODO Auto-generated method stub
		boolean fileCreatedStatus=false;
		try {
			
			String templateFilePath=path;
			File htmlTemplateFile = new File(templateFilePath+"\\Home.html");
			String htmlString = FileUtils.readFileToString(htmlTemplateFile);
			String newFilePath=path;

			File newHtmlFile = new File(newFilePath+"\\Home.html");
				if(newHtmlFile.exists()) {
					FileUtils.writeStringToFile(newHtmlFile, "ServerDown","UTF-8");
					fileCreatedStatus = true;
				}
				else {
					fileCreatedStatus = newHtmlFile.createNewFile();
				}
				if(fileCreatedStatus) {
					String title = "SarkariResult.com : Sarkari Results, Latest Online Form | Result 2020";
					htmlString = htmlString.replace("<li style=\"display: none;\">$admitCard_One$</li>", SimpleGenerationUtilities.oneFileliDesign());
					FileUtils.writeStringToFile(newHtmlFile, htmlString,"UTF-8");
				}
				else
					System.out.println("File Created Status =>"+fileCreatedStatus);
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
				if(!fileCreatedStatus) {
					throw new ArithmeticException("TryAgain"); 
				}
		}
		return fileCreatedStatus;
	}
}
