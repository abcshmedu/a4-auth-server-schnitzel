/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.auth.daos;

import edu.hm.cs.schnitzel.auth.database.PseudoDatabase;
import edu.hm.cs.schnitzel.auth.entities.Role;
import edu.hm.cs.schnitzel.auth.entities.User;
import java.util.HashSet;

/**
 * PseudoDatabaseAccessObject.
 *
 * This implementation of DatabaseAccessObject uses PseudoDatabase as a database
 *
 * @author konopac
 */
public class PseudoDatabaseAccessObject implements DatabaseAccessObject {

    //Constant Variables
    //--------------------------------------------------------------------------
    /**
     * The database.
     */
    private static final PseudoDatabase DATABASE
            = new PseudoDatabase(new HashSet<>(), new HashSet<>());

    //Methods Public Static
    //--------------------------------------------------------------------------
    /**
     * Clear the database. Mostly used for testing
     */
    public static final void clear() {
        DATABASE.getUsers().clear();
        DATABASE.getTokens().clear();
        DATABASE.getTokenUserMap().clear();
    }
    
    //Constructor
    /**
     * Default C-Tor.
     */
    public PseudoDatabaseAccessObject() {
    }

    @Override
    public boolean userExists(User user) {
        //stream users and return true if a match was found
        return DATABASE.getUsers().stream()
                .anyMatch(currentUser -> user.equals(currentUser));
    }

    @Override
    public boolean tokenIsValid(String token) {
        //stream users and return true if a match was found
        return DATABASE.getTokens().stream()
                .anyMatch(currentToken -> currentToken.equals(token));
    }

    @Override
    public final boolean removeToken(String token) {
        return DATABASE.getTokens().remove(token);
    }

    @Override
    public final boolean addToken(User user, String token) {
        final boolean result = DATABASE.getTokens().add(token);
        if (result) {
            DATABASE.getTokenUserMap().put(token, user);
        }
        /* Code for expiration Time 10 minutes
        if(result) {
            new Timer(token, true).schedule(new TimerTask() {
                @Override
                public void run() {
                    DATABASE.getTokens().remove(token);
                    DATABASE.getTokenUserMap().remove(token);
                }
            }, 600000);
        }*/
        return result;
    }

    @Override
    public final Role getRole(String token) {
        return DATABASE.getTokenUserMap().get(token).getRole();
    }

}
