package com.cwhite56.amtrec.mappers;

public interface Mapper<A, B> {
    public B mapTo(A a);
    public A mapFrom(B b);
}
