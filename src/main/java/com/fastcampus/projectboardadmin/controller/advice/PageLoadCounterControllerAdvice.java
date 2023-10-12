package com.fastcampus.projectboardadmin.controller.advice;

import com.fastcampus.projectboardadmin.service.PageLoadCounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@RequiredArgsConstructor
@ControllerAdvice
public class PageLoadCounterControllerAdvice {

  private final PageLoadCounterService pageLoadCounterService;

  @ModelAttribute("pageLoadCount")
  public Long pageLoadCount() {
    return pageLoadCounterService.pageLoadCount();
  }


}
