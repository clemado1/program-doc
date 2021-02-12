package com.mac.doc.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "document")
@Getter
@ToString(exclude = {"program"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "docCache")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "docId")
public class Document extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private Long docId;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "program_cd")
    private Program program = new Program();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doc_sn")
    private DocumentData documentData;

    public void setDocumentData(DocumentData documentData) {
        this.documentData = documentData;
    }

    @Builder
    public Document(Long docId, Program program, DocumentData documentData, String title) {
        this.docId = docId;
        this.program = program;
        this.documentData = documentData;
        this.title = title;
    }

}
