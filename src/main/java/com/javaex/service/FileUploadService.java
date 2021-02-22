package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.BlogVo;

@Service
public class FileUploadService {

	public void fileUpload(BlogVo bVo, MultipartFile file) {

		System.out.println("[Blog Service]: fileUpload(MultipartFile file) 연결");

		/* db에 저장할 정보 수집 */

		/* 원본파일 */
		String orgName = file.getOriginalFilename();
		System.out.println("[FileUpload Service]: orgName = " + orgName);

		/* 확장자 */
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("[FileUpload Service]: exName = " + exName);

		/* 서버에 저장할 이름 */
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("[FileUpload Service]: saveName = " + saveName);
		bVo.setLogoFile(saveName);

		/* 서버저장경로 */
		String saveDir = "c:\\javaStudy\\upload";
		System.out.println("[FileUpload Service]: saveDir  = " + saveDir);

		String filePath = saveDir + "\\" + saveName;
		System.out.println("[File Service]: filePath = " + filePath);

		// 서버에 저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(out);

			bos.write(fileData);
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
