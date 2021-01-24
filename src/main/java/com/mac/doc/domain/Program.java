package com.mac.doc.domain;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "program")
@Getter
@ToString(exclude = {"documents", "chckUser"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Program extends Base {
    @Id
    @Column(name = "program_cd")
    private String programCd;

    private String programNm;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chck_user_id", referencedColumnName = "user_id")
    private User chckUser;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "program")
    private Set<Document> documents = new HashSet<>();

    @Builder
    public Program(String programCd, String programNm) {
        this.programCd = programCd;
        this.programNm = programNm;
    }
}
