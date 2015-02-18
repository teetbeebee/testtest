package com.tbb.ups;

import java.util.Date;
import java.util.HashMap;

public class AnalogData {
	private int dataFlag;		// 0 表示正常；1 表示空数据 2 表示异常错误
	private float inVoltage;
    private float outVoltage;
    private float outCurrent;
    private float batVoltage;
    private float outFrequency;
    private float inFrequency;
    private float load;
    private float batCapacity;
    private float interTemp;
    private Date lastUpdate;
    private long timeout;	
    
    public AnalogData()
    {
        dataFlag = 2;			// 默认状态异常
        inVoltage = 0;
        outVoltage = 0;
        outCurrent = 0;
        batVoltage = 0;
        outFrequency = 0;
        inFrequency = 0;
        load = 0;
        batCapacity = 0;
        interTemp = 0;
        timeout = -1;		// -1表示不做判定， 默认的超时毫秒数,时间太短浪费系统资源
        
        lastUpdate = new Date();
    }

    private static class AnalogDataHolder {
		static AnalogData instance = new AnalogData();
	}
	
	// 定义全局静态接口,缓存提交的UPS信息量
	public static AnalogData getInstance() throws Exception {
		return AnalogDataHolder.instance;
	}
	
	// 提取所有的模拟量量化数据
	public HashMap<String,String> getUpsAnalogMap()
	{
		HashMap<String, String> mapUpsAnalog;		// UPS模拟量状态,通过Map将数据量返回页面
		
		mapUpsAnalog = new HashMap<String, String>();
		if (batCapacity >0 && batCapacity <20) {
			mapUpsAnalog.put("BATLEV", "1");
		} else if (batCapacity >=20 && batCapacity <40) {
			mapUpsAnalog.put("BATLEV", "2");
		} else if (batCapacity >=40 && batCapacity <60) {
			mapUpsAnalog.put("BATLEV", "3");
		} else if (batCapacity >=60 && batCapacity < 80) {
			mapUpsAnalog.put("BATLEV", "4");
		} else if (batCapacity>=80 && batCapacity <= 100) {
			mapUpsAnalog.put("BATLEV", "5");
		} else {
			mapUpsAnalog.put("BATLEV", "0");
		}
		if (inVoltage > 0 && dataFlag == 0) {
			mapUpsAnalog.put("POWER", "0");
		} else if (inVoltage == 0 && dataFlag == 0) {
			mapUpsAnalog.put("POWER", "1");
		} else {
			//mapUpsAnalog.put("POWER", "0");
			mapUpsAnalog.put("POWER", "2");
		}
		mapUpsAnalog.put("DATAFLAG", String.valueOf(dataFlag));
		mapUpsAnalog.put("INVOLTAGE", String.valueOf(inVoltage));
		mapUpsAnalog.put("OUTVOLTAGE", String.valueOf(outVoltage));
		mapUpsAnalog.put("OUTCURRENT", String.valueOf(outCurrent));
		mapUpsAnalog.put("BATVOLTAGE", String.valueOf(batVoltage));
		mapUpsAnalog.put("OUTFREQUENCY", String.valueOf(outFrequency));
		mapUpsAnalog.put("INFREQUENCY", String.valueOf(inFrequency));
		mapUpsAnalog.put("LOAD", String.valueOf(load));
		mapUpsAnalog.put("BATCAPACITY", String.valueOf(batCapacity));
		mapUpsAnalog.put("INTERTEMP", String.valueOf(interTemp));	
		
		return mapUpsAnalog;
	}
	
	public float getBatCapacity() {
		return batCapacity;
	}

	public void setBatCapacity(float batCapacity) {
		this.batCapacity = batCapacity;
	}

	public float getBatVoltage() {
		return batVoltage;
	}

	public void setBatVoltage(float batVoltage) {
		this.batVoltage = batVoltage;
	}

	public int getDataFlag() {
		if (timeout == -1) {
			// 不做超时检测
			return dataFlag;
		} else {			
			// 计算与上次通讯之间的时间间隔毫秒数
			long timedif = (new Date()).getTime() - lastUpdate.getTime();		
			// System.out.print("超时时间<Seconds>:"+ String.valueOf(timedif));	
			if (timedif > timeout) {
				//	判断超时则认为断线
				return 2;
			} else {
				return dataFlag;
			}
		}		
	}

	public void setDataFlag(int dataFlag) {
		this.dataFlag = dataFlag;
	}

	public float getInFrequency() {
		return inFrequency;
	}

	public void setInFrequency(float inFrequency) {
		this.inFrequency = inFrequency;
	}

	public float getInterTemp() {
		return interTemp;
	}

	public void setInterTemp(float interTemp) {
		this.interTemp = interTemp;
	}

	public float getInVoltage() {
		return inVoltage;
	}

	public void setInVoltage(float inVoltage) {
		this.inVoltage = inVoltage;
	}

	public float getLoad() {
		return load;
	}

	public void setLoad(float load) {
		this.load = load;
	}

	public float getOutCurrent() {
		return outCurrent;
	}

	public void setOutCurrent(float outCurrent) {
		this.outCurrent = outCurrent;
	}

	public float getOutFrequency() {
		return outFrequency;
	}

	public void setOutFrequency(float outFrequency) {
		this.outFrequency = outFrequency;
	}

	public float getOutVoltage() {
		return outVoltage;
	}

	public void setOutVoltage(float outVoltage) {
		this.outVoltage = outVoltage;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
}
