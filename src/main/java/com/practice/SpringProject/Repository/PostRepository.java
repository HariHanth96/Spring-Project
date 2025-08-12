package com.practice.SpringProject.Repository;
import com.practice.SpringProject.Model.*;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//repository file are used to interact with the mysql tables all the query functions are done in this file
/*The @Repository annotation in Spring is used to mark a class as a Data Access Object (DAO).
 *  It tells Spring that the class is responsible for interacting with the database, such as fetching, saving,
 *   updating, or deleting data.*/
//@Repository classes are automatically gets marked as @Component

//as this is an interface only method names are mentioned the functionalities are done in PostService
@Repository
public interface PostRepository extends JpaRepository<Posts,Long>{

	Posts findByTitle(String title);
}
/*Method Name					Description
findBy<Field>					Find by exact match
findBy<Field>Containing			LIKE query (partial match)
findBy<Field>StartingWith		Starts with given string
findBy<Field>EndingWith			Ends with given string
findBy<Field>IgnoreCase			Case-insensitive match
findBy<Field>Between			Range between two values
findBy<Field>After / Before		Date or time comparison
findBy<Field>In(List<>)			IN clause
countBy<Field>					Count rows matching the condition
existsBy<Field>					Returns boolean if a match exists
deleteBy<Field>					Deletes all records matching the condition*/