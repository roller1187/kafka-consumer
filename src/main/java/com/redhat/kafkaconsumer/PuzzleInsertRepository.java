package com.redhat.kafkaconsumer;

// import com.redhat.kafkaconsumer.Puzzle;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface PuzzleInsertRepository extends JpaRepository<Puzzle, String> {
// }

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuzzleInsertRepository extends PagingAndSortingRepository<Puzzle, Long>, 
        JpaSpecificationExecutor<Puzzle> {
}

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//
//import org.json.simple.JSONObject;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class PuzzleInsertRepository {
//	
//	@PersistenceContext
//	EntityManager entityManager;
//	 
//  @Transactional
//  public void insertWithQuery(JSONObject JSONAcrostic) {
//  	
//  	String message = JSONAcrostic.get("message").toString();
//  	String map = JSONAcrostic.get("acrostic").toString();
//  	
//  	String sql = "INSERT INTO sampledb.puzzles " +
//				"(id, message, map) VALUES (?, ?, ?)";
//  	
//  	entityManager.createNativeQuery(sql)
//		  .setParameter(1, 1)
//		  .setParameter(2, message)
//		  .setParameter(3, map)
//		  .executeUpdate();
//  }
//}