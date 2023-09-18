package by.aurorasoft.fuelsearcher.model.entity;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "table_metadata")
@NoArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)
public class PropertyMetadataEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "property_name")
    private String propertyName;

    @Type(StringArrayType.class)
    @Column(name = "allowable_values")
    private String[] allowableValues;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "table_metadata_id")
    @ToString.Exclude
    private TableMetadataEntity tableMetadata;
}
