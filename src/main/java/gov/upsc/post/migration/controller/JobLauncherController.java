package gov.upsc.post.migration.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobLauncherController {

    private final JobLauncher jobLauncher;
    private final Job myJob;

    public JobLauncherController(JobLauncher jobLauncher, @Qualifier("job") Job myJob) {
        this.jobLauncher = jobLauncher;
        this.myJob = myJob;
    }

    @PostMapping("/run")
    public String runJob(@RequestParam String requestDate) throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addString("requestDate", requestDate)
                .addLong("time", System.currentTimeMillis()) // ensures uniqueness
                .toJobParameters();

        jobLauncher.run(myJob, params);
        return "Job launched with date=" + requestDate;
    }
}
