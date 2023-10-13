package com.fastcampus.projectboardadmin.config;


import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.fastcampus.projectboardadmin.domain.constant.RoleType;
import com.fastcampus.projectboardadmin.dto.AdminAccountDto;
import com.fastcampus.projectboardadmin.service.AdminAccountService;
import java.util.Optional;
import java.util.Set;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

@Import(SecurityConfig.class)
@Configuration
public class TestSecurityConfig {

  @MockBean private AdminAccountService adminAccountService;

  @BeforeTestMethod
  public void securitySetUp() {
    given(adminAccountService.searchUser(anyString()))
        .willReturn(Optional.of(createAdminAccountDto()));
    given(adminAccountService.saveUser(anyString(), anyString(), anySet(), anyString(), anyString(), anyString()))
        .willReturn(createAdminAccountDto());
  }

  private AdminAccountDto createAdminAccountDto() {
    return AdminAccountDto.of(
        "jackieTest",
        "pw",
        Set.of(RoleType.USER),
        "jackie-test@email.com",
        "jackie-test",
        "test-memo"
    );
  }


}
