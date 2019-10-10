package controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import common.RestResponse;
import dao.BookDao;
import dto.BookAllDto;
import dto.BookDetailDto;
import dto.BookDto;
import entity.Book;
import service.IBookSvc;

@RestController
@RequestMapping(value="/books")
public class BookController {
	
	@Autowired
	private IBookSvc service;
	
	@Autowired
	private BookDao dao;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public RestResponse findBooks(){
		RestResponse res = new RestResponse();
		try {
			LinkedList<BookAllDto> booksDto = service.findAllBook();
			res.setStatus(1);
			res.setMessage("Get Data Success");
			res.setData(booksDto);
		} catch (Exception e) {
			System.out.println(e);
			res.setStatus(0);
			res.setMessage("Error. Get Data Failed");
		}
		
		return res;
	}
	
	@RequestMapping(value="/find", method= RequestMethod.GET)
	public RestResponse findByParam(@RequestParam(value="param", defaultValue="", required=false) String param){
		RestResponse res = new RestResponse();
		try {
			List<BookAllDto> list = service.findBookByParam(param);
			if(list.size() == 0 ){
				res.setStatus(1);
				res.setMessage("Book not found");
				res.setData(list);	
			} else{
				res.setStatus(1);
				res.setMessage("Get data success");
				res.setData(list);	
			}
		} catch (Exception exception) {
			res.setStatus(0);
			res.setMessage("Error. Get Data Failed");
		}
		return res;
	}
	
	@RequestMapping(value="/findOne", method= RequestMethod.GET)
	public RestResponse findByCode(@RequestParam(value="code", defaultValue="", required=false) long code){
		RestResponse res = new RestResponse();
		try {
			BookDetailDto dto = service.findBookByCode(code);
			if(dto == null ){
				res.setStatus(1);
				res.setMessage("Book not found");
				res.setData(dto);	
			} else{
				res.setStatus(1);
				res.setMessage("Get data success");
				res.setData(dto);	
			}
		} catch (Exception exception) {
			res.setStatus(0);
			res.setMessage("Error. Get Data Failed");
		}
		return res;
	}
	
	@RequestMapping(value="/save", method= RequestMethod.POST)
	public RestResponse insertBook(@RequestBody BookDto dto){
		RestResponse res = new RestResponse();
		try {
			service.save(dto);
			res.setStatus(1);
			res.setMessage("Insert data success");
			res.setData(dto);
		} catch (Exception e) {
			System.out.println(e);
			res.setStatus(0);
			res.setMessage("Failed insert data");
		}
		
		return res;
	}
	
	@RequestMapping(value="/update/{code}", method = RequestMethod.PUT)
	public RestResponse update(@PathVariable long code, @RequestBody BookDto dto){
		RestResponse res = new RestResponse();
		try {
			//dto.setCode(code);
			List<String> listError = validate(dto);
			Book book = dao.findOne(code);
			if(book == null){
				res.setStatus(0);
				res.setMessage("Book with code "+code+" is not found.");
			}else if (listError.size() > 0){
				res.setStatus(0);
				res.setListErrorMessage(listError);
				res.setMessage("Failed Updated Data");
			} else{				
				service.update(dto);
				res.setStatus(1);
				res.setMessage("Update data success");
				BookDetailDto data = service.findBookByCode(code);
				res.setData(data);
			}
		} catch (Exception e) {
			res.setStatus(0);
			res.setMessage("Error. Failed updated data");
		}
		return res;
	}
	
	@RequestMapping(value="/delete/{code}", method = RequestMethod.DELETE)
	public RestResponse delete(@PathVariable long code){
		RestResponse res = new RestResponse();
		try {
			Book book = dao.findOne(code);
			if(book == null){
				res.setStatus(0);
				res.setMessage("Book with code "+code+" is not found.");
			} else{				
				service.delete(code);
				res.setStatus(1);
				res.setMessage("Delete data success");
			}
		} catch (Exception e) {
			res.setStatus(0);
			res.setMessage("Error. Failed delete data");
		}
		return res;
	}
	
	public List<String> validate(BookDto dto){
		List<String> errors = new LinkedList<>();
		if (dto.getAuthor().length() < 5){
			errors.add("Author at least 5 characters");
		} else if(dto.getTitle().length() < 5){
			errors.add("Title at least 5 characters");
		} else if(dto.getPublisher().length()<9){
			errors.add("Publisher at least 9 characters");
		} else if(dto.getDescription().length() < 9){
			errors.add("Description at least 9 characters");
		}else if(dto.getBuyPrice()<=2000){
			errors.add("Buy prica must bigger than 2000");
		}
		return errors;
	}
	
	
}
