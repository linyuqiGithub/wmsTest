package com._520it.wms.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter@Getter
public class BaseDomain implements Serializable{
  @ObjectProp("编号")
  private Long id;
}
