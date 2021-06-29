package com.tamchack.tamchack.domain.store;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class BookmarkKey implements Serializable {

    private String userId;

    private int store;

}