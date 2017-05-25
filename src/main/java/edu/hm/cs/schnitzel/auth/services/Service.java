/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.auth.services;

import edu.hm.cs.schnitzel.auth.entities.User;
import edu.hm.cs.schnitzel.auth.result.Result;

/**
 *
 * @author nicfel
 */
public interface Service {

    /**
     * Validate the specified token.
     * @param token The specified token.
     * @return A result which can be processed by the requestHandler.
     */
    Result validateToken(final String token);

    /**
     * Request token for the specified user.
     * @param user The specified user.
     * @return A result which can be processed by the requestHandler.
     */
    Result requestToken(User user);

}
