package com.final_project.eduflow.DataAccess;

import com.final_project.eduflow.Data.Entities.Section;
import com.final_project.eduflow.Data.Entities.SectionId;
import com.final_project.eduflow.Data.Entities.TeachingStaff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, SectionId> {
    Section findByAssistantId(Integer assistantId);
    Section findByInstructorId(Integer instructorId);
    Section findByCourseCode(String courseCode);
    Section findBySectionNumber(Integer sectionNumber);
}
