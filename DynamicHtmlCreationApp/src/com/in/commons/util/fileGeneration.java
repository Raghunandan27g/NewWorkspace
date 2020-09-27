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
	
	public synchronized static boolean homePageFileManipulate(String path,Map userTableModelMap,boolean completeGenFlag) {
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
	            	if(key.equals(UserConstants.SECTION_ADMIT_CARD)) {
	            		strAdmitCard.append(SimpleGenerationUtilities.ulBasicDesign(userTabObj));
	            		if(completeGenFlag)
	            			multiPageTemplateFileManipulate(path,userTabObj);
	            	}
	            	else if(key.equals(UserConstants.SECTION_LATEST_JOBS)) {
	            		strLatestJobs.append(SimpleGenerationUtilities.ulBasicDesign(userTabObj));
	            		if(completeGenFlag)
	            			multiPageTemplateFileManipulate(path,userTabObj);
	            	}
	            	else if(key.equals(UserConstants.SECTION_LATEST_RESULTS)) {
	            		strLatestResults.append(SimpleGenerationUtilities.ulBasicDesign(userTabObj));
	            		if(completeGenFlag)
	            			multiPageTemplateFileManipulate(path,userTabObj);
	            	}
	            	else if(key.equals(UserConstants.SECTION_IMP_LINKS)) {
	            		strOtherImpLinks.append(SimpleGenerationUtilities.ulBasicDesign(userTabObj));
	            		if(completeGenFlag)
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
					htmlString = htmlString.replace("$"+UserConstants.SECTION_ADMIT_CARD+"$", strAdmitCard);
					htmlString = htmlString.replace("$"+UserConstants.SECTION_LATEST_JOBS+"$", strLatestJobs);
					htmlString = htmlString.replace("$"+UserConstants.SECTION_LATEST_RESULTS+"$", strLatestResults);
					htmlString = htmlString.replace("$"+UserConstants.SECTION_IMP_LINKS+"$", strOtherImpLinks);
					
					FileUtils.writeStringToFile(newHtmlFile, htmlString,"UTF-8");
				}
				else
					System.out.println("File Created Status =>"+fileCreatedStatus);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileCreatedStatus;
	}
	
	public synchronized static boolean multiPageTemplateFileManipulate(String path,UserTableDetailsModel userTabObj) {
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
	
	public synchronized static boolean singlePageTemplateFileManipulate(String path) {
		return false;
	}

	public synchronized static boolean insertFileManipulateHome(String path,UserTableDetailsModel objUserTab) {
		// TODO Auto-generated method stub
		boolean fileExist=false;
		try {
			String templateFilePath=path;
			File htmlTemplateFile = new File(templateFilePath+"\\Home.html");
			String htmlString = FileUtils.readFileToString(htmlTemplateFile);
			String newFilePath=path;

			File newHtmlFile = new File(newFilePath+"\\Home.html");
				if(newHtmlFile.exists()) {
					fileExist=true;
					htmlString = htmlString.replace("<li style=\"display: none;\">$"+objUserTab.getTxtSection()+"_One$</li>", SimpleGenerationUtilities.oneFileliDesign(objUserTab));
					FileUtils.writeStringToFile(newHtmlFile, htmlString,"UTF-8");
				}
				else
					System.out.println("Not able to Find Home file:"+fileExist);
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
				if(!fileExist) {
					throw new ArithmeticException("TryAgain"); 
				}
		}
		return fileExist;
	}
}
