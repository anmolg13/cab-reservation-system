package com.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.dao.StorageDao;
import com.assignment.model.Rider;

@Service
public class RiderServiceImpl implements RiderService {

	@Autowired
	StorageDao storage;

	public Boolean registerRider(Rider rider) {
		storage.saveRider(rider);
		return true;
	}

}
