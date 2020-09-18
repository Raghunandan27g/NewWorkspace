package com.in.commons.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTableDetailsModel {
	
	private String txtGenerationType;
	private String txtName;
	private String txtKeyword; 
	private String txtUrl;
	private String txtSection; 
	private String txtContent;
	
	
	public String getTxtGenerationType() {
		return txtGenerationType;
	}
	public void setTxtGenerationType(String txtGenerationType) {
		this.txtGenerationType = txtGenerationType;
	}
	public String getTxtName() {
		return txtName;
	}
	public void setTxtName(String txtName) {
		this.txtName = txtName;
	}
	public String getTxtKeyword() {
		return txtKeyword;
	}
	public void setTxtKeyword(String txtKeyword) {
		this.txtKeyword = txtKeyword;
	}
	public String getTxtUrl() {
		return txtUrl;
	}
	public void setTxtUrl(String txtUrl) {
		this.txtUrl = txtUrl;
	}
	public String getTxtSection() {
		return txtSection;
	}
	public void setTxtSection(String txtSection) {
		this.txtSection = txtSection;
	}
	public String getTxtContent() {
		return txtContent;
	}
	public void setTxtContent(String txtContent) {
		this.txtContent = txtContent;
	} 

	
}
