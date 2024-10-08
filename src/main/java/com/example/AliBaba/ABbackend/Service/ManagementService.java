package com.example.AliBaba.ABbackend.Service;

import java.util.List;

import com.example.AliBaba.ABbackend.ORM.ORMDeleteRecord;
import com.example.AliBaba.ABbackend.ORM.ORMGetHotel;
import com.example.AliBaba.ABbackend.ORM.ORMGetService;
import com.example.AliBaba.ABbackend.ORM.ORMGetServiceRequest;
import com.example.AliBaba.ABbackend.ORM.ORMGetUser;
import com.example.AliBaba.ABbackend.ORM.ORMHotel;
import com.example.AliBaba.ABbackend.ORM.ORMSaveService;
import com.example.AliBaba.ABbackend.ORM.ORMSaveServiceRequest;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;

public interface ManagementService {

	ResponseStatus createHotel(ORMHotel hotel);

	ORMGetHotel getHotel(Long hotelId);

	ResponseStatus deleteRecord(ORMDeleteRecord deleteRecord);

	ResponseStatus createEmployee(ORMSaveUser employee);

	ORMGetUser getEmployee(Long userId);

	ResponseStatus deleteEmployee(ORMDeleteRecord deleteRecord);

	ResponseStatus updateEmployee(ORMSaveUser employee);

	ResponseStatus updateHotel(ORMHotel hotel);

	ResponseStatus createService(ORMSaveService saveService);

	ORMGetService getService(Long serviceId);

	ResponseStatus updateService(ORMSaveService saveService);

	ResponseStatus deleteServiceRecord(ORMDeleteRecord deleteRecord);

	List<ORMGetUser> getEmployeesByHotel(Long hotelId);

	List<ORMGetService> getServicesByHotelId(Long hotelId);

	ResponseStatus createServiceRequest(ORMSaveServiceRequest saveServiceRequest);

	ORMGetServiceRequest getServiceRequest(Long serviceRequestId);

	List<ORMGetServiceRequest> getServiceRequestsByHotelId(Long hotelId);

	ResponseStatus updateServiceRequest(ORMSaveServiceRequest saveServiceRequest);

	ResponseStatus deleteServiceRequestRecord(ORMDeleteRecord deleteRecord);


	
}
