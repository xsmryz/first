package com.trq.practise.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.trq.practise.pojo.FileBean;
import com.trq.practise.pojo.FileTest;
import com.trq.practise.service.FileService;

@RequestMapping("file")  
@Controller  
public class FileController {  
	
//	private final String PATH = "../file";
	
	@Autowired
	private FileService fileService;
	
	/**
	 * 文件上传到磁盘(并返回file_id,用于和所属条目绑定)
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/uploadToDisk")
	@ResponseBody
	public Integer uploadToDisk(MultipartFile file,HttpServletRequest request) throws Exception{
		//文件上传到磁盘固定路径
		String savePath = "D:\\Applications\\uploadFiles";
			//上传到tomcat\webapps\file
			//String savePath = request.getSession().getServletContext().getRealPath(PATH);
		//开始上传
//        String contentType = file.getContentType();  //获取文件类型
    	String fileName = file.getOriginalFilename();
    	String UUIDName = (UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."))).replaceAll("-", "");
    	//将文件的信息保存到数据库
    	FileBean fb = new FileBean();
    	fb.setFileName(fileName);
    	fb.setUUIDName(UUIDName);
    	fb.setSavePath(savePath.replace("\\", "/"));
    	fileService.insertFileBean(fb);
    	//将文件保存到指定路径
    	File newFile = new File(savePath,UUIDName);          
        if(!newFile.exists()){  
        	newFile.mkdirs();  
        }  
        //MultipartFile自带的解析方法  
        file.transferTo(newFile); 
        
		return fb.getFile_id();
	}
	
	/**
	 * 将文件和条目绑定,并删除该条目之前的附件(条目和附件一对一)
	 * @param file_id
	 * @param item_id
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateFileBean")
	@ResponseBody
	public String updateFileBean(Integer file_id,Integer item_id,HttpServletRequest request){
		try {
			fileService.updateFileBean(file_id,item_id,request);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return "1";
	}
	
	/**
	 * 下载附件
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/downloadFile")
	public void downloadFile(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//接收form表单隐藏域传过来的参数
		Integer item_id = Integer.valueOf(request.getParameter("itemId"));
		FileBean fb = fileService.getFileBean(item_id);
		String filePath = fb.getSavePath()+"\\"+fb.getUUIDName();
		//获取输入流
		InputStream bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
        //假如以中文名下载的话  
        String filename = fb.getFileName(); 
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);  
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        bis.close();
        out.close();  
	}
	
	@RequestMapping("/deleteFile")
	@ResponseBody
	public String deleteFile(Integer item_id,HttpServletRequest request){
		try {
			FileBean fb = fileService.getFileBean(item_id);
			if(fb!=null){
				//删除文件
//			String filePath = fb.getSavePath()+"\\"+fb.getUUIDName();
				String filePath = fb.getSavePath()+"/"+fb.getUUIDName();    //ok
				File file = new File(filePath);
				file.delete();
				//删除数据库文件信息
				fileService.deleteFileBeanInfo(item_id);
				//将条目下的附件名置为null
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return "1";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**  
     * 文件上传功能2:将文件保存到本地项目中,数据库加入保存路径及文件名  
     * @param file  
     * 
     */  
    @RequestMapping(value="/upload2",method=RequestMethod.POST)  
    @ResponseBody  
    public FileTest upload2(MultipartFile file,HttpServletRequest request) throws Exception{  
    	//拼接文件存储的目录
        String temp=Thread.currentThread().getContextClassLoader().getResource("").getPath(); 
        int num=temp.indexOf(".metadata");
        String savePath=temp.substring(1,num).replace('/', '\\')+request.getContextPath().replaceAll("/", "")+"\\WebContent\\WEB-INF\\uploadFiles\\";
    	//上传到本地
        String contentType = file.getContentType();  
    	String oldName = file.getOriginalFilename();
    	String newName1 = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
    	String newName = newName1.replaceAll("-", "");
    	
    	
    	
    	//将上传文件的信息保存到数据库
        FileTest ft = new FileTest();
        byte[] bytes = file.getBytes();	//这一行不能放在file.transferTo(newFile)的后面,否则file就被解析掉了,所以会提示找不到file.
        ft.setFile(bytes);
        ft.setFileName(newName);
        ft.setFilePath(savePath.replace('\\', '/'));
        ft.setOldName(oldName);
        fileService.insertFileToDatabase(ft);
    	
    	File newFile = new File(savePath,newName);          
        if(!newFile.exists()){  
        	newFile.mkdirs();  
        }  
        //MultipartFile自带的解析方法  
        file.transferTo(newFile); 
    	
        FileTest fileTest = fileService.getFileFromDatabase2(ft.getId());
    	
        return fileTest;
    }
	
