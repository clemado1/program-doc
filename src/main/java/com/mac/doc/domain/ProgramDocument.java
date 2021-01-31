package com.mac.doc.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "prodocId")
public class ProgramDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prodoc_id")
    private Long prodocId;

    @ManyToOne
    @JoinColumn(name = "program_cd")
    private Program program;

    @ManyToOne
    @JoinColumn(name = "doc_id")
    private Document document;

    @Builder
    public ProgramDocument(Program program, Document document) {
        this.program = program;
        this.document = document;
    }
}
