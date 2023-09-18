package by.aurorasoft.fuelsearcher.model.entity;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

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
@ToString(callSuper = true)
@Builder
public class PropertyMetadataEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
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
