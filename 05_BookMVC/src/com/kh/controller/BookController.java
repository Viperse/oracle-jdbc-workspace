package com.kh.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.model.dao.BookDAO;
import com.kh.model.vo.Book;
import com.kh.model.vo.Member;
import com.kh.model.vo.Rent;

public class BookController {
	
	private BookDAO dao = new BookDAO();
	private Member member = new Member();
	
	public ArrayList<Book> printBookAll() {
		try {
			return dao.printBookAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean registerBook(Book book) {
		try {
			if(dao.registerBook(book) != 0) {
				dao.registerBook(book);
				return true;
			}					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean sellBook(int no) {
		try {
			if(dao.sellBook(no) != 0) {
				dao.sellBook(no);
				return true;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean registerMember(Member member) {
		try {
			if(dao.registerMember(member) != 0) {
				dao.registerMember(member);
				return true;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Member login(String id, String password) {
		try {
			if(dao.login(id, password) != null) {
				member = dao.login(id, password);
				return member;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteMember() {
		try {
			if(dao.deleteMember(member.getMemberId(), member.getMemberPwd()) != 0) {
				dao.deleteMember(member.getMemberId(), member.getMemberPwd());
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean rentBook(int no) {
		
		try {
			Rent rent = new Rent();
			Book book = new Book();
			book.setBkNo(no);
			rent.setBook(book);
			rent.setMember(member);
			if(dao.rentBook(rent) != 0) {
				dao.rentBook(rent);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteRent(int no) {
		
		try {
			if(dao.deleteRent(no) != 0) {
				dao.deleteRent(no);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Rent> printRentBook() {
		try {			
			return dao.printRentBook(member.getMemberId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
