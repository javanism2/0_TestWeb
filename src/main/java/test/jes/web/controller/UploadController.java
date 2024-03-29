package test.jes.web.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class UploadController {
	
	@PostMapping("upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		System.out.println(file);
		
		try {
			
			 File dir = new File("/upload");
			 if(!dir.exists()) {
			      //Creating the directory
			      boolean bool = dir.mkdir();
			      if(bool){
			         System.out.println("Directory created successfully");
			         
			      }else{
			         System.out.println("Sorry couldn’t create specified directory");
			      }
			 }
			 
			 file.transferTo(new File("/upload/"+file.getOriginalFilename()));
			
			
		
			return "upload ok!!!";
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "upload fail!!!";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "upload fail!!!";
		}
	}
	
	

}









