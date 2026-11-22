package com.example.crudApp.repository;

import com.example.crudApp.entity.DepartmentReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentReportRepository extends JpaRepository<DepartmentReport,Long> {
}
