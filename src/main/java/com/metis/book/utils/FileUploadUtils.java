package com.metis.book.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {
	public static void saveFile(MultipartFile file) throws IOException {
		StringBuilder fileNames = new StringBuilder();
		Path fileNameAndPath = Paths.get(AppConstant.UPLOAD_DIRECTORY, file.getOriginalFilename());
		fileNames.append(file.getOriginalFilename());
		Files.write(fileNameAndPath, file.getBytes());
    }
}
