package com.app.gland.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.app.gland.entity.GlandFileEntity;
import com.app.gland.inteface.GlandFileService;
import com.app.gland.unit.BaseController;

@Controller
public class FileController extends BaseController {
	
	
	private static  String[] fileTypeArr =new String[] {"血检","超声"};
	
	@Autowired
	private GlandFileService glandFileService;
	
	/**
     * 实现多文件上传
     * */
	@ResponseBody
    @RequestMapping(value="multifileUpload",method=RequestMethod.POST) 
    public  String multifileUpload(HttpServletRequest request){
    	String fileDate = get(request,"fileDate");
    	
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileNameGlass");
        List<MultipartFile> files2 = ((MultipartHttpServletRequest)request).getFiles("fileNameSupersound");
        if(files.isEmpty()){
            return "false";
        }
        String path = "F:/gland-file" ;
        List<GlandFileEntity> list = new ArrayList<GlandFileEntity>();
        Integer count=0;
        for(MultipartFile file:files){
        	String suffix = file.getOriginalFilename().split("\\.")[1];
            String fileName = fileDate+"_"+fileTypeArr[0]+"_"+count+"."+suffix;
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
                    dto.setCheckDate(fileDate);
                    dto.setPath(allpath);
                    dto.setFileType(0+"");
                    dto.setName(fileName);
                    list.add(dto);
                }catch (Exception e) {
                   continue;
                } 
            }
        }
        glandFileService.saveBatch(list);
        return "true";
    }
	
	
}
