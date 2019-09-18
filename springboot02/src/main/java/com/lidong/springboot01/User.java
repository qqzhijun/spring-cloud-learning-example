package com.lidong.springboot01;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class User {
  private Long id;
  private Long providerId;
  private String name;
}