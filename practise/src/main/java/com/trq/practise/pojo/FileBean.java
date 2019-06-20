package com.trq.practise.pojo;

public class FileBean {
	
	//文件数据库id
	private Integer file_id;
	//文件原名
	private String fileName;
	//随机生成的唯一名称
	private String UUIDName;
	//保存路径
	private String savePath;
	//文件二进制流
	private byte[] fileBinary;
	//文件所属条目id(备用)
	private Integer item_id;
	
	public Integer getFile_id() {
		return file_id;
	}
	public void setFile_id(Integer file_id) {
		this.file_id = file_id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUUIDName() {
		return UUIDName;
	}
	public void setUUIDName(String uUIDName) {
		UUIDName = uUIDName;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public byte[] getFileBinary() {
		return fileBinary;
	}
	public void setFileBinary(byte[] fileBinary) {
		this.fileBinary = fileBinary;
	}
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	
	
}
