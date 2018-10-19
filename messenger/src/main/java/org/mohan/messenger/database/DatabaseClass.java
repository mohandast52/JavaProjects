package org.mohan.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.mohan.messenger.model.Message;
import org.mohan.messenger.model.Profile;

public class DatabaseClass {
	// just a static database (stub)
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();

	public static Map<Long, Message> getMessages() {
		return messages;
	}

	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
}
