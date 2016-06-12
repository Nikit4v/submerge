package submerge.sub.object.srt;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

import submerge.sub.object.itf.TimedObject;

public class SRTTime implements Serializable, TimedObject {

	private static final long serialVersionUID = -5787808223967579723L;
	public static final String PATTERN = "HH:mm:ss,SSS";
	private static final String TS_PATTERN = "%02d:%02d:%02d,%03d";
	public static final String DELIMITER = " --> ";

	private LocalTime start;
	private LocalTime end;

	public SRTTime(LocalTime start, LocalTime end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(format(this.start));
		sb.append(DELIMITER);
		sb.append(format(this.end));
		return sb.toString();
	}

	// ===================== private method =====================

	private static String format(LocalTime time) {

		int hr = time.get(ChronoField.HOUR_OF_DAY);
		int min = time.get(ChronoField.MINUTE_OF_HOUR);
		int sec = time.get(ChronoField.SECOND_OF_MINUTE);
		int ms = time.get(ChronoField.MILLI_OF_SECOND);

		return String.format(TS_PATTERN, hr, min, sec, ms);
	}

	// ===================== getter and setter start =====================

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.submerge.sub.srt.SubtitleTime#getStart()
	 */
	@Override
	public LocalTime getStart() {
		return this.start;
	}

	public void setStart(LocalTime start) {
		this.start = start;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.submerge.sub.srt.SubtitleTime#getEnd()
	 */
	@Override
	public LocalTime getEnd() {
		return this.end;
	}

	public void setEnd(LocalTime end) {
		this.end = end;
	}

}