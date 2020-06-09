package com.ideas2it.fileUpload.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ideas2it.fileUpload.repository.RedisRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;


public class FileUploadService {

	@Value("${UPLOAD_FOLDER}")
	private static String UPLOAD_FOLDER;

	@Value("${OUTPUT_FOLDER}")
	private static String OUTPUT_FOLDER;

	@Autowired
	private RedisRepository redisRepository;


	public String fileUpload(MultipartFile file, int noOfPartitions) {

		if (file.isEmpty()) {
			return "Please select a file and try again";
		}

		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			redisRepository.saveContext("first_try", "test_ok");
			startAutomation(file, noOfPartitions);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "file uploaded successfully";
	}

	public void startAutomation(MultipartFile file , int noOfPartitions) {
		try {
			Workbook inputWorkbook = new XSSFWorkbook(file.getInputStream());
			Sheet inputWorkbookSheet = inputWorkbook.getSheetAt(0);
			int totalRowCount = inputWorkbookSheet.getPhysicalNumberOfRows();
			int partitionValue = totalRowCount / noOfPartitions;
			Workbook outputWorkbook = null;
			Sheet outputWorksheet = null;

			for(int i = 1;i <= inputWorkbookSheet.getPhysicalNumberOfRows(); i++) {
				if (i == 1) {
					outputWorkbook = new XSSFWorkbook();
					outputWorksheet = outputWorkbook.createSheet("output");
				} else {
					outputWorkbook = getExcelForOutput(outputWorkbook, noOfPartitions);
					if (null != outputWorkbook) {
						outputWorksheet = outputWorkbook.getSheetAt(0);
					}
				}
				Row row = inputWorkbookSheet.getRow(i);
				Row newRow = outputWorksheet.createRow(i);
				for (int j = 0;j <= row.getPhysicalNumberOfCells(); j++) {
					Cell newCell = newRow.createCell(j);
					newCell.setCellValue(row.getCell(j).getStringCellValue());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Workbook getExcelForOutput(Workbook workbook, int noOfPartitions) {
		try {
			int outputFileCount = 1;
			if (noOfPartitions == workbook.getSheetAt(0).getPhysicalNumberOfRows()) {
				FileOutputStream fileOut = new FileOutputStream(OUTPUT_FOLDER + "output_"+ outputFileCount +".xlsx");
				workbook.write(fileOut);
				fileOut.close();
				workbook.close();
				outputFileCount++;
				Workbook newWorkbook = new XSSFWorkbook();
				Sheet sheet = newWorkbook.createSheet("output");
				return newWorkbook;
			} else {
				return workbook;
			}
		} catch (IOException exception) {
			exception.printStackTrace();
			return null;
		}
	}

}
