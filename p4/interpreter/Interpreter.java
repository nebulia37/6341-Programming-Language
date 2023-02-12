package interpreter;

import java.io.*;
import java.util.*;
import parser.ParserWrapper;
import ast.*;

public class Interpreter {

    // Process return codes
    public static final int EXIT_SUCCESS = 0;
    public static final int EXIT_PARSING_ERROR = 1;
    public static final int EXIT_STATIC_CHECKING_ERROR = 2;

    /* Proj3: */
    public static final int EXIT_UNINITIALIZED_VAR_ERROR = 3;
    public static final int EXIT_DIV_BY_ZERO_ERROR = 4;
    public static final int EXIT_FAILED_STDIN_READ = 5;
    
    /* Proj4: */
    public static LinkedMap lm = new LinkedMap(null);
    
    public static void main(String[] args) {
        String filename = args[0];
        Program astRoot = null;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
	    astRoot = ParserWrapper.parse(reader);
        } catch (Exception ex) {
            Interpreter.fatalError("Uncaught parsing error: " + ex, Interpreter.EXIT_PARSING_ERROR);
        }
        
        /* Proj1, Proj2: Static Checking */
        
        try {
        	astRoot.typeCheck(lm);
        } catch (Exception ex) {
        	Interpreter.fatalError("Static checking error: " + ex, EXIT_STATIC_CHECKING_ERROR);
        } 
        
        /* Proj3, Proj4: Evaluation */
        astRoot.exec(new HashMap<String, AS>());
		
        // for debugging
        astRoot.print(System.out);
    }
    
    public static void fatalError(String message, int processReturnCode) {
        System.out.println(message);
        System.exit(processReturnCode);
    }
}
