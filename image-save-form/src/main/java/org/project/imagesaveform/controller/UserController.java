package org.project.imagesaveform.controller;

import java.io.IOException;

import org.project.imagesaveform.model.User;
import org.project.imagesaveform.service.FileService;
import org.project.imagesaveform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@CrossOrigin("*")
@RequestMapping("/project")

public class UserController {

	@Value("${candidate.photo}")
	private String photoPath;
	@Value("${candidate.signature}")
	private String signaturePath;
	
	
	@Autowired
	private UserService userService;
	@Autowired
	private FileService fileService;
	
	@GetMapping("/")
	public String formPage(Model model) {
		User user = new User();  
	    model.addAttribute("user", user);
	    return "form"; // Return the Thymeleaf template for the form
	}
	
	
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String saveUser(Model model,
			@ModelAttribute("user") User user, 
			@RequestParam("photo1") MultipartFile photoFile,
			@RequestParam("signature1") MultipartFile signatureFile) throws IOException {	
		
		//generate random number
		int min = 1000;
		int max = 9999;
		int randomInt = min + (int)(Math.random() * ((max - min) + 1));
		
		
		//setting photo
		String photo = "";
		String signature="";
		try {
			photo = fileService.savePhoto(this.photoPath, photoFile,randomInt+"");
			signature = fileService.saveSignature(this.signaturePath, signatureFile,randomInt+"");
			//setting new names
			user.setPhoto(photo);
			user.setSignature(signature);
					
			
			user = userService.saveUser(user);
		}catch(Exception e) {
			model.addAttribute("errorMessage", "Data is not valid. Please check your inputs.");
			System.out.println(user);
			return "form";
		}
		return "home";
	}
}
