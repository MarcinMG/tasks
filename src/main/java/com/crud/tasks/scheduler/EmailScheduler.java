package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.MailCreatorService;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    private String message;

   // @Scheduled(cron = "0 0 10 * * *")
    @Scheduled(fixedDelay = 1000000)
    public void sendInformationEmail() {
        hasTasks();
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                message
        ));
    }

    public boolean hasTasks() {
        long size = taskRepository.count();
        boolean tasksExist;
        if (size == 0) {
            message = "There is no task";
            tasksExist = false;
        } else if (size == 1) {
            message = "You have " + size + " task";
            tasksExist = true;
        } else {
            message = "You have " + size + " tasks";
            tasksExist = true;
        }
        return tasksExist;
    }
}
