package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entity.Book;

public interface BookDao extends JpaRepository<Book, Long> {
	
	@Query("select b.title, b.author, b.description, b.genre, b.buyDate, b.buyPrice from Book b where b.title like %:param% or b.author like %:param% or b.description like %:param%")
	public List<Book> findAllBookByParam(@Param("param") String param);

	@Query("select b.title, b.author, b.description, b.genre, b.buyDate, b.buyPrice from Book b ")
	public List<Book> findAllBook();

}
