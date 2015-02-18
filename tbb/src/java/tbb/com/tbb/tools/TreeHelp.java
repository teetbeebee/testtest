package com.tbb.tools;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.newbee.tmf.config.Config;
import com.newbee.tmf.util.ValueUtils;

public class TreeHelp {

	public static String getSubTree(String text, String value, String action,
			String src) {

		if (text == null) {
			text = "空";
			System.err.println(text + "|" + value + "|" + action + "|" + src);
		}
		if (value == null) {
			value = "";
			System.err.println(text + "|" + value + "|" + action + "|" + src);
		}
		if (action == null) {
			action = "javascript:void(0)";
		}
		if (src == null)
			src = "";

		String subtree = "	<tree text=\"" + ValueUtils.escape(text+"@@"+value)
				+ "\" action=\"" + ValueUtils.escape(action) + "\" ";
		if (src.length() > 0)
			subtree += "src=\""
					+ ValueUtils.escape(Config.CONTEXT + src
							+ "&timestamp=\"+new Date().getTime()") + "\"";
		subtree += " />\r\n";
		return subtree;
	}

	public static String outputTree(HttpServletResponse response, String subTree)
			throws Exception {
		String tree = "";
		tree = "<?xml version=\"1.0\" encoding=\"GBK\"?>";
		tree += "<tree>\r\n";

		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setCharacterEncoding("GBK");

		tree += subTree;
		tree += "</tree>";
		out.println((tree));
		out.close();

		return tree;
	}
}
