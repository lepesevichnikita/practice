package org.klaster.model.state.job;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import org.klaster.builder.DefaultJobBuilder;
import org.klaster.builder.JobBuilder;
import org.klaster.model.context.Job;
import org.klaster.model.entity.Skill;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/*
 * practice
 *
 * 11.02.2020
 *
 */

/**
 * PublishedJobStateTest
 *
 * @author Nikita Lepesevich
 */

public class PublishedJobStateTest {

  private Job job;

  private static final String NEW_DESCRIPTION = "New description";
  private static final String NEW_SKILL_NAME = "New skill";
  private static LocalDateTime newEndDateTime = LocalDateTime.now();

  @BeforeMethod
  public void initialize() {
    final JobBuilder defaultJobBuilder = new DefaultJobBuilder();
    job = defaultJobBuilder.build();
  }

  @Test
  public void jobCantBeRuined() {
    assertThat(job.getCurrentState()
                  .isOverDeadlines(), is(false));
  }

  @Test
  public void canUpdateJob() {
    final Set<Skill> newSkills = new LinkedHashSet<>();
    newSkills.add(new Skill(NEW_SKILL_NAME));
    job.getCurrentState()
       .updateJob(NEW_DESCRIPTION, newSkills, newEndDateTime);
    assertThat(job, allOf(
        hasProperty("endDateTime", equalTo(newEndDateTime)),
        hasProperty("description", equalTo(NEW_DESCRIPTION)),
        hasProperty("skills", equalTo(newSkills))
    ));
  }
}