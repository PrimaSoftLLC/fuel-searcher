package com.aurorasoft.fuelsearcher.crud.model.entity;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "property_metadata")
@TypeDef(
        name = "string-array",
        typeClass = StringArrayType.class
)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class PropertyMetadataEntity extends BaseEntity<Long> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = SEQUENCE, generator = "property_metadata_id_seq")
    @SequenceGenerator(name = "property_metadata_id_seq", sequenceName = "property_metadata_id_seq")
    private Long id;

    @Column(name = "property_name")
    private String propertyName;

    @Type(type = "string-array")
    @Column(name = "allowable_values", columnDefinition = "TEXT[]")
    private String[] allowableValues;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "table_metadata_id")
    @ToString.Exclude
    private TableMetadataEntity tableMetadata;
}
