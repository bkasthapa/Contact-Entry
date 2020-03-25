package com.cubic3.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubic3.dtos.AddressDto;
import com.cubic3.dtos.ContactInfoDto;
import com.cubic3.dtos.NameDto;
import com.cubic3.dtos.PhoneDto;
import com.cubic3.entities.AddressEntity;
import com.cubic3.entities.ContactInfoEntity;
import com.cubic3.entities.NameEntity;
import com.cubic3.entities.PhoneEntity;
import com.cubic3.repositories.ContactInfoRepository;

@Service
public class ContactInfoService {

	@Autowired
	private ContactInfoRepository contactInfoRepository;

	public void createContactInfo(ContactInfoDto contactInfoDto) {

		ContactInfoEntity contactInfoEntity = new ContactInfoEntity();
		contactInfoEntity.setEmail(contactInfoDto.getEmail());
		contactInfoEntity.setCreate(new Date());
		contactInfoEntity.setUpdate(new Date());
		contactInfoEntity.setUUID(UUID.randomUUID().toString());

		NameDto nameDto = contactInfoDto.getName();
		NameEntity nameEntity = new NameEntity();
		nameEntity.setFirstName(nameDto.getFirstName());
		nameEntity.setMiddleName(nameDto.getMiddleName());
		nameEntity.setLastName(nameDto.getLastName());
		nameEntity.setContactInfoEntity(contactInfoEntity);
		contactInfoEntity.setName(nameEntity);

		AddressDto addressDto = contactInfoDto.getAddress();
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setStreet(addressDto.getStreet());
		addressEntity.setCity(addressDto.getCity());
		addressEntity.setState(addressDto.getState());
		addressEntity.setZip(addressDto.getZipCode());
		addressEntity.setContactInfoEntity(contactInfoEntity);
		contactInfoEntity.setAddress(addressEntity);

		List<PhoneDto> phoneDtos = contactInfoDto.getPhone();
		List<PhoneEntity> phoneEntities = new ArrayList<PhoneEntity>();

		for (PhoneDto p : phoneDtos) {
			PhoneEntity phoneEntity = new PhoneEntity();
			phoneEntity.setNumber(p.getNumber());
			phoneEntity.setType(p.getType());
			phoneEntity.setContactInfoEntity(contactInfoEntity);
			phoneEntities.add(phoneEntity);
			contactInfoEntity.setPhoneentity(phoneEntities);
		}
		contactInfoRepository.save(contactInfoEntity);
	}

	public ContactInfoDto getContact(int id) {

		Optional<ContactInfoEntity> contactInfoEntity = contactInfoRepository.findById(id);

		ContactInfoDto contactInfoDto = null;

		if (contactInfoEntity.isPresent()) {
			contactInfoDto = new ContactInfoDto();
			contactInfoDto.setEmail(contactInfoEntity.get().getEmail());

			NameEntity nameEntity = contactInfoEntity.get().getName();
			NameDto nameDto = new NameDto();
			nameDto.setFirstName(nameEntity.getFirstName());
			nameDto.setMiddleName(nameEntity.getMiddleName());
			nameDto.setLastName(nameEntity.getLastName());
			contactInfoDto.setName(nameDto);

			AddressEntity addressEntity = contactInfoEntity.get().getAddress();
			AddressDto addressDto = new AddressDto();
			addressDto.setStreet(addressEntity.getStreet());
			addressDto.setCity(addressEntity.getCity());
			addressDto.setState(addressEntity.getState());
			addressDto.setZipCode(addressEntity.getZip());
			contactInfoDto.setAddress(addressDto);

			List<PhoneEntity> phoneEntity = contactInfoEntity.get().getPhoneentity();

			List<PhoneDto> phoneDto = new ArrayList<PhoneDto>();

			for (PhoneEntity p : phoneEntity) {
				PhoneDto phoneDtos = new PhoneDto();
				phoneDtos.setNumber(p.getNumber());
				phoneDtos.setType(p.getType());
				phoneDto.add(phoneDtos);
				contactInfoDto.setPhone(phoneDto);

			}
		}
		return contactInfoDto;
	}
}