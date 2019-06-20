$(document).ready(function(){
	
	getFileEditValues();
	
	/*loginSystem();
	userRegister();*/
});

function getFileEditValues(){
	//上传文件
	$("#fileInput").change(function(){
		var root = $(this).val();
		var fileIndex = root.lastIndexOf('\\');
		var fileName = root.slice(fileIndex+1);
		$('span#fileLoc').html(fileName);
	})
}
//上传文件
$("#uploadFile").click(function(){
//	$("#uploadBook").on('submit',function(){
		$("#uploadBook").ajaxSubmit(function(data){
			if(data != null){
				$.post('./file/updateFileBean',{'file_id':data,'item_id':1},function(data2){
					if(data2 != null){
						alert("上传成功");
					}else{
						alert("上传失败");
					}
				},'json')
			}
		})
//	})
})
//删除文件
$('#delFile').click(function(){
	$.post('./file/deleteFile',{'item_id':1},function(data2){
		if(data2 == "1"){
			alert("删除成功");
//			$('span#fileLoc').html('');
//			$('#fileInput').removeAttr('disabled');
		}else{
			alert("删除失败");
		}
	},'json')
})










/*
function uploadFile() {  
    var fd = new FormData();  
    fd.append("uploadfile", document.getElementById('uploadfile').files[0]);  
    var xhr = new XMLHttpRequest();  
    xhr.upload.addEventListener("progress", uploadProgress, false);  //上传进度  
    xhr.addEventListener("load", uploadComplete, false);  //上传完成  
    xhr.addEventListener("error", uploadFailed, false);   //上传出错  
    xhr.addEventListener("abort", uploadCanceled, false); //取消上传  
    xhr.addEventListener("success", uploadSuccess, false);//上传成功  
    xhr.open("POST", "/TeaQueryHomework/UploadFile");   //Controller里边UploadFile方法  
    xhr.onreadystatechange = function () {  
        if (xhr.readyState == 4 && xhr.status == 200)  
        {  
            var data = xhr.responseText;  
            var content = JSON.parse(data);  
            alert(content);  
        }  
    };  
     xhr.send(fd);  
   
  
}  

//前期工作做得差不错了，剩下的就是从cookie中获取数据，打开flexpaper还有设置一些它的属性
function Preview() {
    
    //用cookie获取存储的文件名
    var path = getCookie("path_Id");

    var test = $("#uploadfile").val();
     $("#dlgPreview").dialog('open');

        var target = "../../Content/TeaFile/SWF/" + path;
        var fp = new FlexPaperViewer(
　　　　'../../FlexPaper/FlexPaperViewer',            对应FlexPaperViewer.swf文件
　　　　'viewerPlaceHolder', {config : {
            SwfFile: escape(target),
            Scale : 0.6,
　　　　　　ZoomTransition : 'easeOut',
　　　　　　ZoomTime : 0.5,
　　　　　　ZoomInterval : 0.2,
　　　　　　FitPageOnLoad : true,
　　　　　　FitWidthOnLoad : true,
　　　　　　FullScreenAsMaxWindow : false,
　　　　　　ProgressiveLoading : false,
　　　　　　MinZoomSize : 0.2,
　　　　　　MaxZoomSize : 5,
　　　　　　SearchMatchAll : false,
　　　　　　InitViewMode : 'Portrait',
　　　　　　ViewModeToolsVisible : true,
　　　　　　ZoomToolsVisible : true,
　　　　　　NavToolsVisible : true,
　　　　　　CursorToolsVisible : true,
　　　　　　SearchToolsVisible : true,
　　　　　　localeChain: 'zh_CN'
		}
	});
  $("#dlgPreview").dialog('open').dialog('setTitle', '预览');
}

function getCookie(Name) //cookies读取
{
    var search = Name + "="
    if (document.cookie.length > 0) {
        offset = document.cookie.indexOf(search)
        if (offset != -1) {
            offset += search.length
            end = document.cookie.indexOf(";", offset)
            if (end == -1) end = document.cookie.length
            return decodeURIComponent(document.cookie.substring(offset, end))
        }
        else return ""
    }
    return ""
}


function loginSystem(){	
	
}
function userRegister(){
	
}*/