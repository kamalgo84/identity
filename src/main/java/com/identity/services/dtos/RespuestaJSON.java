package com.identity.services.dtos;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang.builder.ToStringBuilder;




public class RespuestaJSON implements Serializable{

	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Indica si ha tenido éxito o no la ejecución de un proceso.
	 */
	private boolean success;
	/**
	 * Objeto con los errores
	 */
	
	private ErrorInfo errorInfo;
	/**
	 * Objeto con la respuesta
	 */
	private Object data;
	
	//Constructores de la clase
	
	/**
	 * Constructor vacío de la clase
	 */
	public RespuestaJSON() {
		super();
	}
	/**
	 * Constructor de la clase con parámetros
	 * @param success
	 * @param errorInfo
	 * @param data
	 */
	public RespuestaJSON(boolean success, ErrorInfo errorInfo, Object data) {
		super();
		this.success = success;
		this.errorInfo = errorInfo;
		this.data = data;
	}
	
	// Métodos de obtención y establecimiento
	
	/**
	 * @return the success
	 */
	public boolean getSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the errorInfo
	 */
	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}
	/**
	 * @param errorInfo the errorInfo to set
	 */
	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	// Constantes
	
	/**
	 * Posible valor de la variable success
	 * OK: true
	 */
	public static final boolean SUCCESS_OK = true;
	/**
	 * Posible valor de la variable success
	 * KO: false
	 */
	public static final boolean SUCCESS_KO = false;

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("errorInfo", this.errorInfo)
			.append("data", this.data)
			.append("success", this.success).toString();
	}

	
}
