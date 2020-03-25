package com.cubic3.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class ContactInfoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	@Column(name = "email")
	private String Email;

	@Column(name = "created_at")
	private Date create;

	@Column(name = "updated_at")
	private Date update;

	@Column(name = "uuid")
	private String UUID;

	@OneToOne(mappedBy = "contactInfoEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private NameEntity name;

	@OneToOne(mappedBy = "contactInfoEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private AddressEntity address;

	@OneToMany(mappedBy = "contactInfoEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<PhoneEntity> phoneentity;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public NameEntity getName() {
		return name;
	}

	public void setName(NameEntity name) {
		this.name = name;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public List<PhoneEntity> getPhoneentity() {
		return phoneentity;
	}

	public void setPhoneentity(List<PhoneEntity> phoneentity) {
		this.phoneentity = phoneentity;
	}

}
