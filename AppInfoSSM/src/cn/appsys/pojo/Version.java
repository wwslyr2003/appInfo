package cn.appsys.pojo;

import java.util.Date;

public class Version { 
	private String appName;
			private int id;
			private int appId; 
			private String versionNo; 
			private String versionInfo; 
			private int publishStatus; 
			private String downloadLink; 
			private double versionSize; 
			private int createdBy; 
			private Date creationDate; 
			private int modifyBy; 
			private Date modifyDate; 
			private String apkLocPath; 
			private String apkFileName;
			private String publishStatusName;
				
			public String getPublishStatusName() {
				return publishStatusName;
			}
			public void setPublishStatusName(String publishStatusName) {
				this.publishStatusName = publishStatusName;
			}
			public String getAppName() {
				return appName;
			}
			public void setAppName(String appName) {
				this.appName = appName;
			}
			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}
			public int getAppId() {
				return appId;
			}
			public void setAppId(int appId) {
				this.appId = appId;
			}
			public String getVersionNo() {
				return versionNo;
			}
			public void setVersionNo(String versionNo) {
				this.versionNo = versionNo;
			}
			public String getVersionInfo() {
				return versionInfo;
			}
			public void setVersionInfo(String versionInfo) {
				this.versionInfo = versionInfo;
			}
			public int getPublishStatus() {
				return publishStatus;
			}
			public void setPublishStatus(int publishStatus) {
				this.publishStatus = publishStatus;
			}
			public String getDownloadLink() {
				return downloadLink;
			}
			public void setDownloadLink(String downloadLink) {
				this.downloadLink = downloadLink;
			}
			public double getVersionSize() {
				return versionSize;
			}
			public void setVersionSize(double versionSize) {
				this.versionSize = versionSize;
			}
			public int getCreatedBy() {
				return createdBy;
			}
			public void setCreatedBy(int createdBy) {
				this.createdBy = createdBy;
			}
			public Date getCreationDate() {
				return creationDate;
			}
			public void setCreationDate(Date creationDate) {
				this.creationDate = creationDate;
			}
			public int getModifyBy() {
				return modifyBy;
			}
			public void setModifyBy(int modifyBy) {
				this.modifyBy = modifyBy;
			}
			public Date getModifyDate() {
				return modifyDate;
			}
			public void setModifyDate(Date modifyDate) {
				this.modifyDate = modifyDate;
			}
			public String getApkLocPath() {
				return apkLocPath;
			}
			public void setApkLocPath(String apkLocPath) {
				this.apkLocPath = apkLocPath;
			}
			public String getApkFileName() {
				return apkFileName;
			}
			public void setApkFileName(String apkFileName) {
				this.apkFileName = apkFileName;
			}
			public Version(int id, int appId, String versionNo,
					String versionInfo, int publishStatus, String downloadLink,
					double versionSize, int createdBy, Date creationDate,
					int modifyBy, Date modifyDate, String apkLocPath,
					String apkFileName) {
				super();
				this.id = id;
				this.appId = appId;
				this.versionNo = versionNo;
				this.versionInfo = versionInfo;
				this.publishStatus = publishStatus;
				this.downloadLink = downloadLink;
				this.versionSize = versionSize;
				this.createdBy = createdBy;
				this.creationDate = creationDate;
				this.modifyBy = modifyBy;
				this.modifyDate = modifyDate;
				this.apkLocPath = apkLocPath;
				this.apkFileName = apkFileName;
			}
			public Version() {
				super();
			}
			
}
