package com.trq.practise.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.trq.practise.pojo.FileBean;
import com.trq.practise.pojo.FileTest;

public interface FileDao {

	void insertFileBean(FileBean fb);
	//传多个参数需要加注解
	void updateFileBean(@Param("file_id")Integer file_id, @Param("item_id")Integer item_id);
	
	List<FileBean> getOldFiles(@Param("file_id")Integer file_id, @Param("item_id")Integer item_id);
	
	void deleteFBInfo(Integer file_id);
	
	FileBean getFileBean(Integer item_id);
	
	void deleteFileBeanInfo(Integer item_id);
	
	
	
	
	
	void insertFileToDatabase(FileTest ft);

	FileTest getFileFromDatabase(Integer id);

	FileTest getFileFromDatabase2(Integer id);






}
