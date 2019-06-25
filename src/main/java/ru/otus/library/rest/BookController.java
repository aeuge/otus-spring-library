package ru.otus.library.rest;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
    private JobLauncher jobLauncher;
    private Job importBookJob;

    public BookController(JobLauncher jobLauncher, Job importBookJob ) {
        this.jobLauncher = jobLauncher;
        this.importBookJob = importBookJob;
    }

    @GetMapping("/")
    public String listBook() {
        return "index";
    }

    @GetMapping("/batch")
    public String runBatch() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters =
                new JobParametersBuilder()
                        .addLong("time",System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(importBookJob, jobParameters);
        return "batch";
    }

    @GetMapping("/book/{id}")
    public String editBook() {
        return "book";
    }
}
