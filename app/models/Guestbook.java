package models;

import java.util.*;

import org.apache.commons.codec.digest.DigestUtils;

public class Guestbook extends JongoModel {

	public String name;
	public String email;
	public String password;

	public String content;
	public Date postAt;

	public static JongoModel.Finder<Guestbook> find = new JongoModel.Finder<Guestbook>(
			Guestbook.class);

	public static Guestbook findbyIDPW(Long id2, String password2) {
		return find
				.models()
				.findOne(
						"{email: " + id2 + ", password:  "
								+ DigestUtils.md5Hex(password2) + "}")
				.as(Guestbook.class);
	}

}