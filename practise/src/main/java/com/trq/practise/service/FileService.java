package com.trq.practise.service;

import javax.servlet.http.HttpServletRequest;

import com.trq.practise.pojo.FileBean;
import com.trq.practise.pojo.FileTest;

public interface FileService {

	void insertFileToDatabase(FileTest ft);
	
	void updateFileBean(Integer file_id, Integer item_id, HttpServletRequest request);

	FileBean getFileBean(Integer item_id);
	
	void deleteFileBeanInfo(Integer item_id);
	
	
	
	
	
	FileTest getFileFromDatabase(Integer id);

	FileTest getFileFromDatabase2(Integer id);

	void insertFileBean(FileBean fb);



}
