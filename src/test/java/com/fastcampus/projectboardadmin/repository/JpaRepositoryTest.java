package com.fastcampus.projectboardadmin.repository;


import static org.assertj.core.api.Assertions.assertThat;

import com.fastcampus.projectboardadmin.domain.AdminAccount;
import com.fastcampus.projectboardadmin.domain.constant.RoleType;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@DisplayName("JPA 연결 테스트")
@Import(JpaRepositoryTest.TestJpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

  private final AdminAccountRepository adminAccountRepository;

  public JpaRepositoryTest(@Autowired AdminAccountRepository adminAccountRepository) {
    this.adminAccountRepository = adminAccountRepository;
  }

  @DisplayName("회원 정보 select 테스트")
  @Test
  void givenAdminAccount_whenSelecting_thenWorksFine() {

    // Given

    // When
    List<AdminAccount> adminAccounts = adminAccountRepository.findAll();

    // Then

    assertThat(adminAccounts)
        .isNotNull()
        .hasSize(4);

  }

  @DisplayName("회원 정보 insert 테스트")
  @Test
  void givenAdminAccount_whenInsering_thenWorksFine() {

    // Given
    long previousCount = adminAccountRepository.count();

    AdminAccount adminAccount = AdminAccount.of("test", "pw", Set.of(RoleType.MANAGER), null, null, null, "test");
    // When
    adminAccountRepository.save(adminAccount);

    // Then

    assertThat(adminAccountRepository.count()).isEqualTo(5);


  }

  @DisplayName("회원 정보 update 테스트")
  @Test
  void givenAdminAccountAndRoleType_whenUpdating_thenWorksFine() {

    // Given
    AdminAccount adminAccount = adminAccountRepository.getReferenceById("admin1");
    adminAccount.addRoleType(RoleType.DEVELOPER);
    adminAccount.addRoleTypes(List.of(RoleType.USER, RoleType.USER));
    adminAccount.removeRoleType(RoleType.ADMIN);

    // When
    AdminAccount updatedAccount = adminAccountRepository.saveAndFlush(adminAccount);

    // Then

    assertThat(updatedAccount)
        .hasFieldOrPropertyWithValue("userId", "admin1")
        .hasFieldOrPropertyWithValue("roleTypes", Set.of(RoleType.DEVELOPER, RoleType.USER));


  }

  @DisplayName("회원 정보 delete 테스트")
  @Test
  void givenAdminAccount_whenDeleting_thenWorksFine() {

    // Given
    long previousCount = adminAccountRepository.count();

    AdminAccount adminAccount = AdminAccount.of("test", "pw", Set.of(RoleType.MANAGER), null, null, null, "test");
    // When
    adminAccountRepository.save(adminAccount);

    // Then

    assertThat(adminAccountRepository.count()).isEqualTo(5);


  }

  @EnableJpaAuditing
  @TestConfiguration
  static class TestJpaConfig {

    AuditorAware<String> auditorAware() {
      return () -> Optional.of("test");
    }
  }

}

