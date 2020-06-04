package com.ideas2it.fileUpload.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.fileUpload.service.FileUploadService;

@RestController
@RequestMapping("/fileUpload")
public class FileUploadController {

    
	private static String UPLOAD_FOLDER = "/home/ubuntu/Desktop/fileUpload/src/main/resources/";

	@RequestMapping("/upload")
	public ModelAndView showUpload() {
		return new ModelAndView("upload");
	}

	@PostMapping("/upload")
	public ModelAndView fileUpload(@RequestParam("file") MultipartFile file, 
	    @RequestParam("noOfThreats") int threats, @RequestParam("noOfPartions") int partion,
	    RedirectAttributes redirectAttributes) {
        FileUploadService fileUploadService = new FileUploadService();
        String message = fileUploadService.fileUpload(file);
		
		System.out.println("threats" + threats);
        System.out.println("\n partion" + partion);
		return new ModelAndView("status", "message", message);
	}

}
