package org.grayleaves.sweater;

import java.util.HashMap;
import java.util.Map;



public class SimpleYarnStash implements YarnStash {

	private Map<YarnEnum, Integer> yarn = new HashMap<>(); 
	public SimpleYarnStash(YarnEnum color, int yards) {
		yarn.put(color, yards);
	}

	public SimpleYarnStash() {
	}

	@Override
	public int use(YarnEnum color, int yards) {
		int available = 0; 
		if (yarn.containsKey(color)) {
			available = yarn.get(color);
			if (available > yards) {
				yarn.put(color, (available - yards)); 
				available = yards; 
			}
			else {
				yarn.put(color, 0); 
			}
		}
		return available;
	}

	@Override
	public int add(YarnEnum color, int yards) {
		Integer available = yarn.get(color);
		if (available != null) {
			yards = yards + available; 
		}
		yarn.put(color, yards); 
		return yards; 
	}

	
	public int zero(YarnEnum color, int yards) {
		Integer available = yarn.get(color);
		if (available != null) {
			yards = 0; 
		}
		yarn.put(color, yards); 
		return yards; 
	}


}
