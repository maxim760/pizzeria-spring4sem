package com.example.pizzeria.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;


@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique = true)
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String password;
    @Email
    private String email;
    private String phone;
    private Long cash;

    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> roles = new HashSet<>();

    // полученные сертификаты
    @OneToMany(mappedBy = "toUser", fetch = FetchType.EAGER)
    private List<CertificateEntity> receivedCertificates = new ArrayList<>();

    // подаренные сертификаты
    @OneToMany(mappedBy = "fromUser", fetch = FetchType.EAGER)
    private List<CertificateEntity> donatedCertificates = new ArrayList<>();

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    private AddressEntity address;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) getRoles();
    }

    public void addRole(RoleEntity role) {
        this.roles.add(role);
        role.getUsers().add(this);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
