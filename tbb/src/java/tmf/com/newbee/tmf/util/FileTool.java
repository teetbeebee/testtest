package com.newbee.tmf.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import com.newbee.tmf.core.BaseException;

public class FileTool {
	// 上传文件的最大值，单位为M
	public static final int FILE_MAX_SIZE = 25;

	// 各种导入模板的文件路径
	public static final String TEMPLATE_PATH = "";

	protected static Log log = LogFactory.getLog(FileTool.class);

	public FileTool() {

	}

	public static void saveToFile(InputStream is, String filename)
			throws IOException {
		// 添加了检查并创建目录的操作
		File file = new File(filename);
		File path = file.getParentFile();
		if (!path.exists()) {
			path.mkdirs();
		}

		BufferedOutputStream os = new BufferedOutputStream(
				new FileOutputStream(filename));
		byte[] buf = new byte[8192];
		int len;
		while ((len = is.read(buf)) != -1) {
			os.write(buf, 0, len);
		}
		os.flush();
		os.close();
	}

	/** ***** 通用多文件上传模板 ***** */
	public static FormFile[] uploadFile(int fileMaxSize, ActionForm form)
			throws Exception {
		// 从页面获取file对象
		DynaActionForm theForm = (DynaActionForm) form;
		Hashtable files_hashTable = theForm.getMultipartRequestHandler()
				.getFileElements();
		Collection files = files_hashTable.values();
		Iterator iter = files.iterator();
		while (iter.hasNext()) {
			// 限制上传文件的大小，fileMaxSize的单位为M
			FormFile formFile = (FormFile) iter.next();
			int size = fileMaxSize * 1024 * 1024;
			if (formFile.getFileSize() > size) {
				throw new BaseException("你上传的文件" + formFile.getFileName()
						+ "已超过最大值" + fileMaxSize + "M");
			}
		}
		return (FormFile[]) files.toArray(new FormFile[0]);
	}

	/** ***** 通用单文件上传模板 ***** */
	public static FormFile uploadFile(int fileMaxSize, String file_id,
			ActionForm form) throws Exception {
		// 从页面获取file对象
		DynaActionForm theForm = (DynaActionForm) form;
		Hashtable files = theForm.getMultipartRequestHandler()
				.getFileElements();
		FormFile formFile = (FormFile) files.get(file_id);
		// 限制上传文件的大小，fileMaxSize的单位为M
		int size = fileMaxSize * 1024 * 1024;
		if (formFile.getFileSize() > size) {
			throw new BaseException("你上传的文件已超过最大值" + fileMaxSize + "M");
		}
		return formFile;
	}

	/** ***** 通用文件下载模板 ***** */
	public static void downloadFile(String filePath, String fileName,
			String showName, HttpServletResponse response) throws Exception {
		String errorMessage = null;
		// 检查参数
		if (filePath == null || filePath == "") {
			errorMessage = "文件路径为空";
		}
		if (fileName == null || fileName == "") {
			errorMessage = "文件名为空";
		}
		if (showName == null || showName == "") {
			showName = fileName;
		}
		if (response == null) {
			errorMessage = "response为空";
		}
		if (errorMessage != null) {
			throw new BaseException(errorMessage);
		}
		// 读取文件，获得输入流
		String file = filePath + fileName;
		InputStream is = new BufferedInputStream(new FileInputStream(file));
		// 设置http头
		String encodeShowName = new String(showName.getBytes("GBK"),
				"ISO8859-1");
		response.setContentType("application/octet-stream");
		// response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ encodeShowName);
		// 发送输出流到Response
		OutputStream os = new BufferedOutputStream(response.getOutputStream());
		byte[] buf = new byte[8192];
		int len;
		while ((len = is.read(buf)) != -1) {
			os.write(buf, 0, len);
		}
		os.flush();
		is.close();
		os.close();
		
		is = null;
		os = null;
	}

	/** ***** 通用文件下载模板 ***** */
	public static void downloadFile(InputStream is, String showName,
			HttpServletResponse response) throws Exception {
		String errorMessage = null;
		// 检查参数
		if (is == null) {
			errorMessage = "输入流为空";
		}
		if (showName == null || showName == "") {
			errorMessage = "显示到客户端的文件名为空";
		}
		if (response == null) {
			errorMessage = "response为空";
		}
		if (errorMessage != null) {
			throw new BaseException(errorMessage);
		}
		// 设置http头InputStream
		String encodeShowName = new String(showName.getBytes("GBK"),
				"ISO8859-1");
		response.setContentType("application/octet-stream");
		// response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ encodeShowName);
		// 发送输出流到Response
		OutputStream os = new BufferedOutputStream(response.getOutputStream());
		byte[] buf = new byte[8192];
		int len;
		while ((len = is.read(buf)) != -1) {
			os.write(buf, 0, len);
		}
		os.flush();
		is.close();
		os.close();
	}

