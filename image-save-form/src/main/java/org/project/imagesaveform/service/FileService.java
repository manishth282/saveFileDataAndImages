package org.project.imagesaveform.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	public String savePhoto( String path, MultipartFile file,String refNo) throws IOException,FileUploadException{
		
		
		//File name
		String name =file.getOriginalFilename();
		String newFileName = "photo"+refNo+name.substring(name.lastIndexOf("."));
		//check file type
		String fileExtension = name.substring(name.lastIndexOf(".")+1).toUpperCase();
		if (!fileExtension.equals("JPEG") && !fileExtension.equals("PNG")&&!fileExtension.equals("JPG")) {
			throw new FileUploadException();
		}
		
		
		//File.separator it provide separator depending on system like(/,\,..... )
		String filePath = path+File.separator+newFileName;
		
		
		//Create folder if not created in the provided path
//		File f = new File(path);
//		if(!f.exists()) {
//			f.mkdir();
//		}
		
		//File copy to the created folder ( resource, target)
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return newFileName;
	}
	
	
	public String saveSignature( String path, MultipartFile file,String refNo) throws IOException,FileUploadException{
	
		//File name
		String name =file.getOriginalFilename();
		String newFileName = "Signature"+refNo+name.substring(name.lastIndexOf("."));
		//check file type
				String fileExtension = name.substring(name.lastIndexOf(".")+1).toUpperCase();
				if (!fileExtension.equals("JPEG") && !fileExtension.equals("PNG")&&!fileExtension.equals("JPG")) {
					throw new FileUploadException();
				}
				
		
		//File.separator it provide separator depending on system like(/,\,..... )
		String filePath = path+File.separator+newFileName;
		
		
		//File copy to the created folder ( resource, target)
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return newFileName;
	
	}
	
	
}
