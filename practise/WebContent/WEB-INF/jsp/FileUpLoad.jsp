<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basepath= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	pageContext.setAttribute("basepath", basepath);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FileUpLoad</title>
	<!--js-->
    <script type="text/javascript" src="<%=basepath %>js/jQuery/jquery-1.12.2.min.js"></script>
    <script type="text/javascript" src="<%=basepath %>js/jquery.form.js"></script>

</head>
<body>
	
	<form action="./file/uploadToDisk" method="post" enctype="multipart/form-data" id="uploadBook" onsubmit="return false">  
       <p>
       		<span>附&nbsp;件上传</span>
       		<input type="file" name="file" id="fileInput"><span id="fileLoc"></span>
       		<button id="uploadFile">上传</button>
       </p>
    </form>  
    <button id="delFile">删除附件</button>
    
    <hr>  
    <form action="./file/downloadFile" method="post">  
    	<input type="hidden" name="itemId" value="1" id="itemId">
        <input type="submit" value="下载">  
    </form> 
	
	
	
	
	
	
	<!-- 
	<form action="http://localhost:8080/practise/file/upload2" method="post" enctype="multipart/form-data">  
        选择文件:<input type="file" name="file" width="120px">  
        <input type="submit" value="上传">  
    </form>  
    <hr>  
    <form action="http://localhost:8080/practise/file/down4" method="get">  
        <input type="submit" value="下载">  
    </form> 
	 -->
	
	



	<!-- 用于接收path路径 
        <input type="hidden" id="path" value="@ViewData["path"]" />  
        <div id="upload" style="width: 300px; display: block; padding-bottom: 15px; padding-top: 15px;">  
            <input type="file" class="easyui-linkbutton" name="uploadfile" id="uploadfile"  
                multiple="multiple" onchange="fileSelected();" />  
              
        </div>  
    --> 
    <!-- 添加按钮弹出框   
    	<div style="margin-bottom: 5px; margin-top: 20px;">  
            <a id="" class="easyui-linkbutton" href="#" data-options="plain:true,iconCls:'icon-undo'" style="margin-left: 5px;" onclick="uploadFile()">  
           <span>上传</span>  
           </a>  
		</div>  
	-->


	<!-- <div><span style="white-space:pre;"></span>
		<form id="form" enctype="multipart/form-data">  
	    	<span style="white-space:pre;"> </span>
	    	<div class="form-group">  
	            <div class="col-sm-2" >  
	                <label for="fileUpload" class="btn btn-success"><i class="glyphicon glyphicon-plus">上传</i></label>  
	                <span style="white-space:pre;"> </span><input id="fileUpload" name="fileUpload" type="file" style="display:none">  
	            </div>  
	            <div style="border:1px dashed #000; height:76px;width:86px;margin-left: 100px;">   
	                <label class ="col-sm-2control-label">  
	                <img id="image" src =" " alt="" style="height:76px;width:86px;margin-top: 0px;">  
	                  
	                </label>  
	            </div>  
	            <br>  
	            存储文件的解析数据  
	            <input id="file_data" name="file_data" type="text" style="display: none;">  
	            <div class="col-sm-2" >  
                <label for="submit" class="btn btn-success"><i class="glyphicon glyphicon-ok">提交</i></label>  
                <input id="submit" name="submit" type="button" onclick="submitFile();" style="display:none">  
                </div>  
                <div class="col-sm-2" style="margin-left: -87px;">  
                    <label for="reset" class="btn btn-danger"><i class="glyphicon glyphicon-remove">取消</i></label>  
                        <input class="btn btn-danger" id="reset" name="reset" onclick="resetUpload();" type="reset" style="display:none">  
                </div>  
            </div>  
	    </form>  
	</div>   -->
	
</body>
    <script type="text/javascript" src="<%=basepath %>js/upload.js"></script>
</html>