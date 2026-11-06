package time;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testGetTotalSeconds() {
		int seconds = Time.getTotalSeconds("05:05:05");
		assertTrue(seconds == 18305, "The total seconds were not calculated properly for good input");
		
	}
	@Test
	public void testGetTotalSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class, () -> {
			Time.getTotalSeconds("10:00");
		}, "Expected StringIndexOutOfBoundsException for short string");
	}
	
	@Test
	public void testGetTotalSecondsBoundary() {
		int seconds = Time.getTotalSeconds("00:00:00");
		assertTrue(seconds == 0, "The total seconds were not calculated properly for boundary input");
	}
	
	

	@ParameterizedTest
	@ValueSource(strings = { "00:00:15", "12:12:15", "23:59:15" })
	void testGetSecondsGoodAndBoundary(String candidate) {
		int seconds = Time.getSeconds(candidate);
		assertTrue(seconds == 15, "The seconds were not calculated properly");
	}

	@Test
	public void testGetSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class, () -> {
			Time.getSeconds("12:00");
		}, "Expected StringIndexOutOfBoundsException for string missing seconds");
	}
	
	

	@ParameterizedTest
	@ValueSource(strings = { "00:10:00", "12:10:00", "23:10:59" })
	void testGetTotalMinutesGoodAndBoundary(String candidate) {
		int minutes = Time.getTotalMinutes(candidate);
		assertTrue(minutes == 10, "The minutes were not calculated properly");
	}

	@Test
	public void testGetTotalMinutesBad() {
		assertThrows(NumberFormatException.class, () -> {
			Time.getTotalMinutes("10:00:00:00");
		}, "Expected NumberFormatException because time length is > 8");
	}
	
	
	
	@ParameterizedTest
	@ValueSource(strings = { "05:00:00", "05:15:15", "05:59:59" })
	void testGetTotalHoursGoodAndBoundary(String candidate) {
		int hours = Time.getTotalHours(candidate);
		assertTrue(hours == 5, "The hours were not calculated properly");
	}
	
	@Test
	public void testGetTotalHoursBad() {
		assertThrows(StringIndexOutOfBoundsException.class, () -> {
			Time.getTotalHours("1");
		}, "Expected StringIndexOutOfBoundsException for string missing characters");
	}
	
	

	@ParameterizedTest
	@ValueSource(strings = { "10:00:00:05", "12:12:12:05", "23:59:59:05" })
	void testGetMillisecondsGoodAndBoundary(String candidate) {
		int milliseconds = Time.getMilliseconds(candidate);
		assertTrue(milliseconds == 5, "The milliseconds were not calculated properly");
	}

	@Test
	public void testGetMillisecondsBad() {
		assertThrows(NumberFormatException.class, () -> {
			Time.getMilliseconds("10:00:00:05:99");
		}, "Expected NumberFormatException because time length is > 11");
	}
	
	
	

}
