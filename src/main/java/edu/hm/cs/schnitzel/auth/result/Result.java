package edu.hm.cs.schnitzel.auth.result;

import edu.hm.cs.schnitzel.auth.entities.Role;

/**
 *
 * @author konopac
 */
public interface Result {

    /**
     * Getter for code.
     *
     * @return the status code of the result
     */
    int getCode();

    /**
     * Setter for code.
     * 
     * @param newCode is the new code
     */
    void setCode(final int newCode);

    /**
     * Getter for message.
     *
     * @return the message of the result
     */
    String getMessage();

    /**
     * Setter for message.
     * 
     * @param newMessage is the new message
     */
    void setMessage(final String newMessage);

    /**
     * Getter for content.
     *
     * @return the content of the result
     */
    Content getContent();

    /**
     * Setter for valid.
     * 
     * @param newValid is the new value of valid
     */
    void setValid(final boolean newValid);

    /**
     * Setter for token.
     * 
     * @param newToken is the new token
     */
    void setToken(final String newToken);

    /**
     * Setter for userRole.
     * 
     * @param newUserRole is the new user role
     */
    void setUserRole(final Role newUserRole);

    /**
     * Inner class to hold the content of the result.
     * 
     * @author philipp
     */
    class Content {
    	
    	// Fields
    	
    	/**
    	 * The checked token is valid or not.
    	 */
    	private boolean valid;
    	
    	/**
    	 * The token as String if requested.
    	 */
    	private String token;
    	
    	/**
    	 * The role of the user matching to the token
    	 */
    	private Role userRole;
    	
    	// Constructors

    	/**
    	 * Empty Constructor.
    	 */
    	public Content() {
    		this.valid = false;
    		this.token = "";
    		this.userRole = null;
    	}

    	// Getter and Setter

    	/**
    	 * Getter for valid.
		 *
    	 * if the token was NOT requested, this will return false
    	 * 
    	 * @return true, if the token was valid
    	 */
    	public final boolean isValid() {
			return valid;
    	}

    	/**
    	 * Setter for valid.
    	 * 
    	 * @param newValid is the new value for valid
    	 */
    	protected final void setValid(final boolean newValid) {
			this.valid = newValid;
		}

    	/**
    	 * Getter for token.
    	 * 
    	 * @return the token, if requested, else it will be empty
    	 */
    	public final String getToken() {
    		return token;
    	}

    	/**
    	 * Setter for token.
    	 * 
    	 * @param newToken is the new token for the content
    	 */
    	protected final void setToken(final String newToken) {
			this.token = newToken;
		}

    	/**
    	 * Getter for userRole.
    	 * 
    	 * @return the role of the user matching to the token
    	 */
    	public final Role getUserRole() {
			return userRole;
		}

    	/**
    	 * Setter for userRole.
    	 * 
    	 * @param newUserRole is the new role for the user
    	 */
    	protected final void setUserRole(final Role newUserRole) {
			this.userRole = newUserRole;
		}
    }
}
