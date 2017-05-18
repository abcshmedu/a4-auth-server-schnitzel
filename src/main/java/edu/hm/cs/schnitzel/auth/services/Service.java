/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.auth.services;

import edu.hm.cs.schnitzel.auth.requesthandler.ServiceResult;

/**
 *
 * @author nicfel
 */
public interface Service {
    
    ServiceResult validateToken(final String token);
    
    ServiceResult requestToken(final String username, final String password);
    
}
