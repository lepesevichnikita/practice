package org.klaster.model.context;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.klaster.model.controller.EmployerProfile;
import org.klaster.model.entity.FreelancerProfile;
import org.klaster.model.entity.Skill;
import org.klaster.model.state.job.JobState;
import org.klaster.model.state.job.PublishedJobState;
import org.klaster.service.FreelancersRecommendationService;

/**
 * Job
 *
 * @author Nikita Lepesevich
 */

public class Job extends AbstractContext<JobState> {

  private final EmployerProfile employerProfile;
  private String description;
  private Set<Skill> skills;
  private LocalDateTime endDateTime;

  public Job(String description, EmployerProfile employerProfile, LocalDateTime endDateTime) {
    this.description = description;
    this.employerProfile = employerProfile;
    this.endDateTime = endDateTime;
    this.setCurrentState(new PublishedJobState(this));
  }

  public Set<Skill> getSkills() {
    return skills;
  }

  public void setSkills(Set<Skill> skills) {
    this.skills = skills;
  }

  public EmployerProfile getEmployerProfile() {
    return employerProfile;
  }

  public String getDescription() {
    return description;
  }


  public LocalDateTime getEndDateTime() {
    return endDateTime;
  }

  public void setEndDateTime(LocalDateTime endDateTime) {
    this.endDateTime = endDateTime;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<FreelancerProfile> getRecommendedFreelancerProfiles(long limit) {
    return FreelancersRecommendationService.getInstance()
                                           .getRecommended(this, limit);
  }
}
