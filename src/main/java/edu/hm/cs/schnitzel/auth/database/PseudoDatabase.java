/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.auth.database;

import edu.hm.cs.schnitzel.auth.entities.User;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * A pseudo database for storing tokens and users + passwords.
 */
public class PseudoDatabase {

    //Object Variables
    /**
     * Set for all Users.
     */
    private final Set<User> users;
    /**
     * Set for all currently active tokens.
     */
    private final Set<String> tokens;
    /**
     * Maps users to tokens.
     */
    private final Map<String, User> tokenUserMap;

    //Constructors
    /**
     * Creates a empty Database.
     */
    public PseudoDatabase() {
        this(new HashSet<User>(), new HashSet<String>());
    }

    /**
     * Creates a database pre initialized with the given collections.
     *
     * @param users The list for all users.
     * @param tokens The currently active tokens.
     */
    public PseudoDatabase(Set<User> users, Set<String> tokens) {
        this.users = users;
        this.tokens = tokens;
        this.tokenUserMap = new TreeMap<>();
    }

    //Getter
    /**
     * Simple getter for userset.
     *
     * @return The set.
     */
    public final Set<User> getUsers() {
        return users;
    }

    /**
     * Simple getter for tokenset.
     *
     * @return The set.
     */
    public final Set<String> getTokens() {
        return tokens;
    }
    
    /**
     * Simple getter for tokenUserMap.
     * @return The map.
     */
    public final Map<String, User> getTokenUserMap() {
        return tokenUserMap;
    }

}
