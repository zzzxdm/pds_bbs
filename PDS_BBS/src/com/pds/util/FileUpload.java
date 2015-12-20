package com.pds.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

public class FileUpload {

	/**
	 * 
	 *@Author 朱振振
	 *Name:
	 *Function:文件存在upload目录下
	 *
	 * @param file
	 * @param source
	 * @return
	 * @throws Exception
	 */
	public static String fileUpload(File file, String source) throws Exception {
		if (file == null)
			return "";
		String realpath = ServletActionContext.getServletContext().getRealPath("/upload");// 上传目录
		String filename = UUID.randomUUID().toString()+ source.substring(source.lastIndexOf("."));// 文件扩展名
		String df = realpath + File.separator + filename;
		int b = 0;
		File newfile = new File(df);
		FileInputStream is = new FileInputStream(file);
		FileOutputStream os = new FileOutputStream(newfile);
		try {
			while ((b = is.read()) != -1) {
				os.write(b);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			is.close();
			os.close();
		}
		return newfile.getPath();
	}
	//指定文件存放的路徑
	/**
	 * 
	 *@Author 朱振振
	 *Name:
	 *Function:文件存在upload目录下的自己指定的目录下
	 *
	 * @param file
	 * @param source
	 * @param savePath
	 * @return
	 * @throws Exception
	 */
	public static String fileUpload(File file, String source,String savePath) throws Exception {
		if (file == null)
			return "";
		String basePath = ServletActionContext.getServletContext().getRealPath("/upload");// 上传根目录
		File folder = new File(basePath+"/"+savePath);
		if(!folder.exists()){
			folder.mkdirs();
		}
		String realpath = folder.getPath();// 最终上传目录
		String filename = UUID.randomUUID().toString()+ source.substring(source.lastIndexOf("."));// 文件扩展名
		String df = realpath + File.separator + filename;
		int b = 0;
		File newfile = new File(df);
		FileInputStream is = new FileInputStream(file);
		FileOutputStream os = new FileOutputStream(newfile);
		try {
			while ((b = is.read()) != -1) {
				os.write(b);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			is.close();
			os.close();
		}
		return newfile.getPath();
	}
	/**
	 * 
	 *@Author 朱振振
	 *Name:
	 *Function:上传图片的方法  每个用户的头像存在自己用户名对应文件夹中
	 *
	 * @param file
	 * @param source
	 * @param savePath
	 * @return
	 * @throws Exception
	 */
	public static String imgFileUpload(File file, String source,String savePath) throws Exception {
		if (file == null)
			return "";
		String basePath = ServletActionContext.getServletContext().getRealPath("/upload");// 上传根目录
		File folder = new File(basePath+"/"+savePath+"/image");
		if(!folder.exists()){
			folder.mkdirs();
		}
		String realpath = folder.getPath();// 最终上传目录
		String filename = UUID.randomUUID().toString()+ source.substring(source.lastIndexOf("."));// 文件扩展名
		String df = realpath + File.separator + filename;
		int b = 0;
		File newfile = new File(df);
		FileInputStream is = new FileInputStream(file);
		FileOutputStream os = new FileOutputStream(newfile);
		try {
			while ((b = is.read()) != -1) {
				os.write(b);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			is.close();
			os.close();
		}
		return newfile.getPath();
	}
	/**
	 * 
	 *@Author 朱振振
	 *Name:
	 *Function:上传其他资料的方法
	 *
	 * @param file
	 * @param source
	 * @param savePath
	 * @return
	 * @throws Exception
	 */
	public static String dataFileUpload(File file, String source,String savePath) throws Exception {
		if (file == null)
			return "";
		String basePath = ServletActionContext.getServletContext().getRealPath("/upload");// 上传根目录
		String str = new Date().toLocaleString().substring(0, 10);
		File folder = new File(basePath+"/"+savePath+"/data/"+str);
		if(!folder.exists()){
			folder.mkdirs();
		}
		String realpath = folder.getPath();// 最终上传目录
		String filename = UUID.randomUUID().toString()+ source.substring(source.lastIndexOf("."));// 文件扩展名
		String df = realpath + File.separator + filename;
		int b = 0;
		File newfile = new File(df);
		FileInputStream is = new FileInputStream(file);
		FileOutputStream os = new FileOutputStream(newfile);
		try {
			while ((b = is.read()) != -1) {
				os.write(b);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			is.close();
			os.close();
		}
		return newfile.getPath();
	}
}
