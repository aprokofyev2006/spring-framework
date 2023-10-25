package com.cydeo.bootstrap;

import com.cydeo.enums.Status;
import com.cydeo.model.*;
import com.cydeo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataGenerator implements CommandLineRunner {

    private final PaymentRepository paymentRepository;
    private final MerchantRepository merchantRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    @Autowired
    public DataGenerator(PaymentRepository paymentRepository, MerchantRepository merchantRepository, CustomerRepository customerRepository, ItemRepository itemRepository, CartRepository cartRepository) {
        this.paymentRepository = paymentRepository;
        this.merchantRepository = merchantRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Payment p1 = new Payment(LocalDate.of(2022,4,19),new BigDecimal("150000"), Status.SUCCESS);
        Payment p2 = new Payment(LocalDate.of(2022,4,25),new BigDecimal("100000"), Status.FAILURE);

        PaymentDetail pd1 = new PaymentDetail(new BigDecimal("140000"),new BigDecimal("10000"),LocalDate.of(2022,4,24));
        PaymentDetail pd2 = new PaymentDetail(new BigDecimal("90000"),new BigDecimal("5000"),LocalDate.of(2022,4,29));

        Merchant m1 = new Merchant("Amazon Sub Merchant","M123",new BigDecimal("0.25"),new BigDecimal("3.25"),5);
        Customer c1 = new Customer("msmith","Mike","Johnson","mike@yopmail.com","123 Main st");

        p1.setPaymentDetail(pd1);
        p2.setPaymentDetail(pd2);

        p1.setMerchant(m1);
        p2.setMerchant(m1);

        p1.setCustomer(c1);
        p2.setCustomer(c1);

        Item item1 = new Item("Milk","M01");
        Item item2 = new Item("Sugar","S01");
        Item item3 = new Item("Bread","B01");

        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        cart1.setItemList(Arrays.asList(item1,item2,item2));
        cart2.setItemList(Arrays.asList(item1,item3));

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);

        cartRepository.save(cart1);
        cartRepository.save(cart2);

        merchantRepository.save(m1);
        customerRepository.save(c1);

        paymentRepository.save(p1);
        paymentRepository.save(p2);
    }
}
