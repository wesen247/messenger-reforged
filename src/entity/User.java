package entity;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;
/**
 * Entity class, Class with user information
 * @author Zacharias André Ruben Amir
 *
 */
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4193667861507882544L;
	private String name;
	private String password;
	private ArrayList<User> friendList;
	private ArrayList<String> memberOfGroups;
	private byte[] imageInByte;
	
	public User(String name) {
		this.name = name;
	}
	
	public User(String name, ArrayList<String> memberOfGroups, ArrayList<User> friendList, BufferedImage image) {
		this.name = name;
		this.memberOfGroups = memberOfGroups;
		this.friendList = friendList;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "jpg", bos );
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.imageInByte = bos.toByteArray();
	}

	public String getName() {
		return name;
	}
	
	public BufferedImage getImage() {
		
		ByteArrayInputStream bis = new ByteArrayInputStream(imageInByte);
		try {
			return ImageIO.read(bis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public ArrayList<String> getGroups(){
		return memberOfGroups;
	}
	
	public boolean passwordMatch(String enteredPassword) {
		if(enteredPassword==password) {
			return true;
		}else {
			return false;
		}
	}
	
	public ArrayList<User> getFriends() {
		return friendList;
	}
	
	public void addGroup(String groupName) {
		memberOfGroups.add(groupName);
	}
}
