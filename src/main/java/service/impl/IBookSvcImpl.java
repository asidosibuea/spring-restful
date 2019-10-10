package service.impl;

import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BookDao;
import dto.BookAllDto;
import dto.BookDetailDto;
import dto.BookDto;
import entity.Book;
import service.IBookSvc;

@Transactional
@Service
public class IBookSvcImpl implements IBookSvc {

	@Autowired
	private BookDao dao;

	@Override
	public LinkedList<BookAllDto> findAllBook() {
		List<Book> bookEntities = dao.findAllBook();
		LinkedList<BookAllDto> listBook = new LinkedList<>();
		for (Book books : bookEntities) {
			BookAllDto dto = new BookAllDto();
			dto.setTitle(books.getTitle());
			dto.setAuthor(books.getAuthor());
			dto.setDescription(books.getDescription());
			dto.setGenre(books.getGenre());
			dto.setBuyDate(books.getBuyDate());
			dto.setBuyPrice(books.getBuyPrice());
			listBook.add(dto);
		}
		return listBook;
	}

	@Override
	public LinkedList<BookAllDto> findBookByParam(String param) {
		List<Book> bookEntities = dao.findAllBookByParam(param);
		LinkedList<BookAllDto> listBook = new LinkedList<>();
		for (Book books : bookEntities) {
			BookAllDto dto = new BookAllDto();
			dto.setTitle(books.getTitle());
			dto.setAuthor(books.getAuthor());
			dto.setDescription(books.getDescription());
			dto.setGenre(books.getGenre());
			dto.setBuyDate(books.getBuyDate());
			dto.setBuyPrice(books.getBuyPrice());
			listBook.add(dto);
		}
		return listBook;
	}

	@Override
	public BookDetailDto findBookByCode(long code) {
		Book book = dao.findOne(code);
		BookDetailDto dto = new BookDetailDto();
		dto.setCode(book.getCode());
		dto.setTitle(book.getTitle());
		dto.setDescription(book.getDescription());
		dto.setAuthor(book.getAuthor());
		dto.setPublisher(book.getPublisher());
		dto.setLanguage(book.getLanguage());
		dto.setGenre(book.getGenre());
		dto.setLocation(book.getLocation());
		dto.setBuyDate(book.getBuyDate());
		dto.setBuyPrice(book.getBuyPrice());

		return dto;
	}

	@Override
	public void save(BookDto dto) {
		Book book = new Book();
		book.setDescription(dto.getDescription());
		book.setAuthor(dto.getAuthor());
		book.setPublisher(dto.getPublisher());
		book.setLanguage(dto.getLanguage());
		book.setGenre(dto.getGenre().charAt(0));
		book.setLocation(dto.getLocation());
		book.setBuyDate(dto.getBuyDate());
		book.setBuyPrice(dto.getBuyPrice());
		book.setTitle(dto.getTitle());
		book.setCode(dto.getCode());

		dao.save(book);
	}

	@Override
	public void update(BookDto dto) {
		Book book = dao.findOne(dto.getCode());
		
		book.setDescription(dto.getDescription());
		book.setAuthor(dto.getAuthor());
		book.setPublisher(dto.getPublisher());
		book.setLanguage(dto.getLanguage());
		book.setGenre(dto.getGenre().charAt(0));
		book.setLocation(dto.getLocation());
		book.setBuyDate(dto.getBuyDate());
		book.setBuyPrice(dto.getBuyPrice());
		book.setTitle(dto.getTitle());
		dao.save(book);
	}

	@Override
	public void delete(long code) {
		dao.delete(code);

	}

}
