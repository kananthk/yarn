package org.grayleaves.sweater;

public class YarnStatusResponse extends StatusResponse {


	protected static final int STITCHES_PER_YARD = 200;
	protected static YarnEnum COLOR = YarnEnum.GRAY;
	protected static YarnStash YARN_SERVICE = new SimpleYarnStash(); 
	private String yarnResponse = "";
	private int used;

	public static void color(YarnEnum color) {
		COLOR  = color; 
	}
	
	public static void forceColor(String color) {
		for (YarnEnum yarnEnum : YarnEnum.values()) {
			if (color.equalsIgnoreCase(yarnEnum.toString())) {
				color(yarnEnum); 
			}
		}
	}
	public static void reset() {
		YARN_SERVICE = new SimpleYarnStash(); 
		COLOR = YarnEnum.GRAY;
	}

	public YarnStatusResponse() {
		super(); 
	}
		
	public String getColor() {
		return COLOR.toString();
	}
	public void setColor(String color) {
		
	}
	public String getYarnResponse() {
		return yarnResponse;
	}

	public void setYarnResponse(String yarnResponse) {
		this.yarnResponse = yarnResponse;
	}

	public void use(int yards) {
		used = YARN_SERVICE.use(COLOR, yards);
		yarnResponse = yards+" yards "+COLOR+" yarn requested; "+used+" yards used";
	}
	public int add(int yards) {
		int added = YARN_SERVICE.add(COLOR, yards);
		yarnResponse = yards+" yards "+COLOR+" yarn added; "+added+" yards total";
		return added;  
	}
	
	public int zero() {
		int used = 0 ;
		yarnResponse = COLOR+" is resetted ,  total availabe" +used;
		return   used ;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}
}
