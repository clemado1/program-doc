package com.mac.doc.domain;

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
@ToString(exclude = "label")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "docCache")
public class Document extends Base {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private Long docId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DocStat docStat;

    @Column(nullable = false)
    private String title;

    private String contents;

    private Double version = 1.0;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "menu_cd")
    private Menu menu;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "doc_label",
            joinColumns = {@JoinColumn(name = "doc_id")},
            inverseJoinColumns = {@JoinColumn(name = "label_id")}
    )
    private Set<Label> label = new HashSet<>();

    @Builder
    public Document(Menu menu, DocStat docStat, String title, String contents, double version, Set<Label> label) {
        Assert.notNull(menu, "menu must not be empty");
        Assert.notNull(docStat, "docStat must not be null");
        Assert.hasText(title, "title must not be null");

        this.menu = menu;
        this.docStat = docStat;
        this.title = title;
        this.contents = contents;
        this.version = version;
        this.label = label;
    }

}
