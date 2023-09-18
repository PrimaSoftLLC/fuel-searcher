package by.aurorasoft.fuelsearcher.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "table_metadata")
@NoArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)
public class TableMetadataEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tableName")
    private String tableName;

    @OneToMany(mappedBy = "tableMetadata", fetch = LAZY)
    @ToString.Exclude
    private List<PropertyMetadataEntity> propertiesMetadata;
}
