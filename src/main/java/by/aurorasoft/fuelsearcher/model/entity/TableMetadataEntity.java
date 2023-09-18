package by.aurorasoft.fuelsearcher.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

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
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tableName")
    private String tableName;

    @OneToMany(mappedBy = "tableMetadata", fetch = LAZY)
    @ToString.Exclude
    private List<PropertyMetadataEntity> propertiesMetadata;
}
