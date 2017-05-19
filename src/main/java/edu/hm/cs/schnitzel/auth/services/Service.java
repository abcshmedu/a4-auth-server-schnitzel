/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.auth.services;

import edu.hm.cs.schnitzel.auth.result.Result;

/**
 *
 * @author nicfel
 */
public interface Service {
    
    Result validateToken(final String token);
    
    Result requestToken(final String username, final String password);
    
}
