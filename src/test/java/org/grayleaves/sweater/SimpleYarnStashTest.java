package org.grayleaves.sweater;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleYarnStashTest {

	private YarnStash yarnService;

	@Test
	public void defaultsToZeroYardsReturned() {
		yarnService = new SimpleYarnStash(); 
		assertEquals(0, yarnService.use(YarnEnum.GRAY, 1)); 
	}
	@Test
	public void addedCanBeUsed() {
		yarnService = new SimpleYarnStash(); 
		yarnService.add(YarnEnum.GRAY, 1); 
		assertEquals("1 yards available",1, yarnService.use(YarnEnum.GRAY, 1)); 
		assertEquals("0 yards available",0, yarnService.use(YarnEnum.GRAY, 1)); 
	}
	@Test
	public void multipleAddsAndUses() {
		yarnService = new SimpleYarnStash(); 
		yarnService.add(YarnEnum.GRAY, 3); 
		assertEquals("2 yards available",2, yarnService.use(YarnEnum.GRAY, 2)); 
		yarnService.add(YarnEnum.GRAY, 1); 
		assertEquals("1 yards available",1, yarnService.use(YarnEnum.GRAY, 1)); 
		assertEquals("1 yards available",1, yarnService.use(YarnEnum.GRAY, 1)); 
		assertEquals("0 yards available",0, yarnService.use(YarnEnum.GRAY, 1)); 
	}
	@Test
	public void multipleColors() {
		yarnService = new SimpleYarnStash(); 
		yarnService.add(YarnEnum.GRAY, 3); 
		yarnService.add(YarnEnum.WHITE, 3); 
		assertEquals("2 yards available",2, yarnService.use(YarnEnum.GRAY, 2)); 
		assertEquals("3 yards available",3, yarnService.use(YarnEnum.WHITE, 3)); 
	}
	@Test
	public void addReturnsTotal() {
		yarnService = new SimpleYarnStash();
		assertEquals(3, yarnService.add(YarnEnum.GRAY, 3)); 
		assertEquals(5, yarnService.add(YarnEnum.GRAY, 2)); 
		assertEquals(9, yarnService.add(YarnEnum.GRAY, 4)); 
	}
	

}
