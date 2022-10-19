package laptrinh.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import laptrinh.entity.student;

public interface student_repository extends JpaRepository<student, Integer>{
	@Query(value = "select * from student where msv = ?1",nativeQuery = true)
	List<student> findbyMsv(String msv); 
	
	@Query(value = "select * from student order by msv asc",nativeQuery = true)
	List<student> findStudentHoten(Pageable pageable);
	
	@Query(value = "select * from student order by gpa desc",nativeQuery = true)
	List<student> findStudentGpa(Pageable pageable);
	
	@Query(value = "select * from student where hoten like %?1% ",nativeQuery = true)
	List<student> searchStudentName(Pageable pageable,String search);
	
	@Query(value = "select * from student where hoten like %?1% ",nativeQuery = true)
	List<student> searchStudentNameTotal(String search);
	
	@Query(value = "select * from student where msv like %?1% ",nativeQuery = true)
	List<student> searchStudentMsv(Pageable pageable,String search);
	
	@Query(value = "select * from student where msv like %?1% ",nativeQuery = true)
	List<student> searchStudentMsvTotal(String search);
	
}
