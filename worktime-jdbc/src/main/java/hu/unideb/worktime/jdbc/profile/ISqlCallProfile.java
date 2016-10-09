package hu.unideb.worktime.jdbc.profile;

import hu.unideb.worktime.api.model.profile.ProfileRecord;

public interface ISqlCallProfile {
    
    ProfileRecord getProfileData(int workerId);

}
