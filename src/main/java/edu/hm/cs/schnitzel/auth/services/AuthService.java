/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.auth.services;

import edu.hm.cs.schnitzel.auth.daos.DatabaseAccessObject;
import edu.hm.cs.schnitzel.auth.daos.PseudoDatabaseAccessObject;
import edu.hm.cs.schnitzel.auth.entities.User;
import edu.hm.cs.schnitzel.auth.result.Result;
import edu.hm.cs.schnitzel.auth.result.ServiceResult;
import java.util.Random;

/**
 *
 * @author konopac
 */
public class AuthService implements Service {

    private static final int CODE_OK = 200;
    private static final int CODE_NOT_FOUND = 404;
    private static final String OK_MESSAGE = "OK";

    public AuthService() {
    }

    @Override
    public final Result validateToken(final String token) {
        //result
        final Result result;
        //create dao
        final DatabaseAccessObject dao = new PseudoDatabaseAccessObject();
        //check token if is valid
        final boolean tokenIsValid = dao.tokenIsValid(token);
        if (tokenIsValid) {
            result = new ServiceResult(CODE_OK, OK_MESSAGE);
            result.setValid(true);
            result.setUserRole(dao.getRole(token));
        } else {
            result = new ServiceResult(CODE_NOT_FOUND,
                    "Your token does not exist.");
        }
        return result;
    }

    @Override
    public final Result requestToken(final User user) {
        //result
        final Result result;
        //create dao
        final DatabaseAccessObject dao = new PseudoDatabaseAccessObject();
        //check if user exists
        final boolean userExists = dao.userExists(user);
        if (userExists) {
            result = new ServiceResult(CODE_OK, OK_MESSAGE);
            //build random string (not secure because random algorithm is weak)
            String token = "";
            final Random random = new Random();
            //CHECKSTYLE:OFF
            for (int i = 0; i < 25; i++) {
                token = token + random.nextInt(26) + 'a';
            }
            //CHECKSTYLE:ON
            result.setToken(token);
        } else {
            result = new ServiceResult(CODE_NOT_FOUND, "The user does not"
                    + " exist. Please check spelling");
        }
        return result;
    }
}
