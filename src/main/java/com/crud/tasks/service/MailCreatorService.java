package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.scheduler.EmailScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private EmailScheduler emailScheduler;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context  = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_goal", adminConfig.getCompanyGoal());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildSchedulerInformationEmail(String message) {

        List<String> functionalityScheduler = new ArrayList<>();
        functionalityScheduler.add("Sending update every 24 hours");
        functionalityScheduler.add("Provides number of tasks");

        Context contextScheduler = new Context();
        contextScheduler.setVariable("message", message);
        contextScheduler.setVariable("button", "Visit website");
        contextScheduler.setVariable("goodbye", "See you in 24 hours");
        contextScheduler.setVariable("show_button", false);
        contextScheduler.setVariable("is_friend", true);
        contextScheduler.setVariable("admin_config", adminConfig);
        contextScheduler.setVariable("has_tasks", emailScheduler.hasTasks());
        contextScheduler.setVariable("scheduler_functionality", functionalityScheduler);
        return templateEngine.process("mail/scheduled-information-mail", contextScheduler);
    }
}
