package com.cydeo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_account")
public class User extends BaseEntity{

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    private String username;

    @OneToOne(fetch = FetchType.LAZY) //FetchType.LAZY will create hibernateLazyInitializer property inside Account object. We use @JsonIgnoreProperties to ignore it.
    @JoinColumn(name = "account_details_id")
    @JsonManagedReference //is the forward part of reference - the one that gets serialized normally
    private Account account;

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
