package demo.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimpleJob implements Job {

    public void execute(JobExecutionContext jec) throws JobExecutionException {
        System.out.println("Hello Quartz!");
    }
}
