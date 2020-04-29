package com.redhat.kafkaconsumer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Puzzle {

//	public Puzzle(String message, String map) {
//        this.message = message;
//        this.map = map;
//    }
//	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String message;

    @NotBlank
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
    
//    public Puzzle(String message, String map) {
//        this.message = message;
//        this.map = map;
//    }
//
//    @Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
//    private Long id;
//
//    private String message;
//
//    private String map;
//    
//    public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	public String getMap() {
//		return map;
//	}
//
//	public void setMap(String map) {
//		this.map = map;
//	}
}