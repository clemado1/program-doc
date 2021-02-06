package com.mac.doc.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mac.doc.domain.type.ProgramType;
import lombok.*;
import org.springframework.util.Assert;

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

    private ProgramType programType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pic_user_id", referencedColumnName = "user_id")
    private User picUser;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "program")
    private Set<Document> documents = new HashSet<>();

    @Builder
    public Program(String programCd, String programNm, ProgramType programType) {
        Assert.notNull(programCd, "programCd must not be null");
        Assert.notNull(programNm, "programNm must not be null");
        Assert.notNull(programType, "programType must not be null");

        this.programCd = programCd;
        this.programNm = programNm;
        this.programType = programType;
    }
}
