package com.metis.book.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {
	public static Path saveFile(String uploadDir,MultipartFile file) throws IOException {
		Path fileNameAndPath = Paths.get(uploadDir, file.getOriginalFilename());
		Files.write(fileNameAndPath, file.getBytes());
		return fileNameAndPath;
    }
	public static Path saveUserImage(MultipartFile file, Long userId) throws IOException {
		Path fileNameAndPath = Paths.get(AppConstant.UPLOAD_USER_DIRECTORY, userId.toString()+".png");
		Files.write(fileNameAndPath, file.getBytes());
		return fileNameAndPath;
	}
	public static Path saveBookImage(MultipartFile file, Long bookId) throws IOException {
		Path fileNameAndPath = Paths.get(AppConstant.UPLOAD_BOOK_DIRECTORY, bookId.toString()+".png");
		Files.write(fileNameAndPath, file.getBytes());
		return fileNameAndPath;
	}
}
