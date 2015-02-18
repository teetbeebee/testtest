package com.tbb.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.newbee.tmf.core.BaseException;

public class FileHelper {
	/**
	 * 文件上传方法
	 * <p>
	 * 请求form中的实体内容的类型必须是multipart/form-data，请求到该方法的form表单中必须包括两个提交项，
	 * 分别是名字为savePath和fileName的两个input输入框，其中fileName输入框的类型为file；<br />
	 * 例如： <form name="testForm" enctype="multipart/form-data" method="post"
	 * action="upload.jsp"> <input type="file" name="fileName" /> <input
	 * type="text" name="savePath" /> </form>
	 * </p>
	 * 
	 * @param request
	 *            <code>HttpServletRequest</code>对象
	 * @return boolean 返回上传成功与否
	 */
	public static boolean upload(HttpServletRequest request) {
		String savePath = "";
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096); // 设置缓存大小

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		upload.setSizeMax(-1);

		List fileItems;
		FileItem fileItem = null;
		String fileName = "";
		try {
			fileItems = upload.parseRequest(request);
			Iterator iter = fileItems.iterator();
			while (iter.hasNext()) {
				FileItem fi = (FileItem) iter.next();
				String fieldName = fi.getFieldName();
				if (fieldName != null && "savePath".equals(fieldName.trim())) {
					// 保存路径
					savePath = fi.getString();
					continue;
				}
				if (fieldName != null && "fileName".equals(fieldName.trim())) {
					// 一次只上传一个文件
					fileItem = fi;
					continue;
				}
			}

			if (fileItem == null) {
				throw new BaseException("文件上传失败：未获取到上传文件信息！");
			}

			fileName = fileItem.getName();
			fileName = fileName
					.substring(fileName.lastIndexOf(File.separator) + 1); // 去掉路径信息后的文件名

			// 判断文件夹是否存在，如果不存在，则创建
			if (!new File(savePath).isDirectory()) {
				new File(savePath).mkdirs();
			}

			// 写入上传文件
			File file = new File(savePath + File.separator + fileName);
			fileItem.write(file);
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

		return true;
	}

	/**
	 * 文件上传方法
	 * 
	 * @param request
	 *            <code>HttpServletRequest</code>对象
	 * @param savePath
	 *            <code>String</code>上传文件存放路径
	 * @param sizeMax
	 *            上传文件最大限制
	 * @param fileInputName
	 *            上传文件在网页form中的输入框名字，如：<input type="file" name="fileName"
	 *            />的名字为fileName，fileName为我们这里的参数值
	 * @return boolean 返回上传成功与否
	 */
	public static boolean upload(HttpServletRequest request, String savePath,
			long sizeMax, String fileInputName) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096); // 设置缓存大小

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		upload.setSizeMax(sizeMax);

		List fileItems;
		FileItem fileItem = null;
		String fileName = "";
		try {
			fileItems = upload.parseRequest(request);
			Iterator iter = fileItems.iterator();
			while (iter.hasNext()) {
				FileItem fi = (FileItem) iter.next();
				String fieldName = fi.getFieldName();
				if (fieldName != null && fileInputName.equals(fieldName.trim())) {
					// 一次只上传一个文件
					fileItem = fi;
					break;
				}
			}

			if (fileItem == null) {
				throw new BaseException("文件上传失败：未获取到上传文件信息！");
			}

			fileName = fileItem.getName();
			fileName = fileName
					.substring(fileName.lastIndexOf(File.separator) + 1); // 去掉路径信息后的文件名

			// 判断文件夹是否存在，如果不存在，则创建
			if (!new File(savePath).isDirectory()) {
				new File(savePath).mkdirs();
			}

			// 写入上传文件
			File file = new File(savePath + File.separator + fileName);
			fileItem.write(file);
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

		return true;
	}

	/**
	 * 文件上传方法，调用该方法，上传文件大小将不受限制
	 * 
	 * @param request
	 *            <code>HttpServletRequest</code>对象
	 * @param savePath
	 *            <code>String</code>上传文件存放路径
	 * @param fileInputName
	 *            上传文件在网页form中的输入框名字，如：<input type="file" name="fileName"
	 *            />的名字为fileName，fileName为我们这里的参数值
	 * @return boolean 返回上传成功与否
	 */
	public static boolean upload(HttpServletRequest request, String savePath,
			String fileInputName) {
		long sizeMax = -1; // 上传文件最大不受限制
		return upload(request, savePath, sizeMax, fileInputName);
	}

	/**
	 * 文件下载
	 * 
	 * @param filePath
	 *            <code>String</code>要下载文件名及存放的完整路径
	 * @param response
	 *            <code>HttpServletResponse</code>对象
	 * @param isOnLine
	 *            <code>boolean</code>文件下载方式：true为在线打开，false为下载
	 * @throws Exception
	 */
	public static void downLoad(HttpServletResponse response, String filePath,
			boolean isOnLine) throws Exception {
		if (filePath == null || "".equals(filePath.trim())) {
			throw new BaseException("文件下载失败：下载文件路径不存在！");
		}
		File f = new File(filePath);
		if (!f.exists()) {
			throw new BaseException("文件下载失败：下载文件不存在！");
		}

		String fileName = f.getName();
		fileName = new String(fileName.getBytes("UTF-8"), "UTF-8");
		fileName = new String(fileName.getBytes("GBK"), "ISO8859-1"); // 保证下载文件名汉字正常显示
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;

		response.reset(); // 非常重要
		if (isOnLine) { // 在线打开方式
			URL u = new URL("file:///" + filePath);
			response.setContentType(u.openConnection().getContentType());
			response.setHeader("Content-Disposition", "inline; filename="
					+ fileName);
		} else { // 纯下载方式
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileName);
		}
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0)
			out.write(buf, 0, len);
		br.close();
		out.close();
	}
}
