package com.mac.doc.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mac.doc.domain.type.FunctionType;
import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "function")
@Getter
@ToString(exclude = {"documents", "holdUser"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "functionCd")
public class Function extends Base {
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "function")
    private final Set<Document> documents = new HashSet<>();

    @Id
    @Column(name = "function_cd")
    private String functionCd;

    private String functionNm;

    private FunctionType functionType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hold_user_id", referencedColumnName = "user_id")
    private User holdUser;

    @Builder
    public Function(String functionCd, String functionNm, FunctionType functionType) {
        Assert.notNull(functionCd, "functionCd must not be null");

        this.functionCd = functionCd;
        this.functionNm = functionNm;
        this.functionType = functionType;
    }
}
