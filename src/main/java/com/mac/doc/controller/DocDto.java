package com.mac.doc.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocDto {
    private String title;
    private String contents;
    private Double version;
    private String menuCd;
}
