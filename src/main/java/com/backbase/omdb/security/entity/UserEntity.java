package com.backbase.omdb.security.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@DynamicUpdate
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    private int id;

    private String username;

    private String password;

    @CreationTimestamp
    private java.sql.Timestamp createdAt;

}
