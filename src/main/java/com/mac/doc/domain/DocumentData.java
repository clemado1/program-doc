package com.mac.doc.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mac.doc.domain.type.DocStat;
import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "document_data")
@Getter
@ToString(exclude = {"document", "label"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "docSn")
public class DocumentData extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_sn")
    private Long docSn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_id")
    private Document document;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DocStat docStat;

    @Lob
    private String contents;

    private Double version = 1.0;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "doc_label",
            joinColumns = {@JoinColumn(name = "doc_sn")},
            inverseJoinColumns = {@JoinColumn(name = "label_id")}
    )
    private Set<Label> label = new HashSet<>();

    @Builder
    public DocumentData(Long docSn, Document document, DocStat docStat, String contents, Double version, Set<Label> label) {
        Assert.notNull(docStat, "docStat must not be null");

        this.docSn = docSn;
        this.document = document;
        this.docStat = docStat;
        this.contents = contents;
        this.version = version;

    }
}
