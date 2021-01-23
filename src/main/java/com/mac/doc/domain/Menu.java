package com.mac.doc.domain;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doc_menu")
@Getter
@ToString(exclude = "document")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends Base {
    @Id
    @Column(name = "menu_cd")
    private String menuCd;

    private String menuNm;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chck_user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    private List<Document> document = new ArrayList<>();

    @Builder
    public Menu(String menuCd, String menuNm) {
        this.menuCd = menuCd;
        this.menuNm = menuNm;
    }
}
