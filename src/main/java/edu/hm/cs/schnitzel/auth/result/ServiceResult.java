package edu.hm.cs.schnitzel.auth.result;

import edu.hm.cs.schnitzel.auth.entities.Role;

/**
 * @author philipp
 *
 */
public class ServiceResult implements Result {

	// Fields

	/**
	 * The status code of the result.
	 */
	private int code;

	/**
	 * The message of the result.
	 */
	private String message;

	/**
	 * The content of the result.
	 */
	private Content content;

	// Constructors

	/**
	 * Detailed Constructor.
	 * 
	 * Creates a filled object of ServiceResult with following input
	 * (Content has to be filled afterwards):
	 * 
	 * @param codeInput is the status code of the result
	 * @param messageInput is the message of the result
	 */
	public ServiceResult(final int codeInput, final String messageInput) {
		this.code = codeInput;
		this.message = messageInput;
		this.content = new Content();
	}

	// Getter and Setter

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public void setCode(final int newCode) {
		this.code = newCode;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setMessage(final String newMessage) {
		this.message = newMessage;
	}

	@Override
	public Content getContent() {
		return content;
	}

	@Override
	public void setValid(final boolean newValid) {
		getContent().setValid(newValid);
	}

	@Override
	public void setToken(final String newToken) {
		getContent().setToken(newToken);
	}

	@Override
	public void setUserRole(final Role newUserRole) {
		getContent().setUserRole(newUserRole);
	}
}
