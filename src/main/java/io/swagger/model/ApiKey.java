package io.swagger.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ApiKey {
    @Id
    private String key;

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    public ApiKey(String key)
    {
        this.key = key;
    }
}
