package org.mohan.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mohan.messenger.database.DatabaseClass;
import org.mohan.messenger.model.Profile;

public class ProfileService {
	// get messages by accessing database
	private static Map<String, Profile> profiles = DatabaseClass.getProfiles();

	static {
		profiles.put("mohandast52", new Profile(1, "mohandast52", "Mohan", "Das"));
		profiles.put("rajan1974", new Profile(2, "rajan1974", "Soundara", "Rajan"));
	}

	public ProfileService() {

	}

	public List<Profile> getAllProfiles() {
		return (new ArrayList<Profile>(profiles.values()));
	}

	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}

	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
}
