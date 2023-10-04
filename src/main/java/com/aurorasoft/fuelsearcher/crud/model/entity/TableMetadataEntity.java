package com.aurorasoft.fuelsearcher.crud.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "table_metadata")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)
@Builder
public class TableMetadataEntity extends BaseEntity<Long> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = SEQUENCE, generator = "table_metadata_id_seq")
    @SequenceGenerator(name = "table_metadata_id_seq", sequenceName = "table_metadata_id_seq")
    private Long id;

    @Column(name = "tableName")
    private String tableName;

    @OneToMany(mappedBy = "tableMetadata", fetch = LAZY)
    @ToString.Exclude
    private List<PropertyMetadataEntity> propertiesMetadata;
}
