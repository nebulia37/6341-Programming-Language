package ast;
import java.util.*;

public class LinkedMap {
	
	public Map<String, String> tbl;
	public LinkedMap prev = null;	
	
	public LinkedMap(LinkedMap map) {
		tbl = new HashMap<String, String>();
		// Link current map with previous map
		if (map != null)
			prev = map;
	}
	
	public String get(String key) {
		String value = "";
		if (this.tbl.containsKey(key))
			value = this.tbl.get(key);
		else if(this.prev != null)
			value = this.prev.get(key);
		return value;
	}
}

