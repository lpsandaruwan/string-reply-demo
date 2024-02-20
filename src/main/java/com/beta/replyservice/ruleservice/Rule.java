package com.beta.replyservice.ruleservice;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private int index;

    @Column(unique = false, nullable = false)
    private String operation;

    @Column(nullable = true)
    private String description;
}
