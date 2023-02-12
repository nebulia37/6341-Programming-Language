package parser;

import ast.Program;
import java_cup.runtime.*;

import java.io.Reader;

public class ParserWrapper {
    public static Program parse(Reader reader) throws Exception {
        ComplexSymbolFactory csf = new ComplexSymbolFactory();
        Lexer scanner = new Lexer(reader, csf);
        Parser parser = new Parser(scanner, csf);
        return (Program) parser.parse().value;
    }
}
