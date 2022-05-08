package com._520it.wms.util;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.UUID;

public class FileUploadUtil {
	public static final String suffix = "_small";

	public static String uploadFile(File file, String fileName)
			throws Exception {
		//随机生成的uuid
		String uuid = UUID.randomUUID().toString();
		//获取上传文件的类型，如.jpg
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		//拼接成随机生成的文件名
		fileName = uuid + fileType;
		String path = ServletActionContext.getServletContext().getRealPath(
				"/upload");
		//在指定路径/upload中创建一个以fileName为文件名文件
		File targetFile = new File(path, fileName);
		//将上传的文件拷贝为这个文件
		FileUtils.copyFile(file, targetFile);

		// 缩略图是在文件名后面加上_small
		String smallImg = uuid + suffix + fileType;
		//在/upload路径中创建一个以smallImg为文件名的文件
		File smallTargetFile = new File(path, smallImg);
		// 使用缩略图工具将图片targetFile压缩为原来的40%，生成缩略图smallTargetFile
		Thumbnails.of(targetFile).scale(0.4f).toFile(smallTargetFile);
		return "/upload/" + fileName;
	}

	/**
	 * 删除图片 根据文件完成路径删除
	 * @param pic
	 */
	public static void deleteFile(String pic) {
		String path=ServletActionContext.getServletContext().getRealPath("/")+pic;
		File file=new File(path);
		//删除原图片
		if(file.exists()) file.delete();
		
		path=ServletActionContext.getServletContext().getRealPath("/")+ pic.substring(0,pic.indexOf("."))+FileUploadUtil.suffix+pic.substring(pic.indexOf("."));
		file=new File(path);
		//删除压缩后的图片
		if(file.exists()) file.delete();
	}
}
