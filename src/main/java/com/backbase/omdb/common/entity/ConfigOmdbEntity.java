package com.backbase.omdb.common.entity;

import com.backbase.omdb.common.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

/**
 * Configuration OMDB entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@DynamicUpdate
@Entity
@Table(name = "config_omdb")
public class ConfigOmdbEntity {

    @Id
    private int id;

    @Column(insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(insertable = false)
    private boolean enable;

    @CreationTimestamp
    private java.sql.Timestamp createdAt;

    @UpdateTimestamp
    private java.sql.Timestamp updatedAt;

}
