package com.gt.gestiontaches.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Getter(value = AccessLevel.NONE)
    @Column(name = "USERNAME", unique = true)
    private String userName;

    @Getter(value = AccessLevel.NONE)
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ENABLED")
    private Boolean enabled;

    //@JoinTable(name="employee_task")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="EMPLOYEE_TASK",
            joinColumns= @JoinColumn(name="EMPLOYEE_ID", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="TASK_ID", referencedColumnName="id"))
    private List<Task> tasks = new ArrayList<>();;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="EMPLOYEE_ROLE",
            joinColumns= @JoinColumn(name="EMPLOYEE_ID", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="ROLE_ID", referencedColumnName="id"))
    private List<Role> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getLabel()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.enabled;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
