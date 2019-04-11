package entity;

import java.io.Serializable;

/**
 * 
 * @author Ruben, Amir, Andrè
 *
 */
public class Event implements Serializable{

	private static final long serialVersionUID = 2913196604089749918L;
	private Group group;
	private User user;
	private int date;
	private String comment;
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
	public Group getGroup() {
		return group;
	}
	public User getUser() {
		return user;
	}
	public int getDate() {
		return date;
	}
	public String getComment() {
		return comment;
	}
}
