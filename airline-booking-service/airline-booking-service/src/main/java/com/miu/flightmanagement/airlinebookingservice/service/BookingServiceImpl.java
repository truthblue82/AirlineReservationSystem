package com.miu.flightmanagement.airlinebookingservice.service;

import com.miu.flightmanagement.airlinebookingservice.dao.BookingDao;
import com.miu.flightmanagement.airlinebookingservice.dao.PaymentDao;
import com.miu.flightmanagement.airlinebookingservice.dto.BookingDTO;
import com.miu.flightmanagement.airlinebookingservice.dto.BookingWithPaymentRequest;
import com.miu.flightmanagement.airlinebookingservice.model.Booking;
import com.miu.flightmanagement.airlinebookingservice.model.Payment;
import com.miu.flightmanagement.airlinebookingservice.model.ScheduledFlight;
import com.miu.flightmanagement.airlinebookingservice.util.BookingUtil;
import com.miu.flightmanagement.airlinebookingservice.util.PaymentUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService{

    private final BookingDao bookingDao;

    private final PaymentDao paymentDao;

    @Override
    @Transactional
    public BookingDTO bookTicketsWithPayment(final BookingWithPaymentRequest bookingRequest) {
        Booking booking = BookingUtil.toEntity(bookingRequest.getBooking());
        booking = bookingDao.save(booking);
        final Payment payment = PaymentUtil.toEntity(bookingRequest.getPayment());
        payment.setBooking(booking);
        paymentDao.save(payment);
        log.info("BOOKING {}", booking);
        return BookingUtil.toDTO(booking);
    }

    @Override
    public BookingDTO findBooking(String bookingCode) {
        return BookingUtil.toDTO(bookingDao.findBookingByBookingCode(bookingCode));
    }

    @Override
    public boolean hasBooking(Long scheduledFlightId) {
        return bookingDao.existsBookingByScheduledFlight(ScheduledFlight.builder().scheduleFlightId(scheduledFlightId).build());
    }
}
