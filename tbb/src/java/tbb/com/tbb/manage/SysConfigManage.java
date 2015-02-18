package com.tbb.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbb.sys.domain.SysConfig;
import com.tbb.sys.service.SysConfigService;

public class SysConfigManage
{
     private SysConfigService sds;
     private static SysConfigManage instance;
     private List<SysConfig> sysConfigList;
     private HashMap keyMap;
     @SuppressWarnings("unchecked")
	private  SysConfigManage()throws Exception
 	 {
    	 sds = SysConfigService.getInstance();
    	 Map params = new HashMap();
    	 params.put("orderBy", "sys_id,config_id");
    	 sysConfigList = sds.querySysConfigForList(params);
    	 keyMap = new HashMap();
    	 for(SysConfig sc: sysConfigList)
 		 {
    		 keyMap.put(sc.getSys_id()+";"+sc.getConfig_id(), sc.getConfig_value());
 		 }
 	 }
     
     //当系统配置发生更改时重载系统配置表
     @SuppressWarnings("unchecked")
	public void reloadSysConfig() throws Exception
     {
    	 sds = SysConfigService.getInstance();
    	 Map params = new HashMap();
    	 params.put("orderBy", "sys_id,config_id");
    	 sysConfigList = sds.querySysConfigForList(params);
    	 keyMap = new HashMap();
    	 for(SysConfig sc: sysConfigList)
 		 {
    		 keyMap.put(sc.getSys_id()+";"+sc.getConfig_id(), sc.getConfig_value());
 		 }
    }
     
     public static SysConfigManage getInstance() throws Exception
 	 {
 	   if (instance == null)
 	   {
 	     instance =  new SysConfigManage();
 	   }
 	   return instance;
     }
     
     public List getSysConfigList()
     {
    	 return sysConfigList;   	 
     }
     
     public String getConfigValue(String key)
     {
    	 if (keyMap.get(key)!=null)
    	 {
    		 return  (String)keyMap.get(key);	 
    	 }
    	 else
    	 {
    		 return key.split(";").length > 1 ? key.split(";")[1] : "";
    	 }
    	    	 
     }
     
     public SysConfig getSysConfig(String key)
     {
    	 for (SysConfig sc : sysConfigList)
    	 {
    		 if (sc.getSys_id().equalsIgnoreCase(key.split(";")[0]) && sc.getConfig_id().equalsIgnoreCase(key.split(";")[1]))
    		 {
    			 return sc;
    		 }
    	 }
    	 
    	 return null;
     }
     
}
