package com.metis.book.serviceImpl;


import com.metis.book.model.order.OrderTrack;
import com.metis.book.repository.OrderTrackRepository;
import com.metis.book.service.IOrderTrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class OrderTrackServiceImpl implements IOrderTrackService {
    @Autowired
    OrderTrackRepository orderTrackRepository;
    @Override
    public List<OrderTrack> getAllOrderTrack() {
        return orderTrackRepository.findAll();
    }
}
