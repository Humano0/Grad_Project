package com.final_project.eduflow.DataAccess;

import com.final_project.eduflow.Data.Entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Integer> {
}
