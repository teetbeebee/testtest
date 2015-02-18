package com.tbb.tools;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class RemoteProcClass {

	@SuppressWarnings("unchecked")
	public static HashMap RemoteProc(String url) {

		List<HashMap> li = RemoteProcList(url);
		if (li != null && li.size() > 0) {
			return li.get(0);
		} else {
			return null;
		}
	}
	
	public static HashMap RemoteProc(String url, Map paramMap) {

		List<HashMap> li = RemoteProcList(url, paramMap);
		if (li != null && li.size() > 0) {
			return li.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<HashMap> RemoteProcList(String url) {
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) (new URL(url)).openConnection();
			InputStream in = connection.getInputStream();
			StringBuffer content = new StringBuffer();
			byte[] byteBuffer = new byte[4096];
			for (int n; (n = in.read(byteBuffer)) != -1;) {
				content.append(new String(byteBuffer, 0, n));
			}
			String remoteData = (content.toString()).trim();
			RemoteProcReturnValue rpc = RemoteProcHelp
					.getRemoteProcReturnValue(remoteData);
			if (rpc.isSuccess()) {
				return rpc.getValue();
			}
		} catch (Exception e) {
		} finally {
			connection = null;
		}
		return null;
	}
	
	public static List<HashMap> RemoteProcList(String url, Map paramMap) {
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) (new URL(url)).openConnection();
			
			if(paramMap != null)
			{
				Iterator it = paramMap.keySet().iterator();
				while(it.hasNext())
				{
					String key = (String)it.next();
					String value = (String)paramMap.get(key);
					connection.addRequestProperty(key, value);
				}
			}
			InputStream in = connection.getInputStream();
			StringBuffer content = new StringBuffer();
			byte[] byteBuffer = new byte[4096];
			for (int n; (n = in.read(byteBuffer)) != -1;) {
				content.append(new String(byteBuffer, 0, n));
			}
			String remoteData = (content.toString()).trim();
			RemoteProcReturnValue rpc = RemoteProcHelp
					.getRemoteProcReturnValue(remoteData);
			if (rpc.isSuccess()) {
				return rpc.getValue();
			}
		} catch (Exception e) {
		} finally {
			connection = null;
		}
		return null;
	}
	

}