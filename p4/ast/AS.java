package ast;
import java.util.*;

public enum AS {
	Neg(0), Zero(1), Pos(2), AnyInt(3), AnyFloat(4), Error(-1);
	
	public final int v;
	private AS(int v) {
		this.v = v;
	}
	public int v() {
		return this.v;
	}	
}


	





