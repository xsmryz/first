package com.trq.practise.serviceImpl;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trq.practise.dao.FileDao;
import com.trq.practise.pojo.FileBean;
import com.trq.practise.pojo.FileTest;
import com.trq.practise.service.FileService;

@Service
@Transactional
public class FileServiceImpl implements FileService {

	@Autowired
	private FileDao fileDao;

	@Override
	public void insertFileBean(FileBean fb) {
		fileDao.insertFileBean(fb);
	}
	
	@Override
	public void updateFileBean(Integer file_id, Integer item_id, HttpServletRequest request) {
		//将item_id和上传文件绑定
		fileDao.updateFileBean(file_id,item_id);
		//一个条目对应一个文件附件,所以查找并删除之前的附件
		List<FileBean> list = fileDao.getOldFiles(file_id,item_id);
		if(list!=null && list.size()!=0){
			for (FileBean fb : list) {
				//删除文件
				String filePath = fb.getSavePath()+fb.getUUIDName();
				File file = new File(filePath);
				file.delete();
				//删除对应数据库信息
				fileDao.deleteFBInfo(fb.getFile_id());
			}
		}
		//将新附件的名称更新到条目表中(在条目信息中显示附件名称,根据item_id就可以实现下载\预览\删除)
		
	}
	
	@Override
	public FileBean getFileBean(Integer item_id) {
		return fileDao.getFileBean(item_id);
	}
	
	@Override
	public void deleteFileBeanInfo(Integer item_id) {
		fileDao.deleteFileBeanInfo(item_id);
	}
	
	
	
	
	
	
	
	
	
	@Override
	public void insertFileToDatabase(FileTest ft) {
		fileDao.insertFileToDatabase(ft);
	}

	@Override
	public FileTest getFileFromDatabase(Integer id) {
		return fileDao.getFileFromDatabase(id);
	}
	
	@Override
	public FileTest getFileFromDatabase2(Integer id) {
		return fileDao.getFileFromDatabase2(id);
	}

	

	










	
	
}
