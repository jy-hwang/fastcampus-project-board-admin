package com.fastcampus.projectboardadmin.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.fastcampus.projectboardadmin.config.SecurityConfig;
import com.fastcampus.projectboardadmin.domain.constant.RoleType;
import com.fastcampus.projectboardadmin.dto.ArticleDto;
import com.fastcampus.projectboardadmin.dto.UserAccountDto;
import com.fastcampus.projectboardadmin.service.ArticleManagementService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("컨트롤러 - 게시글 관리")
@Import(SecurityConfig.class)
@WebMvcTest(ArticleManagementController.class)
class ArticleManagementControllerTest {

  private final MockMvc mvc;

  @MockBean
  private ArticleManagementService articleManagementService;

  public ArticleManagementControllerTest(@Autowired MockMvc mvc) {
    this.mvc = mvc;
  }

  @DisplayName(("[view][GET] 게시글 관리 페이지 - 정상 호출"))
  @Test
  void givenNothing_whenRequestingArticleManagementView_thenReturns() throws Exception {
    // Given
    given(articleManagementService.getArticles()).willReturn(List.of());

    // When & Then
    mvc.perform(get("/management/articles"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
        .andExpect(view().name("management/articles"))
        .andExpect(model().attribute("atticles", List.of()));

    then(articleManagementService).should().getArticles();

  }

  @DisplayName("[data][GET] 게시글 1개 - 정상 호출")
  @Test
  void givenArticleId_whenRequestingArticle_thenReturnsArticle() throws Exception {
    // Given
    Long articleId = 1L;
    ArticleDto articleDto = createArticleDto("title", "content");
    given(articleManagementService.getArticle(articleId)).willReturn(articleDto);

    // When & Then
    mvc.perform(get("/management/articles/" + articleId))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(articleId))
        .andExpect(jsonPath("$.title").value(articleDto.title()))
        .andExpect(jsonPath("$.content").value(articleDto.content()))
        .andExpect(jsonPath("$.userAccount.nickname").value(articleDto.userAccount().nickname()));
    then(articleManagementService).should().getArticle(articleId);
  }

  @DisplayName("[view][POST] 게시글 삭제 - 정상 호출")
  @Test
  void givenArticleId_whenRequestingDeletion_thenRedirectsToArticleManagementView() throws Exception {
    // Given
    Long articleId = 1L;
    willDoNothing().given(articleManagementService).deleteArticle(articleId);

    // When & Then
    mvc.perform(
            post("/management/articles/" + articleId)
                .with(csrf())
        )
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/management/articles"))
        .andExpect(redirectedUrl("/management/articles"));
    then(articleManagementService).should().deleteArticle(articleId);
  }


  private ArticleDto createArticleDto(String title, String content) {
    return ArticleDto.of(
        1L,
        createUserAccountDto(),
        title,
        content,
        null,
        LocalDateTime.now(),
        "admin1",
        LocalDateTime.now(),
        "admin1"
    );
  }

  private UserAccountDto createUserAccountDto() {
    return UserAccountDto.of(
        "jackieTest",
        "jackie-test@email.com",
        "jackie-test",
        "test memo"
    );
  }

}
