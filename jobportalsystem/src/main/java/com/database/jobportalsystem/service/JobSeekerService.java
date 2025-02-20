package com.database.jobportalsystem.service;

import java.util.List;
import org.hibernate.SessionFactory;
import com.database.jobportalsystem.entities.JobSeeker;

public interface JobSeekerService {
    void insertJobSeeker(SessionFactory sf);
    void updateJobSeeker(SessionFactory sf);
    void deleteJobSeeker(SessionFactory sf);
    void getAllJobSeekers(SessionFactory sf);
    void getJobSeeker(SessionFactory sf);
    void getJobSeekerInformation(SessionFactory sf);
}
