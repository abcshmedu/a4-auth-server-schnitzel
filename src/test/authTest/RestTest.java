/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authTest;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.hm.JettyStarter;
import edu.hm.cs.schnitzel.auth.daos.PseudoDatabaseAccessObject;
import edu.hm.cs.schnitzel.auth.database.PseudoDatabase;

/**
 *
 * @author konopac
 */
public class RestTest {

    private JettyStarter jettyStarter;

    public RestTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        jettyStarter = new JettyStarter();
        jettyStarter.start();
        PseudoDatabaseAccessObject.clear();
    }

    @After
    public void tearDown() throws Exception {
        jettyStarter.stop();
    }

    // Test methods
    @Test
    public void createEmptyPseudoDatabase() {
    	final PseudoDatabase db = new PseudoDatabase();
    	final boolean expected = true;
    	final boolean got = db.getUsers().isEmpty() && db.getTokens().isEmpty() && db.getTokenUserMap().isEmpty();
    	assertEquals(expected, got);
    }
    
    @Test
    public void filledDatabaseRequestToken() throws IOException {
        // specify expected
        final String expected = "{\"code\": 200, \"message\": \"OK\", \"content\": {\"token\": \"abc\", \"role\": \"user\"}}";
        // send request and receive answer
        final String got = sendAndReceive("GET", "", "{\"username\": \"Max\", \"password\": \"password\"}");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void filledDatabaseRequestTokenWrongPassword() throws IOException {
    	// specify expected
    	final String expected = "{\"code\": 400, \"message\": \"Wrong Password\", \"content\": {}}";
    	// send request and receive answer
    	final String got = sendAndReceive("GET", "", "{\"username\": \"Max\", \"password\": \"wrongPassword\"}");
    	// assert equals
    	assertEquals(expected, got);
    }

    @Test
    public void filledDatabaseValidateToken() throws IOException {
    	// specify expected
    	final String expected = "{\"code\": 200, \"message\": \"OK\", \"content\": {\"valid\": \"true\", \"role\": \"user\"}}";
    	// send request and receive answer
    	final String got = sendAndReceive("GET", "token", "{\"token\": \"abc\"}");
    	// assert equals
    	assertEquals(expected, got);
    }


    // Private Methods
    private String sendAndReceive(String method, String token, String content) throws IOException {
        String result = "";
        try (final Socket socket = new Socket("localhost", 8082);
                final PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                final BufferedReader buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
        	System.out.println();
            System.out.println("SENDING...");
            // send header
            sendHttpHeader(printWriter, method, token, content.length());
            // send content
            sendContent(printWriter, content);

            System.out.println();
            System.out.println("READING...");
            // read answer
            readUntilBody(buffReader);
            // read content
            result = buffReader.lines().collect(Collectors.joining());
            System.out.println("\t" + result);
            System.out.println();
        }
        return result;
    }

    private void sendHttpHeader(PrintWriter writer, String method, String token, int contentLength) {
        writer.print(method + " /auth/" + token + " HTTP/1.0\r\n");
        System.out.print("\t" + method + " /auth" + token + " HTTP/1.0\r\n");
        writer.print("Host: localhost\r\n");
        System.out.print("\t" + "Host: localhost\r\n");
        writer.print("Content-Type: application/json\r\n");
        System.out.print("\t" + "Content-Type: application/json\r\n");
        writer.print("Content-Length: " + contentLength + "\r\n");
        System.out.print("\t" + "Content-Length: " + contentLength + "\r\n");
        writer.print("\r\n");
        System.out.print("\t" + "\r\n");
        writer.flush();
    }

    private void readUntilBody(BufferedReader buffReader) throws IOException {
    	System.out.println();
        String line = buffReader.readLine();
        while (line.length() > 0) {
            System.out.println("\t" + line);
            line = buffReader.readLine();
        }
        System.out.println();
    }

    private void sendContent(PrintWriter printWriter, String content) {
        printWriter.write(content);
        System.out.println("\t" + content);
        printWriter.flush();
    }

}
