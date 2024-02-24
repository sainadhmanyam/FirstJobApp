package com.saispring.FirstJobApp.job.impl;

import com.saispring.FirstJobApp.job.Job;
import com.saispring.FirstJobApp.job.JobRepository;
import com.saispring.FirstJobApp.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs = new ArrayList<>();

    @Autowired
    private JobRepository jobRepository;
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJobById(Long id, Job updateJob) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if(optionalJob.isPresent()) {
            Job job = optionalJob.get();
            job.setDescription(updateJob.getDescription());
            job.setMaxSalary(updateJob.getMaxSalary());
            job.setLocation(updateJob.getLocation());
            job.setTitle(updateJob.getTitle());
            job.setMinSalary(updateJob.getMinSalary());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
