package com.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.dao.StorageDao;
import com.assignment.model.Driver;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	StorageDao storage;

	public Boolean registerDriver(Driver driver) {
		storage.saveDriver(driver);
		return true;
	}
}
