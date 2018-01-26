package com.morpheme.POJO;

import lombok.Data;

import java.util.List;

@Data
public class model<E> {
    List<E> data;
    double time;
    List<E> noun;

    public model(List<E> data, List<E> noun, double time) {
        this.data = data;
        this.time = time;
        this.noun = noun;
    }
}
