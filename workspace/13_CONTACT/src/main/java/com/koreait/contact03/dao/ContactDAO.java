package com.koreait.contact03.dao;

import java.util.List;

import com.koreait.contact03.dto.Contact;

public interface ContactDAO {

	public List<Contact> selectContactList();
	public int insertContact(Contact contact);
	public Contact selectContactView(long no);
	public int updateContact(Contact contact);
	public int deleteContact(long no);
}
