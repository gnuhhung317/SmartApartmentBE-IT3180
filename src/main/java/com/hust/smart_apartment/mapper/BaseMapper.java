package com.hust.smart_apartment.mapper;

public abstract class BaseMapper<A,B,C> {
    public abstract B entityToResponse(C c);
    public abstract C requestToEntity(A a);
}
