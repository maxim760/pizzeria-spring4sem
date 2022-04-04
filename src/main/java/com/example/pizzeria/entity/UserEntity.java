package com.example.pizzeria.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Entity
@Table(name = "users")
@Getter
@Setter
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
    @OneToMany(mappedBy = "toUser")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CertificateEntity> receivedCertificates = new ArrayList<>();
    // подаренные сертификаты
    @OneToMany(mappedBy = "fromUser")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CertificateEntity> donatedCertificates = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<OrderEntity> orders = new ArrayList<>();

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true, cascade = {CascadeType.ALL})
    private AddressEntity address;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) getRoles();
    }

    public void addRole(RoleEntity role) {
        this.roles.add(role);
        role.getUsers().add(this);
    }
    public void addAddress(AddressEntity address) {
        this.address = address;
        address.setUser(this);
    }
    public void addOrder(OrderEntity order) {
        this.orders.add(order);
        order.setUser(this);
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


    public List<CertificateEntity> getDescDonatedCertificates() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss"); //your own date format
        Collections.sort(this.donatedCertificates, new Comparator<CertificateEntity>() {
            @Override
            public int compare(CertificateEntity o1, CertificateEntity o2) {
                try {
                    return simpleDateFormat.parse(o2.getDateCreate()).compareTo(simpleDateFormat.parse(o1.getDateCreate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
        return this.donatedCertificates;
    }

    public List<CertificateEntity> getDescReceivedCertififcates() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss"); //your own date format
        Collections.sort(this.receivedCertificates, new Comparator<CertificateEntity>() {
            @Override
            public int compare(CertificateEntity o1, CertificateEntity o2) {
                try {
                    return simpleDateFormat.parse(o2.getDateCreate()).compareTo(simpleDateFormat.parse(o1.getDateCreate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
        return this.receivedCertificates;
    }

    @Override
    public String toString() {
        return "UserEntity(" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", cash=" + cash +
                ')';
    }
}
