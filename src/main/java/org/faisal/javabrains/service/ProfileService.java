package org.faisal.javabrains.service;

import org.faisal.javabrains.config.DatabaseClass;
import org.faisal.javabrains.exceptions.DataNotFoundException;
import org.faisal.javabrains.model.Profile;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProfileService {
    private Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public List<Profile> getAllProfiles() {
        return profiles.values().stream().toList();
    }

    public void createProfile(Profile profile) {
        profile.setId(profiles.size() + 1);
        profile.setCreated(Date.from(Instant.now()));
        profiles.put(profile.getProfileName(), profile);
    }

    public Profile updateProfile(Profile profile) {
        return profiles.put(profile.getProfileName(), profile);
    }

    public void deleteProfile(String profileName) {
        var profile = profiles.get(profileName);
        if (profile != null)
            profiles.remove(profile.getProfileName());
    }

    public Profile getProfile(String profileName) {
        var profile = profiles.get(profileName);
        if (profile != null) return profile;
        else throw new DataNotFoundException(String.format("profile with profileName:%d not found", profileName));
    }
}
