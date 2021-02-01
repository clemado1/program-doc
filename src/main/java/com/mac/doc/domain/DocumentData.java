package com.mac.doc.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @JsonIgnore
    private Document document;

    @Column(nullable = false)
    private String title;

    private String contents;

    private Double version = 1.0;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "doc_label",
            joinColumns = {@JoinColumn(name = "doc_sn")},
            inverseJoinColumns = {@JoinColumn(name = "label_id")}
    )
    private Set<Label> label = new HashSet<>();

    @Builder
    public DocumentData(String title, String contents, Double version, Set<Label> label) {
        Assert.notNull(title, "title must not be null");

        this.title = title;
        this.contents = contents;
        this.version = version;
    }
}
