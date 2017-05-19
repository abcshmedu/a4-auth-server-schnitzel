/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.auth.entities;

/**
 * Represents Books. This class will be able to be parsed from + to json.
 *
 * @author N.Dassler, P.Konopac
 */
public class User {

	// Fields

    /**
     * The login name of the user.
     */
    private String name;

    /**
     * The password of the user.
     * (not encrypted here)
     */
    private String password;
    
    /**
     * The role of the user.
     */
    private Role role;

    // Constructors

    /**
     * Empty Constructor.
     * 
     * Creates a not filled object of User
     */
    public User() {
    	this("", "");
    }

    /**
     * Detailed Constructor.
     * 
     * Creates a filled object of User with following input:
     * 
     * @param nameInput is the name of the user
     * @param passwordInput is the password of the user
     * @param roleInput is the role of the user
     */
    public User(final String nameInput, final String passwordInput, final Role roleInput) {
    	this.name = nameInput;
    	this.password = passwordInput;
    	this.role = roleInput;
    }

    /**
     * Detailed Constructor with default role.
     * 
     * Creates a filled object of User with role "user" and following input:
     * 
     * @param nameInput is the name of the user
     * @param passwordInput is the password of the user
     */
    public User(final String nameInput, final String passwordInput) {
    	this(nameInput, passwordInput, Role.user);
    }
    
    /**
     * Copy Constructor.
     * 
     * Creates a deep copy of the given object
     * 
     * @param original is the original user to copy
     */
    public User(final User original) {
    	this(new String(original.getName()), new String(original.getPassword()), original.getRole());
    }

    // Public Methods

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
		result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object other) {
		boolean result = false;
		if (this == other) {
			result = true;
		}
		else if (other instanceof User) {
			final User otherUser = (User) other;
			result = this.getName().equals(otherUser.getName())
				  && this.getPassword().equals(otherUser.getPassword())
				  && this.getRole().equals(otherUser.getRole());
		}
		return result;
	}

    // Getter and Setter

    /**
     * Getter for name.
     * 
     * @return the name of the user
     */
    public final String getName() {
		return name;
	}

    /**
     * Setter for name.
     * 
     * @param newName is the new name of the user
     */
    public final void setName(final String newName) {
		this.name = newName;
	}

    /**
     * Getter for password.
     * 
     * @return the password of the user
     */
    public final String getPassword() {
		return password;
	}

    /**
     * Setter for password.
     * 
     * @param newPassword is the new password of the user
     */
    public final void setPassword(final String newPassword) {
		this.password = newPassword;
	}

    /**
     * Getter for role.
     * 
     * @return the role of the user
     */
    public final Role getRole() {
		return role;
	}

    /**
     * Setter for role.
     * 
     * @param newRole is the new role of the user
     */
    public final void setRole(final Role newRole) {
		this.role = newRole;
	}
}
