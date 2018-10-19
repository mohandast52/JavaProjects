package org.mohan.messenger.model;

import java.util.Date;

public class Profile {
	private long id;
	private String profileName;
	private String firstName;
	private String secondName;
	private Date created;

	public Profile() {

	}

	public Profile(long id, String profileName, String firstName, String secondName) {
		super();
		this.id = id;
		this.profileName = profileName;
		this.firstName = firstName;
		this.secondName = secondName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
