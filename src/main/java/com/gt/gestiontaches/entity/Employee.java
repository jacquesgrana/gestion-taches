package com.gt.gestiontaches.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String userName;

    //@JoinTable(name="employee_task")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="employee_task",
            joinColumns= @JoinColumn(name="id_employee", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="id_task", referencedColumnName="id"))
    private List<Task> tasks;

}
