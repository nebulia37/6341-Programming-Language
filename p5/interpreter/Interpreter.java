package interpreter;

import ast.AbstractVal;
import ast.Program;
import ast.Type;
import parser.ParserWrapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Interpreter {

    // Process return codes
    public static final int EXIT_SUCCESS = 0;
    public static final int EXIT_PARSING_ERROR = 1;
    public static final int EXIT_STATIC_CHECKING_ERROR = 2;
    public static final int EXIT_UNINITIALIZED_VAR_ERROR = 3;
    public static final int EXIT_DIV_BY_ZERO_ERROR = 4;
    public static final int EXIT_FAILED_STDIN_READ = 5;
    public static final int EXIT_DEAD_CODE = 6;

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
        // for debugging
        astRoot.print(System.out);

        ArrayList<Map<String, Type>> tables = new ArrayList<>();
        astRoot.check(tables);
        Map<String, AbstractVal> valueTable = new HashMap<>();
        Scanner s = new Scanner(System.in);
        astRoot.evaluate(valueTable, s);
        s.close();

        // for debugging
        // System.out.println(valueTable);
        System.exit(EXIT_SUCCESS);
    }

    public static void fatalError(String message, int processReturnCode) {
        System.out.println(message);
        System.exit(processReturnCode);
    }
}
