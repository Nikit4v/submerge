package com.submerge.sub.ass;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Contain the subtitle text, their timings, and how it should be displayed. The
 * fields which appear in each Dialogue line are defined by a Format: line,
 * which must appear before any events in the section. The format line specifies
 * how SSA will interpret all following Event lines.
 * 
 * The field names must be spelled correctly, and are as follows:
 * 
 * Marked, Start, End, Style, Name, MarginL, MarginR, MarginV, Effect, Text
 * 
 * The last field will always be the Text field, so that it can contain commas.
 * The format line allows new fields to be added to the script format in future,
 * and yet allow old versions of the software to read the fields it recognises -
 * even if the field order is changed.
 */
public class Events implements Comparable<Events>, Serializable {

	private static final long serialVersionUID = -6706119890451628726L;
	public static final String FORMAT_STRING = "Layer, Start, End, Style, Name, MarginL, MarginR, MarginV, Effect, Text";
	private static final String ESCAPED_RETURN = "\\N";
	private static final String DIALOGUE = "Dialogue: ";
	private static final String COMMA = ",";

	/**
	 * Subtitles having different layer number will be ignored during the
	 * collusion detection.
	 * 
	 * Higher numbered layers will be drawn over the lower numbered.
	 */
	private int layer;

	/**
	 * Timecodes
	 */
	private ASSTime time;

	/**
	 * Style name. If it is "Default", then your own *Default style will be
	 * subtituted.
	 * 
	 * However, the Default style used by the script author IS stored in the
	 * script even though SSA ignores it - so if you want to use it, the
	 * information is there - you could even change the Name in the Style
	 * definition line, so that it will appear in the list of "script" styles.
	 */
	private String style;

	/**
	 * Character name. This is the name of the character who speaks the
	 * dialogue. It is for information only, to make the script is easier to
	 * follow when editing/timing.
	 */
	private String name = StringUtils.EMPTY;

	/**
	 * 4-figure Left Margin override. The values are in pixels. All zeroes means
	 * the default margins defined by the style are used.
	 */
	private String marginLeft = "0000";

	/**
	 * 4-figure Right Margin override. The values are in pixels. All zeroes
	 * means the default margins defined by the style are used.
	 */
	private String marginRight = "0000";

	/**
	 * 4-figure Bottom Margin override. The values are in pixels. All zeroes
	 * means the default margins defined by the style are used.
	 */
	private String marginVertical = "0000";

	/**
	 * Transition Effect. This is either empty, or contains information for one
	 * of the three transition effects implemented in SSA v4.x
	 * 
	 * The effect names are case sensitive and must appear exactly as shown. The
	 * effect names do not have quote marks around them.
	 * 
	 * "Scroll up;y1;y2;delay[;fadeawayheight]"means that the text/picture will
	 * scroll up the screen. The parameters after the words "Scroll up" are
	 * separated by semicolons.
	 * 
	 * “Banner;delay” means that text will be forced into a single line,
	 * regardless of length, and scrolled from right to left accross the screen.
	 */
	private String effect = StringUtils.EMPTY;

	/**
	 * Subtitle Text. This is the actual text which will be displayed as a
	 * subtitle onscreen. Everything after the 9th comma is treated as the
	 * subtitle text, so it can include commas.
	 * 
	 * The text can include \n codes which is a line break, and can include
	 * Style Override control codes, which appear between braces { }.
	 */
	private List<String> textLines;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            style name to apply
	 * @param start
	 *            Start Time of the Event
	 * @param end
	 *            End Time of the Event
	 */
	public Events(String style, ASSTime time, List<String> textLines) {
		this.style = style;
		this.time = time;
		this.textLines = textLines;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(DIALOGUE);

		sb.append(this.layer).append(COMMA);
		sb.append(ASSTime.format(this.time.getStart())).append(COMMA);
		sb.append(ASSTime.format(this.time.getEnd())).append(COMMA);
		sb.append(this.style).append(COMMA);
		sb.append(this.name).append(COMMA);
		sb.append(this.marginLeft).append(COMMA);
		sb.append(this.marginRight).append(COMMA);
		sb.append(this.marginVertical).append(COMMA);
		sb.append(this.effect).append(COMMA);
		this.textLines.forEach(tl -> sb.append(tl.toString()).append(ESCAPED_RETURN));

		return StringUtils.removeEnd(sb.toString(), ESCAPED_RETURN);
	}

	@Override
	public int compareTo(Events event) {
		return this.time.getStart().compareTo(event.time.getStart());
	}

	// ===================== getter and setter start =====================

	public int getLayer() {
		return this.layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public ASSTime getTime() {
		return this.time;
	}

	public void setTime(ASSTime time) {
		this.time = time;
	}

	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMarginLeft() {
		return this.marginLeft;
	}

	public void setMarginLeft(String marginLeft) {
		this.marginLeft = marginLeft;
	}

	public String getMarginRight() {
		return this.marginRight;
	}

	public void setMarginRight(String marginRight) {
		this.marginRight = marginRight;
	}

	public String getMarginVertical() {
		return this.marginVertical;
	}

	public void setMarginVertical(String marginVertical) {
		this.marginVertical = marginVertical;
	}

	public String getEffect() {
		return this.effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public List<String> getText() {
		return this.textLines;
	}

	public void setText(List<String> textLines) {
		this.textLines = textLines;
	}

}