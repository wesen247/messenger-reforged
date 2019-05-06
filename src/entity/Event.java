package entity;

import java.io.Serializable;

/**
 * 
 * @author Ruben, Amir, Andrè
 *
 */
public class Event implements Serializable {

	private static final long serialVersionUID = 2913196604089749918L;
	private Group group;
	private User user;
	private String date;
	private String comment;
	private String location;

	/**
	 * 
	 * @param group
	 * @param user
	 * @param date
	 * @param comment
	 */
	public Event(Group group, User user, String date, String comment, String location) {
		this.group = group;
		this.user = user;
		this.date = date;
		this.comment = comment;
		this.location = location;
	}

	public Group getGroup() {
		return group;
	}

	public User getUser() {
		return user;
	}

	public String getDate() {
		return date;
	}

	public String getComment() {
		return comment;
	}

	public String getLocation() {
		return location;
	}
}
