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
    private Map<Long, Profile> profiles = DatabaseClass.getProfiles();

    public List<Profile> getAllProfiles() {
        return profiles.values().stream().toList();
    }

    public void createProfile(Profile profile) {
        profile.setId(profiles.size() + 1);
        profile.setCreated(Date.from(Instant.now()));
        profiles.put(profile.getId(), profile);
    }

    public Profile updateProfile(Profile profile) {
        return profiles.put(profile.getId(), profile);
    }

    public void deleteProfile(long id) {
        var profile = profiles.get(id);
        if (profile != null)
            profiles.remove(profile.getId());
    }

    public Profile getProfile(long id) {
        var profile = profiles.get(id);
        if (profile != null) return profile;
        else throw new DataNotFoundException(String.format("profile with id:%d not found", id));
    }
}
