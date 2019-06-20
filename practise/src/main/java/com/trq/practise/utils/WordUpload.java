package com.trq.practise.utils;

public class WordUpload {
	
	/*
	 * 调用controller中的方法，因为要预览，所有只上传文件还是不行的，这里用到了一个flexpaper插件，
	 * 将office文件转换为pdf文件，再将pdf文件转为swf文件，才可以被flexpaper预览。
	 */
	
	/*
	 public ActionResult UploadFile(HttpPostedFileBase[] fileToUpload)  
    {  
        //变量定义pdf转为swf的工具路径  
        string pdf2swfToolPath = System.Web.HttpContext.Current.Server.MapPath("~/FlexPaper/pdf2swf.exe");  

        //定义保存路径的变量  
        string OfficeFilePath = Server.MapPath("~/Content/TeaFile/OFFICE/");  
        string PdfFilePath = Server.MapPath("~/Content/TeaFile/PDF/");  
        string SWFFilePath = Server.MapPath("~/Content/TeaFile/SWF/");  
        string SwfFileName = String.Empty;  


        //上传word文件  
        HttpPostedFileBase uploadfile = Request.Files["uploadfile"];  
        if (uploadfile == null || string.IsNullOrEmpty(uploadfile.FileName))  
        {  
            return Json("请选择上传文件！");  
        }  
        //取得上传文件之扩展文件名，并转换成小写字母  
        string fileExtension = System.IO.Path.GetExtension(uploadfile.FileName).ToLower();  
        if (fileExtension != ".docx")  
        {  
            return Json("只能上传word文件！");  
        }  

        ////存储文件到文件夹  
        uploadfile.SaveAs(Server.MapPath("../Content/TeaFile/OFFICE/" + uploadfile.FileName));  
        string PdfFileName = OfficeToPdf(OfficeFilePath, uploadfile.FileName, PdfFilePath);  
        SwfFileName = PdfToSwf(pdf2swfToolPath, PdfFilePath, PdfFileName, SWFFilePath);  

      ViewData["path"] = SWFFilePath;  
      HttpCookie cookie = new HttpCookie("path_Id");  
       
  //向cookie中传时进行编码（防止中文乱码）  
      string path2 = System.Web.HttpUtility.UrlEncode(SwfFileName, Encoding.UTF8);  
     cookie.Value = path2;  
     //添加cookie  
     Response.Cookies.Add(cookie);  
 return Json("上传成功！");    
}  
	
	//OfficeToPdf-将office文件转化为pdf文件
	 private string OfficeToPdf(string OfficePath, string OfficeName, string destPath)
     {
         string fullPathName = OfficePath + OfficeName;//包含 路径 的全称
         string fileNameWithoutEx = System.IO.Path.GetFileNameWithoutExtension(OfficeName);//不包含路径，不包含扩展名
         string extendName = System.IO.Path.GetExtension(OfficeName).ToLower();//文件扩展名
         string saveName = destPath + fileNameWithoutEx + ".pdf";
         string returnValue = fileNameWithoutEx + ".pdf";

         switch (extendName)
         {
             case ".doc":
                 PreviewConvert.WordToPDF(fullPathName, saveName);
                 break;
             case ".docx":
                 PreviewConvert.WordToPDF(fullPathName, saveName);
                 break;
             case ".xls":
                 PreviewConvert.ExcelToPDF(fullPathName, saveName);
                 break;
             case ".xlsx":
                 PreviewConvert.ExcelToPDF(fullPathName, saveName);
                 break;
             default:
                 returnValue = "";
                 break;
         }
         return returnValue;
     }
	
	//PdfToSwf-将pdf文件转化为swf文件
	 private string PdfToSwf(string pdf2swfPath, string PdfPath, string PdfName, string destPath)
     {
         string fullPathName = PdfPath + PdfName;//包含 路径 的全称
         string fileNameWithoutEx = System.IO.Path.GetFileNameWithoutExtension(PdfName);//不包含路径，不包含扩展名
         string extendName = System.IO.Path.GetExtension(PdfName).ToLower();//文件扩展名
         string saveName = destPath + fileNameWithoutEx + ".swf";
         string returnValue = fileNameWithoutEx + ".swf"; 
         if (extendName != ".pdf")
         {
             returnValue = "";
         }
         else
         {
             PreviewConvert.PDFToSWF(pdf2swfPath, fullPathName, saveName);
         }
         return returnValue;
     }
	 */
	 
	 
	
}
