package com.fastcampus.projectboardadmin.config;


import static org.mockito.BDDMockito.given;

import com.fastcampus.projectboardadmin.service.PageLoadCounterService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

@TestConfiguration
public class GlobalControllerConfig {

  @MockBean private PageLoadCounterService pageLoadCounterService;

  @BeforeTestMethod
  public void securitySetUp() {
    given(pageLoadCounterService.pageLoadCount()).willReturn(0L);
  }

}
