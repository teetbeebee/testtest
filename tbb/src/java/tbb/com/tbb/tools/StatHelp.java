package com.tbb.tools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;


import com.newbee.tmf.config.Config;

public class StatHelp {

	public final static String FILE_PATH = "/stat/FusionCharts/Data/";

	@SuppressWarnings("unused")
	public static String getPieData(List<StatData> statDataList,String caption,String subcaption) {
		String xml = "";
		xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\r\n";
		xml += "<chart caption=\""+caption+"\" subcaption=\""+subcaption+"\" palette=\"4\" decimals=\"0\" enableSmartLabels=\"1\" enableRotation=\"0\" bgColor=\"99CCFF,FFFFFF\" bgAlpha=\"40,100\" bgRatio=\"0,100\" bgAngle=\"360\" showBorder=\"1\" startingAngle=\"70\" BaseFontSize=\"12\">\r\n";

		for (StatData sd : statDataList) {
			xml += "<set label=\"" + sd.getLabel() + "\" value=\""
					+ sd.getValue() + "\" />\r\n";
		}
		xml += "</chart>";

		return xml;
	}
	
	@SuppressWarnings("unused")
	public static String getColumnData(List<StatData> statDataList,String caption,String subcaption,String xAxisName,String yAxisName) {
		String xml = "";
		xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\r\n";
		xml += "<chart palette=\"2\" caption=\""+caption+"\" subcaption=\""+subcaption+"\" xAxisName=\""+xAxisName+"\" yAxisName=\""+yAxisName+"\" showValues=\"0\" decimals=\"0\" formatNumberScale=\"0\" useRoundEdges=\"1\" BaseFontSize=\"12\">\r\n";
		for (StatData sd : statDataList) {
			xml += "<set label=\"" + sd.getLabel() + "\" value=\""
					+ sd.getValue() + "\" />\r\n";
		}
		xml += "</chart>";

		return xml;
	}
	
	@SuppressWarnings("unused")
	public static String getLineData(List<StatData> statDataList,String caption,String subcaption,String xAxisName,String yAxisName) {
		String xml = "";
		xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\r\n";
		xml += "<chart caption=\""+caption+"\" subcaption=\""+subcaption+"\" xAxisName=\""+xAxisName+"\" yAxisName=\""+yAxisName+"\" showValues=\"0\" decimals=\"0\" formatNumberScale=\"0\" BaseFontSize=\"12\">\r\n";
		for (StatData sd : statDataList) {
			xml += "<set label=\"" + sd.getLabel() + "\" value=\""
					+ sd.getValue() + "\" />\r\n";
		}
		
		xml += "<styles>";
		xml += "	<definition>";
		xml += "		<style name=\"CanvasAnim\" type=\"animation\" param=\"_xScale\" start=\"0\" duration=\"1\" />";
		xml += "	</definition>";
		xml += "	<application>";
		xml += "		 <apply toObject=\"Canvas\" styles=\"CanvasAnim\" />";
		xml += "	</application>";		
		xml += "</styles>";
		xml += "</chart>";

		return xml;
	}	

	public static void outputStatData(HttpServletRequest request,String xml) throws IOException
	{		
		ServletContext application = request.getSession(true)
		.getServletContext();						
		FileOutputStream fos = new FileOutputStream(Config.ROOT + FILE_PATH + application.getAttribute("STAT").toString() + ".xml");
		Writer out = new OutputStreamWriter(fos, "GBK");
		out.write(xml);
		out.close();
		fos.close();
	}
}
