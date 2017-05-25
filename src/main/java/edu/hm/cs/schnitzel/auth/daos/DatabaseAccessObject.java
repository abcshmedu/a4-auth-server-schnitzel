/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.auth.daos;

import edu.hm.cs.schnitzel.auth.entities.Role;
import edu.hm.cs.schnitzel.auth.entities.User;

/**
 *
 * @author konopac
 */
public interface DatabaseAccessObject {

    /**
     * Check if the provided User exists.
     *
     * @param user The provided user.
     * @return True: User exists; False: User doesn't exist.
     */
    boolean userExists(User user);

    /**
     * Check if the provided token is valid.
     *
     * @param token The provided token.
     * @return True: Token is valid; False: Token is not valid.
     */
    boolean tokenIsValid(String token);

    /**
     * Removes token from the database.
     *
     * @param token The token to be removed.
     * @return Success: true; No success: false.
     */
    boolean removeToken(String token);

    /**
     * Adds token to database.
     *
     * @param user The user which the token is connected to.
     * @param token The token to be added.
     * @return Success: true; No success: false (token was already in database).
     */
    boolean addToken(User user, String token);

    /**
     * Check role of token.
     *
     * @param token The token to be checked.
     * @return The role of the token.
     */
    Role getRole(String token);

}
