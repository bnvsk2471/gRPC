package com.grpcflix.user.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="USER_TABLE")
public class User {

    @Id
    private String login;
    private String name;
    private String genre;
}
