package com.in.commons.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTableDetailsModel {
	
	private String txtSno;
	private String txtName;
	private String txtKeyword;
	private String txtUrl;
	private String txtSection;
	private String txtContent;
	
	
	
	public String getTxtSno() {
		return txtSno;
	}
	public void setTxtSno(String txtSno) {
		this.txtSno = txtSno;
	}
	public String getTxtName() {
		return txtName;
	}
	public void setTxtName(String txtName) {
		this.txtName = txtName.trim().replaceAll(" +", " ");
	}
	public String getTxtKeyword() {
		return txtKeyword;
	}
	public void setTxtKeyword(String txtKeyword) {
		this.txtKeyword = txtKeyword.trim().replaceAll(" +", " ");
	}
	public String getTxtUrl() {
		return txtUrl;
	}
	public void setTxtUrl(String txtUrl) {
		this.txtUrl = txtUrl.trim().replaceAll(" +", "-").toLowerCase();
	}
	public String getTxtSection() {
		return txtSection;
	}
	public void setTxtSection(String txtSection) {
		this.txtSection = txtSection.trim().replaceAll(" +", " ");
	}
	public String getTxtContent() {
		return txtContent;
	}
	public void setTxtContent(String txtContent) {
		this.txtContent = txtContent.trim().replaceAll(" +", " ");
	} 
	
}
