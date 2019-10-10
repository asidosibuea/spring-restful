package common;

import java.util.LinkedList;
import java.util.List;

public class RestResponse {

	private int status;
	private String message;
	private Object data;
	private List<String> listErrorMessage  = new LinkedList<>();

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<String> getListErrorMessage() {
		return listErrorMessage;
	}

	public void setListErrorMessage(List<String> listErrorMessage) {
		this.listErrorMessage = listErrorMessage;
	}

	

}
