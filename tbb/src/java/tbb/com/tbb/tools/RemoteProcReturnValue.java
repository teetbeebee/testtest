package com.tbb.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 返回结果类
 */
public class RemoteProcReturnValue {

	// 是否执行正确
	private boolean isSuccess;

	// 错误代码
	private String errorCode = "";

	// 错误消息
	private String errorMessage = "";

	// 执行正确时的返回值
	private List<HashMap> value = new ArrayList<HashMap>();

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public List<HashMap> getValue() {
		return value;
	}

	public void setValue(List<HashMap> value) {
		this.value = value;
	}

}
