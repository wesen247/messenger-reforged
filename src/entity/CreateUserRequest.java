package entity;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Entity, used to create new user
 * @author André Ruben Amir
 *
 */
public class CreateUserRequest implements Serializable{

	private static final long serialVersionUID = 8993208953585133772L;
	private String password;
	private String name;
	private byte[] imageInByte;

	/**
	 * @author André Ruben Amir
	 * @param name
	 * @param password
	 */
	public CreateUserRequest(String name, String password, BufferedImage image) {
		this.name = name;
		this.password = password;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "jpg", bos );
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException n) {
			JOptionPane.showMessageDialog(null, "Måste välja en bild!");
		}
		this.imageInByte = bos.toByteArray();
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
	/**
	 * @author André Ruben Amir
	 * @return Name of user
	 */
	public String getName() {
		return name;
	}
	/**
	 * @author André Ruben Amir
	 * @return User password
	 */
	public String getPassword() {
		return password;
	}
}

