package com.identity.services.dtos;

public class ErrorInfo {
	
	/**
	 * 
	 */
	private String errorCode;
	/**
	 * 
	 */
	private String errorTitle;
	/**
	 * 
	 */
	private String errorDescription;
	/**
	 * 
	 */
	private String errorKeyTitle;
	/**
	 * 
	 */
	private String errorKeyDescription;
	/**
	 * 
	 */
	private String debugMessage;
	/**
	 * 
	 */
	private String debugTrace;
	
	// Constructores de la clase
	
	/**
	 * Constructor vacío de la clase
	 */
	public ErrorInfo() {
		super();
	}
	/**
	 * Constructor de la clase con parámetros
	 * @param errorCode
	 * @param errorTitle
	 * @param errorDescription
	 * @param errorKeyTitle
	 * @param errorKeyDescription
	 * @param debugMessage
	 * @param debugTrace
	 */
	public ErrorInfo(String errorCode, String errorTitle,
			String errorDescription, String errorKeyTitle,
			String errorKeyDescription, String debugMessage, String debugTrace) {
		super();
		this.errorCode = errorCode;
		this.errorTitle = errorTitle;
		this.errorDescription = errorDescription;
		this.errorKeyTitle = errorKeyTitle;
		this.errorKeyDescription = errorKeyDescription;
		this.debugMessage = debugMessage;
		this.debugTrace = debugTrace;
	}
	
	// Métodos de obtención y establecimiento
	
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return the errorTitle
	 */
	public String getErrorTitle() {
		return errorTitle;
	}
	/**
	 * @param errorTitle the errorTitle to set
	 */
	public void setErrorTitle(String errorTitle) {
		this.errorTitle = errorTitle;
	}
	/**
	 * @return the errorDescription
	 */
	public String getErrorDescription() {
		return errorDescription;
	}
	/**
	 * @param errorDescription the errorDescription to set
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	/**
	 * @return the errorKeyTitle
	 */
	public String getErrorKeyTitle() {
		return errorKeyTitle;
	}
	/**
	 * @param errorKeyTitle the errorKeyTitle to set
	 */
	public void setErrorKeyTitle(String errorKeyTitle) {
		this.errorKeyTitle = errorKeyTitle;
	}
	/**
	 * @return the errorKeyDescription
	 */
	public String getErrorKeyDescription() {
		return errorKeyDescription;
	}
	/**
	 * @param errorKeyDescription the errorKeyDescription to set
	 */
	public void setErrorKeyDescription(String errorKeyDescription) {
		this.errorKeyDescription = errorKeyDescription;
	}
	/**
	 * @return the debugMessage
	 */
	public String getDebugMessage() {
		return debugMessage;
	}
	/**
	 * @param debugMessage the debugMessage to set
	 */
	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}
	/**
	 * @return the debugTrace
	 */
	public String getDebugTrace() {
		return debugTrace;
	}
	/**
	 * @param debugTrace the debugTrace to set
	 */
	public void setDebugTrace(String debugTrace) {
		this.debugTrace = debugTrace;
	}
	@Override
	public String toString() {
		return "ErrorInfo [errorCode=" + errorCode + ", errorTitle="
				+ errorTitle + ", errorDescription=" + errorDescription
				+ ", errorKeyTitle=" + errorKeyTitle + ", errorKeyDescription="
				+ errorKeyDescription + ", debugMessage=" + debugMessage
				+ ", debugTrace=" + debugTrace + "]";
	}
}
