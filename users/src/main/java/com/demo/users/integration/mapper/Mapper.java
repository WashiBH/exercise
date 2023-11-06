package com.demo.users.integration.mapper;

/**
 * Mapper interface.
 *
 * @param <O> Output param.
 * @param <I> Input param.
 */
public interface Mapper<O, I> {
  O toMap(I in);

}
