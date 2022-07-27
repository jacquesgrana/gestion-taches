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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;


    @Column(name = "USERNAME", unique = true)
    private String userName;

    //@JoinTable(name="employee_task")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="EMPLOYEE_TASK",
            joinColumns= @JoinColumn(name="EMPLOYEE_ID", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="TASK_ID", referencedColumnName="id"))
    private List<Task> tasks;

}
