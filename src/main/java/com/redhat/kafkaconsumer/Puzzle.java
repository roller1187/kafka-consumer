package com.redhat.kafkaconsumer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class Puzzle {
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @NotBlank
    @Column
    private String message;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String map;
    
    public String getMessage() {
		return message;
    }
    public void setMessage(String message) {
		this.message = message;
	}
    public String getMap() {
		return map;
    }
    public void setMap(String map) {
		this.map = map;
    }
    public long getId() {
		return id;
    }
    public void setId(Long id) {
		this.id = id;
	}
}