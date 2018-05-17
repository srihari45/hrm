package gov.hrm.forms;

import java.io.Serializable;

public class BaseForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String formId;
	private String errorString;
	private String successString;
	private boolean disableFlg;

	/**
	 * This is a getter method for the form property formId
	 * 
	 * @return the String
	 */
	public String getFormId() {
		return formId;
	}

	/**
	 * This is a setter method for the form property formId
	 * 
	 * @param formId
	 */
	public void setFormId(String formId) {
		this.formId = formId;
	}

	/**
	 * This is a getter method for the form property errorString
	 * 
	 * @return the String
	 */
	public String getErrorString() {
		return errorString;
	}

	/**
	 * This is a setter method for the form property errorString
	 * 
	 * @param errorString
	 */
	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}

	public boolean isDisableFlg() {
		return disableFlg;
	}

	public void setDisableFlg(boolean disableFlg) {
		this.disableFlg = disableFlg;
	}

	public String getSuccessString() {
		return successString;
	}

	public void setSuccessString(String successString) {
		this.successString = successString;
	}

}
