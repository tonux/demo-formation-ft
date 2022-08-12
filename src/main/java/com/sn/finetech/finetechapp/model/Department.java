package com.sn.finetech.finetechapp.model;

import javax.persistence.*;
import java.util.Set;

/*
@Table(name="department_table", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"code"})
})

 */
@Entity

public class Department {
    // name, description
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String code;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private Set<Person> persons;

    public Department() {
    }

    public Department(String name, String description, String code) {
        this.name = name;
        this.description = description;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
