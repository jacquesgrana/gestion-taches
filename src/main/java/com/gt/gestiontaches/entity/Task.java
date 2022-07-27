package com.gt.gestiontaches.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TASK")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private int it;

    private int rt;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy="tasks")
    private List<Employee> employees;

    public Task() {
        if (this.employees == null) {
            this.employees = new ArrayList<>();
        }
    }

    public Task(Long id, String title, String description, int it, int rt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.it = it;
        this.rt = rt;

        if (this.employees == null) {
            this.employees = new ArrayList<>();
        }
    }

    public Task(Long id, String title, String description, int it, int rt, List<Employee> employees) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.it = it;
        this.rt = rt;
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIt() {
        return it;
    }

    public void setIt(int it) {
        this.it = it;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
