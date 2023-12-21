package com.grpcflix.user.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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
