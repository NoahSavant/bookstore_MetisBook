package com.metis.book.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.dto.AimForm;
import com.metis.book.model.Aim;
import com.metis.book.model.Book;
import com.metis.book.model.order.Order;
import com.metis.book.model.order.OrderItem;
import com.metis.book.model.user.User;
import com.metis.book.repository.AimRepository;
import com.metis.book.repository.BookRepository;
import com.metis.book.repository.OrderItemRepository;
import com.metis.book.repository.OrderRepository;
import com.metis.book.repository.UserRepository;
import com.metis.book.service.IAimService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AimServiceImpl implements IAimService {
    @Autowired
    AimRepository aimRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public String getSalesData(int year) {
        List<Order> list = orderRepository.findAll();
        List<Long> sales = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            sales.add(0l);
        }
        for (Order order : list) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(order.getOrderDate());
            if (calendar.get(Calendar.YEAR) == year) {
                int index = calendar.get(Calendar.MONTH);
                sales.set(index, sales.get(index) + order.getTotalPrice());
            }
        }
        String data = "";
        for (Long sale : sales) {
            data += sale.toString() + " ";
        }
        return data;
    }

    private void addBookToList(List<OrderItem> list, OrderItem item) {
        for (OrderItem orderItem : list) {
            if (orderItem.getBook().equals(item.getBook())) {
                orderItem.setQuantity(orderItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setBook(item.getBook());
        orderItem.setQuantity(item.getQuantity());
        list.add(orderItem);
    }

    private OrderItem getTopBook(List<OrderItem> list) {
        if (list.size() == 0)
            return null;
        OrderItem max = new OrderItem();
        max.setQuantity(0);
        for (OrderItem orderItem : list) {
            if (orderItem.getQuantity() > max.getQuantity()) {
                max.setBook(orderItem.getBook());
                max.setQuantity(orderItem.getQuantity());
            }
        }
        if (list.remove(max))
            return max;
        return null;
    }

    private String getTopProduct(List<OrderItem> list) {
        int total = 0;
        for (OrderItem orderItem : list) {
            total += orderItem.getQuantity();
        }
        String data = "";
        String percent = "";
        for (int i = 0; i < 3; i++) {
            OrderItem temp = getTopBook(list);
            if (temp != null) {
                data += temp.getBook().getTitle() + ", ";
                int num = temp.getQuantity() * 100 / total;
                percent += String.valueOf(num) + " ";
            }
        }
        data = data.substring(0, data.length() - 2);
        percent = percent.substring(0, percent.length() - 1);
        data += "|" + percent;
        return data;
    }

    @Override
    public String getProductData(int year) {
        List<Order> list = orderRepository.findAll();
        List<OrderItem> listInYear = new ArrayList<>();

        for (Order order : list) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(order.getOrderDate());
            if (calendar.get(Calendar.YEAR) == year) {
                for (OrderItem orderItem : order.getOrderItems()) {
                    addBookToList(listInYear, orderItem);
                }
            }
        }

        return getTopProduct(listInYear);
    }

    private User getTopUser(List<User> list, int year) {
        if (list.size() == 0)
            return null;
        long max = 0;
        User temp = new User();
        for (User user : list) {
            long total = user.totalPurchase(year);
            if (total > max) {
                max = total;
                temp = user;
            }
        }
        if (list.remove(temp))
            return temp;
        return null;
    }

    @Override
    public String getCustomerData(int year) {
        List<User> list = userRepository.findAll();
        long total = 0;
        for (User user : list) {
            total += user.totalPurchase(year);
        }
        String data = "";
        String percent = "";
        for (int i = 0; i < 3; i++) {
            User temp = getTopUser(list, year);
            if (temp != null) {
                data += temp.getUsername() + ", ";
                long num = temp.totalPurchase(year) * 100 / total;
                percent += String.valueOf(num) + " ";
            }
        }
        data = data.substring(0, data.length() - 2);
        percent = percent.substring(0, percent.length() - 1);
        data += "|" + percent;
        return data;
    }

    @Override
    public String getAimData(int year) {
        Aim temp = aimRepository.findByYear(year);
        if (Objects.isNull(temp))
            return "0 0 0 0 0";
        String data = "";
        String[] list = temp.getValue().split(" ");

        List<User> listUser = userRepository.findAll();
        long totalRevenue = 0;
        for (User user : listUser) {
            totalRevenue += user.totalPurchase(year);
        }
        data += String.valueOf(Math.min(100, totalRevenue * 100 / Long.parseLong(list[0]))) + " ";

        List<Order> listOrder = orderRepository.findAll();
        int totalOrder = 0;
        for (Order order : listOrder) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(order.getOrderDate());
            if (calendar.get(Calendar.YEAR) == year) {
                totalOrder += 1;
            }
        }
        data += String.valueOf(Math.min(100, totalOrder * 100 / Integer.parseInt(list[1]))) + " ";

        data += String.valueOf(Math.min(100, listUser.size() * 100 / Integer.parseInt(list[2]))) + " ";

        List<Book> listBook = bookRepository.findAll();
        float rate = 0;
        int totalRate = 0;
        for (Book book : listBook) {
            float rateTemp = book.getAvgRate();
            if (rateTemp > 0) {
                rate += rateTemp;
                totalRate += 1;
            }
        }

        rate /= totalRate;
        data += String.valueOf(Math.min(100, rate * 100 / Float.parseFloat(list[3]))) + " ";

        int totalProduct = 0;
        for (Order order : listOrder) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(order.getOrderDate());
            if (calendar.get(Calendar.YEAR) == year) {
                totalProduct += order.getNumProduct();
            }
        }
        data += String.valueOf(Math.min(100, totalProduct * 100 / Integer.parseInt(list[4]))) + " ";

        return data;
    }

    @Override
    public String getAimInYear(int year) {
        Aim temp = aimRepository.findByYear(year);
        if (Objects.isNull(temp))
            return "0 0 0 0 0";
        return temp.getValue();
    }

    @Override
    public void save(AimForm aimForm) {
        Aim aim = aimRepository.findByYear(aimForm.getCustomAreaYear());
        if (Objects.isNull(aim)) {
            Aim temp = new Aim();
            temp.setValue(aimForm.getData());
            temp.setYear(aimForm.getCustomAreaYear());
            aimRepository.save(temp);
        } else {
            aim.setValue(aimForm.getData());
            aimRepository.save(aim);
        }
    }

}