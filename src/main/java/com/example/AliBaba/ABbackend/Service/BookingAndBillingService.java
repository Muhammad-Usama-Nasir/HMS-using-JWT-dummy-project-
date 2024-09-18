package com.example.AliBaba.ABbackend.Service;

import java.util.List;

import com.example.AliBaba.ABbackend.ORM.ORMDeleteRecord;
import com.example.AliBaba.ABbackend.ORM.ORMGetPayment;
import com.example.AliBaba.ABbackend.ORM.ORMGetReservations;
import com.example.AliBaba.ABbackend.ORM.ORMSavePayment;
import com.example.AliBaba.ABbackend.ORM.ORMSaveReservations;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;

public interface BookingAndBillingService {

	ResponseStatus createReservation(ORMSaveReservations saveReservation);

	ORMGetReservations findReservation(Long reservationId);

	ResponseStatus updateReservation(ORMSaveReservations saveReservation);

	ResponseStatus createPayment(ORMSavePayment savePayment);

	ORMGetPayment findPayment(Long paymentId);

	ResponseStatus updatePayment(ORMSavePayment savePayment);

	ResponseStatus deleteReservationRecord(ORMDeleteRecord deleteRecord);

	ResponseStatus deletePaymentRecord(ORMDeleteRecord deleteRecord);

	List<ORMGetReservations> findReservationByHotelId(Long hotelId);

	List<ORMGetPayment> findPaymentsByHotelId(Long hotelId);

}
