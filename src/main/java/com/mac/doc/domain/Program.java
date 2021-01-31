package com.mac.doc.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "program")
@Getter
@ToString(exclude = {"documents", "picUser"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "programCd")
public class Program extends Base {
    @Id
    @Column(name = "program_cd")
    private String programCd;

    private String programNm;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pic_user_id", referencedColumnName = "user_id")
    private User picUser;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "program")
    private final Set<ProgramDocument> documents = new HashSet<>();

    @Builder
    public Program(String programCd, String programNm) {
        this.programCd = programCd;
        this.programNm = programNm;
    }
}
