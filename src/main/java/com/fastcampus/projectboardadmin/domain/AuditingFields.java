package com.fastcampus.projectboardadmin.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@ToString

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditingFields {

  /** 생성일시 */
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @CreatedDate
  @Column(nullable = false, updatable = false)
  protected LocalDateTime createdAt;

  /** 생성자 */
  @CreatedBy
  @Column(nullable = false, length = 100, updatable = false)
  protected String createdBy;

  /** 수정일시 */
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @LastModifiedDate
  @Column(nullable = false)
  protected LocalDateTime modifiedAt;

  /** 수정자 */
  @LastModifiedBy
  @Column(nullable = false, length = 100)
  protected String modifiedBy;

}
