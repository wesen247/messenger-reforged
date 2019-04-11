package entity;

import java.io.Serializable;

/**
 * 
 * @author Ruben, Amir, André
 *
 */
public class Response implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4945600552280641508L;
	private String type;
	private Object responseObject;
	/**
	 * 
	 * @param type Type of response
	 * @param responseObject The response
	 */
	public Response(String type, Object responseObject) {
		this.type = type;
		this.responseObject = responseObject;
	}
	/**
	 * 
	 * @return type Type of response
	 */
	public String getType() {
		return type;
	}
	/**
	 * 
	 * @return responseObject The response
	 */
	public Object getResponse() {
		return responseObject;
	}
}
