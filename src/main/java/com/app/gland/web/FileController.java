package com.app.gland.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.app.gland.common.CommentSortEnum;
import com.app.gland.entity.GlandFileEntity;
import com.app.gland.inteface.GlandFileService;
import com.app.gland.unit.BaseController;

@Controller
public class FileController extends BaseController {
	
	
	private static  String[] fileTypeArr =new String[] {"血检","X光","超声","CT","手术"};
	@Autowired
	private GlandFileService glandFileService;
	
	/**
     * 实现多文件上传
     * */
	@ResponseBody
    @RequestMapping(value="multifileUpload",method=RequestMethod.POST) 
    public  String multifileUpload(HttpServletRequest request){

		Integer imageType = getInteger(request,"imageType");
		String checkDateImage = get(request,"checkDateImage");
		
		
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        if(files.isEmpty()){
            return "false";
        }
        
        
        String path = "/www/server/file/gland" ;
        
        String osName = System.getProperty( "os.name" );
        
        if( osName.toLowerCase(Locale.ROOT).startsWith( "win" ) ) 
        {
        	path = "F:/gland-file";
        }
        File dir = new File( path ); //若目录不存在，创建
        if( !dir.exists() || !dir.isDirectory() )
        {
            boolean reMkdir = dir.mkdir();
            if( !reMkdir )
            {
                System.out.println( "创建目录失败：" + path );
            }
        }
        
        List<GlandFileEntity> list = new ArrayList<GlandFileEntity>();
        Integer count=0;
        for(MultipartFile file:files){
        	String suffix = file.getOriginalFilename().split("\\.")[1];
        	
        	;
        	
            String fileName = CommentSortEnum.getTargetName(imageType).getDesc()+"_"+imageType+"_"+checkDateImage+"_"+fileTypeArr[imageType]+new Date().getTime()+"."+suffix;
            if(file.isEmpty()){
                continue;
            }else{        
            	String allpath = path + "/" + fileName;
                File dest = new File(path + "/" + fileName);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                    count++;
                    GlandFileEntity dto = new GlandFileEntity();
                    dto.setCheckDate(checkDateImage);
                    dto.setPath(allpath);
                    dto.setFileType(imageType+"");
                    dto.setName(fileName);
                    list.add(dto);
                }catch (Exception e) {
                  logger.error("保存发生异常:e",e);
                   continue;
                } 
            }
        }
        glandFileService.saveBatch(list);
        return "true";
    }
	
	@ResponseBody
    @RequestMapping("readImg") 
    public  String readImg(HttpServletRequest request,HttpServletResponse response)throws IOException {
		GlandFileEntity entity = glandFileService.getById(get(request, "id"));
		String url = entity.getPath();
		File file = new File(url);  
        if(!file.exists()) {  
            return null;  
        }  
        ServletOutputStream out = null;    
    	FileInputStream ips = null;
        try {
        	
        	ips = new FileInputStream(file);    
            response.setContentType("multipart/form-data");    
            out = response.getOutputStream();    
            //读取文件流    
            int len = 0;    
            byte[] buffer = new byte[1024 * 10];    
            while ((len = ips.read(buffer)) != -1){    
                out.write(buffer,0,len);    
            }    
            out.flush();    
		} catch (Exception e) {
			// TODO: handle exception
		}finally {    
	        out.close();    
	        ips.close();    
	    }  
		return null;
	}
}
