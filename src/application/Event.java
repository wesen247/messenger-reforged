package application;
/**
 * 
 * @author Ruben, Amir, Andrè
 *
 */
public class Event {
	private Group group;
	private User user;
	private int date;
	private String comment = " ";
	/**
	 * 
	 * @param group
	 * @param user
	 * @param date
	 * @param comment
	 */
	public Event(Group group, User user, int date, String comment) {
		this.group = group;
		this.user = user;
		this.date = date;
		this.comment = comment;
	}
}