    /**  
     * 文件下载功能2(从服务器下载文件)
     * @param request  
     * @param response  
     * @throws Exception  
     */  
    @RequestMapping("/down2")  
    public void down2(HttpServletRequest request,HttpServletResponse response) throws Exception{  
	
    	Integer id = 15;
    	FileTest ft = fileService.getFileFromDatabase(id);
    	String fileName = ft.getFilePath()+ft.getFileName();
    	//获取输入流  
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
        //假如以中文名下载的话  
        String filename = ft.getOldName();  
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);  
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        bis.close();
        out.close();  
        
        
    }
    
    /**  
     * 文件下载功能3  
     * @param request  
     * @param response  
     * @throws Exception  
     */  
    @RequestMapping("/down3")  
    public void down3(HttpServletRequest request,HttpServletResponse response) throws Exception{  
    	
    	FileOutputStream out = null;
        FileOutputStream outSTr = null;
        BufferedOutputStream Buff = null;
        FileWriter fw = null;

        int count = 1000;//写文件行数

        try {
            //经过测试：FileOutputStream执行耗时:17，6，10 毫秒
            out = new FileOutputStream(new File("C:\\Users\\TTT\\Desktop\\a.txt"));
            long begin = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                out.write("测试java 文件操作\r\n".getBytes());
            }
            out.close();
            long end = System.currentTimeMillis();
            System.out.println("FileOutputStream执行耗时:" + (end - begin) + " 毫秒");

            //经过测试：BufferedOutputStream执行耗时:1,1，1 毫秒
            outSTr = new FileOutputStream(new File("C:\\Users\\TTT\\Desktop\\b.txt"));
            Buff = new BufferedOutputStream(outSTr);
            long begin0 = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                Buff.write("测试java 文件操作\r\n".getBytes());
            }
            Buff.flush();
            Buff.close();
            long end0 = System.currentTimeMillis();
            System.out.println("BufferedOutputStream执行耗时:" + (end0 - begin0) + " 毫秒");

            //经过测试：FileWriter执行耗时:3,9，5 毫秒
            fw = new FileWriter("C:\\Users\\TTT\\Desktop\\c.txt");
            long begin3 = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                fw.write("测试java 文件操作\r\n");
            }
            fw.close();
            long end3 = System.currentTimeMillis();
            System.out.println("FileWriter执行耗时:" + (end3 - begin3) + " 毫秒");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
                Buff.close();
                outSTr.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    	
    }
    /**  
     * 文件下载功能4(从数据库下载文件)  
     * @param request  
     * @param response  
     * @throws Exception  
     */  
    @RequestMapping("/down4")  
    public void down4(HttpServletRequest request,HttpServletResponse response) throws Exception{  
    	
    	FileOutputStream outSTr = null;
        BufferedOutputStream Buff = null;
        
    	Integer id = 11;
    	FileTest ft = fileService.getFileFromDatabase(id);
    	byte[] bytes = ft.getFile();
    	try {
    		
    		//经过测试：BufferedOutputStream执行耗时:1,1，1 毫秒
            outSTr = new FileOutputStream(new File("C:\\Users\\TTT\\Desktop\\b.png"));
            Buff = new BufferedOutputStream(outSTr);
            long begin0 = System.currentTimeMillis();
            
                Buff.write(bytes);
            
            Buff.flush();
            Buff.close();
            long end0 = System.currentTimeMillis();
            System.out.println("BufferedOutputStream执行耗时:" + (end0 - begin0) + " 毫秒");
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			 Buff.close();
                 outSTr.close();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    }
	
	
	
	
	
	
	
	
    /**  
     * 文件上传功能  
     * @param file  
     * @return  
     * @throws IOException   
     */  
    @RequestMapping(value="/upload",method=RequestMethod.POST)  
    @ResponseBody  
    public String upload(MultipartFile file,HttpServletRequest request) throws IOException{  
    	String contentType = file.getContentType();     //text/html 
    	String filename = file.getOriginalFilename();	//已删除的应用.html
    	byte[] bytes = file.getBytes();
    	FileTest ft = new FileTest();
    	ft.setFile(bytes);
    	ft.setFileName(filename);
    	fileService.insertFileToDatabase(ft);
    	
    	/*上传到本地
        String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = file.getOriginalFilename();    
        File dir = new File(path,fileName);          
        if(!dir.exists()){  
            dir.mkdirs();  
        }  
        //MultipartFile自带的解析方法  
        file.transferTo(dir);  
        return "ok!";  
        */
    	return "FileUpLoad成功";  //就是个字符串
    }  
      
    /**  
     * 文件下载功能  
     * @param request  
     * @param response  
     * @throws Exception  
     */  
    @RequestMapping("/down")  
    public void down(HttpServletRequest request,HttpServletResponse response) throws Exception{  
        //获取数据库中保存的文件
    	Integer id = 1;
    	FileTest ft = fileService.getFileFromDatabase(id);
    	byte[] bytes = ft.getFile();
    	
    	InputStream in = new ByteArrayInputStream(bytes);
    	
    	File file=new File("D:\\重置系统时删除应用.png");
    	FileOutputStream outputStream = new FileOutputStream(file);
    	
         byte[] b = new byte[1024];  
         int nRead = 0;  
         while ((nRead = in.read(b)) != -1) {  
        	 outputStream.write(b, 0, nRead);  
         }  
         outputStream.flush();  
         outputStream.close();  
         in.close();  
    	
    	
    	/*本地下载
    	//模拟文件，myfile.txt为需要下载的文件  
        String fileName = request.getSession().getServletContext().getRealPath("upload")+"/已删除的应用.html";  
        //获取输入流  
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));  
        //假如以中文名下载的话  
        String filename = "下载文件.html";  
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();  
        */
    }  
}  