	/* 读取Excel文件中工作表sheet的第line行的colKeys列的数据 */
	public static Map readSheetLine(Sheet sheet, int line, int column,
			String[] titleKeys) throws Exception {
		Map lineMap = new HashMap();
		Cell firstColumn = sheet.getCell(0, line);
		if (firstColumn == null || firstColumn.getContents() == null
				|| firstColumn.getContents().equals("")) {
			return null;
		}
		for (int i = 0; i < column; i++) {
			Cell cell = sheet.getCell(i, line);
			CellType cellType = cell.getType();
			if (cellType.equals(CellType.LABEL)) {
				LabelCell lc = (LabelCell) cell;
				String cellValue = lc.getString();
				if (cellValue != null || !cellValue.equals("")) {
					cellValue = cellValue.trim();
				}
				lineMap.put(titleKeys[i], cellValue);
			} else if (cellType.equals(CellType.NUMBER)) {
				NumberCell nc = (NumberCell) cell;
				double cellValue = nc.getValue();
				lineMap.put(titleKeys[i], String.valueOf(cellValue));
			} else if (cellType.equals(CellType.DATE)) {
				DateCell dc = (DateCell) cell;
				java.util.Date cellValue = dc.getDate();
				lineMap.put(titleKeys[i], DateUtils.formatDate(cellValue,
						"yyyy-MM-dd"));
			} else {
				// 特殊情况的处理
				String cellValue = cell.getContents();
				if (cellValue != null || !cellValue.equals("")) {
					cellValue = cellValue.trim();
				}
				lineMap.put(titleKeys[i], cellValue);
			}
		}
		return lineMap;
	}

	/** * 解析Excel文件流fileStream,返回一个List,List里的每一项用一个Map存放每一行数据 ** */
	public static List parseExcelFile(InputStream fileStream, int sheetIndex,
			int startIndex, int colunmCount, String[] colunmKeys)
			throws Exception {
		if (fileStream == null) {
			throw new BaseException("文件为空！");
		}
		// 解析excel文件
		Workbook wb = Workbook.getWorkbook(fileStream);
		Sheet sheet = wb.getSheet(sheetIndex);
		int rows = sheet.getRows();
		List list = new ArrayList();

		try {
			// 遍历excel文件的行，依次读出每一行数据
			for (int i = startIndex; i < rows; i++) {
				if ((i - startIndex + 1) > 5000) {
					throw new BaseException("数据行已超过5000行，请分多个文件依次导入！");
				}
				Map lineMap = new HashMap();
				// 读取excel文件的一行，封装到一个Map里
				lineMap = readSheetLine(sheet, i, colunmCount, colunmKeys);
				if (lineMap == null) {
					break;
				}
				list.add(lineMap);
			}
		} catch (Exception ex) {
			throw new BaseException(ex.getMessage());
		} finally {
			wb.close();
			fileStream.close();
		}
		return list;
	}

	/**
	 * 拷贝一个文件到另一个目录
	 * 
	 * @throws BaseException
	 */
	public static void copyFile(String from, String to) throws BaseException {

		File fromFile, toFile;
		try {

			fromFile = new File(from);
			toFile = new File(to);
			FileInputStream fis = null;
			FileOutputStream fos = null;

			toFile.createNewFile();
			fis = new FileInputStream(fromFile);
			fos = new FileOutputStream(toFile);
			int bytesRead;
			byte[] buf = new byte[4 * 1024]; // 4K buffer
			while ((bytesRead = fis.read(buf)) != -1) {
				fos.write(buf, 0, bytesRead);
			}
			fos.flush();
			fos.close();
			fis.close();
		} catch (IOException ex) {
			log.info("文件复制错误:" + ex.getMessage());
		} finally {
			fromFile = null;
			toFile = null;
		}
	}

	public static void copyUrlFile(String url, String to) {
		HttpURLConnection connection = null;

		File fromFile, toFile;

		try {

			toFile = new File(to);
			InputStream fis = null;
			FileOutputStream fos = null;

			toFile.createNewFile();
			connection = (HttpURLConnection) (new URL(url)).openConnection();
			fis = connection.getInputStream();
			fos = new FileOutputStream(toFile);

			int bytesRead;
			byte[] buf = new byte[4 * 1024]; // 4K buffer
			while ((bytesRead = fis.read(buf)) != -1) {
				fos.write(buf, 0, bytesRead);
			}
			fos.flush();
			fos.close();
			fis.close();

		} catch (Exception ex) {
			log.info("文件复制错误:" + ex.getMessage());
		} finally {
			fromFile = null;
			toFile = null;
			connection = null;
		}
	}
	
	/**
	 * 提供Zip文件/目录的方法。
	 * 
	 * @param inputFileName
	 *            输入文件名/目录
	 * @param OutputStream
	 *           输出流
	 */
	public static void zip(String inputFileName, OutputStream os)
			throws Exception {
		ZipOutputStream out = new ZipOutputStream(os);
		zip(out, new File(inputFileName), "");// 递归压缩方法
		System.out.println("zip done");
		out.close();
	}


	/**
	 * 提供Zip文件/目录的方法。
	 * 
	 * @param inputFileName
	 *            输入文件名/目录
	 * @param outputFileName
	 *            输出文件名
	 */
	public static void zip(String inputFileName, String outputFileName)
			throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				outputFileName));
		zip(out, new File(inputFileName), "");// 递归压缩方法
		System.out.println("zip done");
		out.close();
	}

	private static void zip(ZipOutputStream out, File file, String base)
			throws Exception {
		try {
			if (file.isDirectory()) {
				File[] subfiles = file.listFiles();
				if (System.getProperty("os.name").startsWith("Windows")) {
					if (base.length() != 0) {
						out.putNextEntry(new ZipEntry(base + "\\"));
						base += "\\";
					}
				} else {
					if (base.length() != 0) {
						out.putNextEntry(new ZipEntry(base + "/"));
						base += "/";
					}
				}
				for (int i = 0; i < subfiles.length; i++) {
					zip(out, subfiles[i], base + subfiles[i].getName());
				}
			} else {
				out.putNextEntry(new ZipEntry(base));
				FileInputStream in = new FileInputStream(file);
				int b;
				System.out.println(base);
				while ((b = in.read()) != -1) {
					out.write(b);
				}
				in.close();
			}
		} catch (Exception ex) {
			log.info("文件压错误:" + ex.getMessage());
		}
	}

}
