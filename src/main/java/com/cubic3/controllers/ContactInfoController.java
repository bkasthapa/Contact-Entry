package com.cubic3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubic3.dtos.ContactInfoDto;
import com.cubic3.services.ContactInfoService;

@RestController
@RequestMapping(value = "/custinfo")
public class ContactInfoController {

	@Autowired
	private ContactInfoService contactInfoService;

	@PostMapping
	public void createContact(@RequestBody ContactInfoDto contactInfoDto) {
		contactInfoService.createContactInfo(contactInfoDto);

	}

	@GetMapping(value = "/{id}")
	public ContactInfoDto getContacts(@PathVariable("id") int id) {
		return contactInfoService.getContact(id);
	}
}