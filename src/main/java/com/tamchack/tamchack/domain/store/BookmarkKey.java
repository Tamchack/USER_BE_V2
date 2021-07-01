package com.tamchack.tamchack.domain.store;

import com.tamchack.tamchack.domain.member.User;
import lombok.Builder;

import java.io.Serializable;

@Builder
public class BookmarkKey implements Serializable {

    private String user;

    private int store;

}