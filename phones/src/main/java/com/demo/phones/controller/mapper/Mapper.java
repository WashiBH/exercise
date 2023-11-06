package com.demo.phones.controller.mapper;

public interface Mapper <O, I>{
  public O toMap(I in);
}
