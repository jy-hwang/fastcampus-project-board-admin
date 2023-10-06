package com.fastcampus.projectboardadmin.domain.converter;

import com.fastcampus.projectboardadmin.domain.constant.RoleType;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RoleTypesConverter implements AttributeConverter<Set<RoleType>, String> {

  private static final String DELIMETER = ",";

  @Override
  public String convertToDatabaseColumn(Set<RoleType> attribute) {

    return attribute.stream().map(RoleType::name).sorted().collect(Collectors.joining(DELIMETER));
  }

  @Override
  public Set<RoleType> convertToEntityAttribute(String dbData) {
    return Arrays.stream(dbData.split(DELIMETER)).map(RoleType::valueOf).collect(Collectors.toSet());
  }

}
