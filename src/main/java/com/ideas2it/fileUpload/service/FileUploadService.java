package com.ideas2it.fileUpload.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;


public class FileUploadService {

	private static String UPLOAD_FOLDER = "/home/ubuntu/Desktop/fileUpload/src/main/resources/";

	public String fileUpload(MultipartFile file) {

		if (file.isEmpty()) {
			return "Please select a file and try again";
		}

		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "file uploaded successfully";
	}

}
