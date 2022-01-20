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
        String path = "F:/gland-file" ;
        List<GlandFileEntity> list = new ArrayList<GlandFileEntity>();
        Integer count=0;
        for(MultipartFile file:files){
        	String suffix = file.getOriginalFilename().split("\\.")[1];
        	
        	;
        	
            String fileName = CommentSortEnum.getTargetName(imageType).getDesc()+"_"+imageType+"_"+checkDateImage+"_"+fileTypeArr[imageType]+"."+suffix;
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
                   continue;
                } 
            }
        }
        glandFileService.saveBatch(list);
        return "true";
    }
	
	
}
