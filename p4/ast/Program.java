package ast;
import java.io.PrintStream;
import java.util.*;

public class Program extends ASTNode {
    public final UnitList unitList;
    public static Scanner s;
    
    public Program(UnitList ul, Location loc) {
        super(loc);
        unitList = ul;
        //s = new Scanner(System.in);
    }
    public void print(PrintStream ps) {
	unitList.print(ps,"");
    }
    
    public void typeCheck(LinkedMap lm) throws Exception{
    	unitList.typeCheck(lm);	
    }
    
    public void exec(Map<String, AS> st) {
    	s = new Scanner(System.in);
    	unitList.exec(st);
    	s.close();
    }
}
