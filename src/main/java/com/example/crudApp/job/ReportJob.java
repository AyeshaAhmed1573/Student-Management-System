package com.example.crudApp.job;
import com.example.crudApp.entity.DepartmentReport;
import com.example.crudApp.repository.DepartmentReportRepository;
import com.example.crudApp.repository.StudentDepartmentRepository;
import com.example.crudApp.service.services.EmailService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReportJob extends QuartzJobBean {
    private static final Logger log= LoggerFactory.getLogger(ReportJob.class);

    private final DepartmentReportRepository departmentReportRepository;
    private final StudentDepartmentRepository studentDepartmentRepository;
    private final EmailService emailService;
    private final String reportTo;

    public ReportJob(StudentDepartmentRepository studentDepartmentRepository, DepartmentReportRepository departmentReportRepository,EmailService emailService, @Value("${report.mail.to}") String reportTo ){
        this.studentDepartmentRepository=studentDepartmentRepository;
        this.departmentReportRepository=departmentReportRepository;
        this.emailService=emailService;
        this.reportTo=reportTo;

    }
    @Override
    protected void executeInternal(JobExecutionContext context)throws JobExecutionException{
        LocalDateTime now= LocalDateTime.now();
        List<DepartmentReport> rows= studentDepartmentRepository.countStudentsPerDepartment()
                .stream()
                .map(c->{
                    DepartmentReport r= new DepartmentReport();
                    r.setDepartmentName(c.DepartmentName());
                    r.setStudentCount(c.Total());
                    r.setGeneratedAt(now);
                    return r;
                })
                .toList();
        departmentReportRepository.saveAll(rows);
        log.info("Department report saved: {} departments at {}", rows.size(), now);

        StringBuilder body= new StringBuilder("Department report generated at " + now + "\n\n");
        for(DepartmentReport r:rows){
            body.append(r.getDepartmentName()).append(":")
                    .append(r.getStudentCount()).append(" students\n");
        }
        try{
            emailService.send(reportTo,"department reports ", body.toString());
            log.info("Report email sent to {}", reportTo);
        } catch (MailException e) {
            log.error("Could not send report email", e);
        }
    }



}
