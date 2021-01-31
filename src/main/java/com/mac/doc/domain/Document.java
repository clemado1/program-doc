package com.mac.doc.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mac.doc.domain.type.DocStat;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @Enumerated(EnumType.STRING)
    private DocStat docStat;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "document")
    private Set<ProgramDocument> program = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "doc_sn")
    private DocumentData documentData;

    @Builder
    public Document(DocumentData documentData, DocStat docStat) {
        Assert.notNull(docStat, "docStat must not be null");

        this.documentData = documentData;
        this.docStat = docStat;
    }

}
