package com.in28minutes.springboot.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue
    private int id;
    private String user;
    @Size(min = 10, message = "Enter at least 10 characters")
    private String desc;
    private Date targetDate;
    private boolean isDone;

    public Todo(String user, String desc, Date targetDate, boolean isDone) {
        this.user = user;
        this.desc = desc;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }
}
