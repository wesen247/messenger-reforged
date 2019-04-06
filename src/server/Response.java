package server;

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
	 * @param type
	 * @param responseObject
	 */
	public Response(String type, Object responseObject) {
		this.type = type;
		this.responseObject = responseObject;
	}
	/**
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}
	/**
	 * 
	 * @return
	 */
	public Object getResponse() {
		return responseObject;
	}
}
