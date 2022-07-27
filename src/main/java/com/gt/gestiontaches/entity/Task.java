package com.gt.gestiontaches.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TASK")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION", columnDefinition = "text")
    private String description;

    @Column(name = "INITIAL_TIME", columnDefinition = "decimal")
    private int it;

    @Column(name = "REMAINING_TIME", columnDefinition = "decimal")
    private int rt;

   //@ManyToMany(fetch = FetchType.EAGER, mappedBy="tasks")
   //private List<Employee> employees;

}
