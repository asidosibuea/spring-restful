package service;

import java.util.LinkedList;

import dto.BookAllDto;
import dto.BookDetailDto;
import dto.BookDto;

public interface IBookSvc {
	
	public LinkedList<BookAllDto> findAllBook();
	public LinkedList<BookAllDto> findBookByParam(String param);
	public BookDetailDto findBookByCode(long code);
	public void save(BookDto dto);
	public void update(BookDto dto);
	public void delete(long code);

}
