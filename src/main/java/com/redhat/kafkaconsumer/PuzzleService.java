package com.redhat.kafkaconsumer;

import com.redhat.kafkaconsumer.Puzzle;
import com.redhat.kafkaconsumer.PuzzleInsertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class PuzzleService {
    
    @Autowired
    private PuzzleInsertRepository puzzleRepository;
    
    public Puzzle save(Puzzle puzzle) {
        return puzzleRepository.save(puzzle);
    }
    
    public void deleteById(Long id) {
    	puzzleRepository.deleteById(id);
    }
    
    public Long count() {
        return puzzleRepository.count();
    }
}