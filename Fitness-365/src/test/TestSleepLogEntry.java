package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.BeforeClass;
import org.junit.Test;

import model.SleepLogEntry;

public class TestSleepLogEntry {

	private static SleepLogEntry entry1;
	private static SleepLogEntry entry2;
	private static SleepLogEntry entry3;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entry1 = new SleepLogEntry(LocalDate.of(1997, 2, 11), 
				LocalTime.of(22, 30), LocalTime.of(8, 0), 0);
		entry2 =  new SleepLogEntry(LocalDate.of(1997, 2, 12), 
				null, LocalTime.of(8, 0), 0);
		
		entry3 =  new SleepLogEntry(LocalDate.of(1997, 2, 12), 
				LocalTime.of(12, 0), null, 0);
		
	}
	
	@Test
	public void testGetHoursAsleep() {
		double hours1 = entry1.getHoursAsleep();
		double hours2 = entry2.getHoursAsleep();
		double hours3 = entry3.getHoursAsleep();
		
		assertTrue("Testing with valid times.", hours1 == 9.5);
		assertTrue("Testing with null sleep time.", hours2 == -1.0);
		assertTrue("Testing with null wake time.", hours3 == -1.0);

	}
	
	@Test
	public void testGetFormattedSleepTime()
	{
		String time1 = entry1.getFormatedSleepTime();
		String time2 = entry2.getFormatedSleepTime();
		
		assertTrue("Testing with valid time.", time1.equalsIgnoreCase("10:30 pm"));
		assertTrue("Testing with null sleep time.", time2 == null);
	}
	
	@Test
	public void testGetFormattedWakeTime()
	{
		String time1 = entry1.getFormatedWakeTime();
		String time2 = entry3.getFormatedWakeTime();
		
		assertTrue("Testing with valid time.", time1.equalsIgnoreCase("08:00 am"));
		assertTrue("Testing with null wake time.", time2 == null);
		
	}

}
