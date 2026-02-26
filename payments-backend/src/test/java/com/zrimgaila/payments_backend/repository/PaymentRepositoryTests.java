package com.zrimgaila.payments_backend.repository;

import com.zrimgaila.payments_backend.general.PaymentStatus;
import com.zrimgaila.payments_backend.general.PaymentType;
import com.zrimgaila.payments_backend.model.Payment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource("/application-test.properties")
public class PaymentRepositoryTests {

    @Autowired
    private PaymentRepository paymentRepository;

//    @Autowired
//    private JdbcTemplate jdbc;

    @BeforeEach
    public void setupDatabase(){
//        jdbc.execute();
    }

    @Test
    public void PaymentRepository_SaveAll_ReturnSavedPayment(){
        // arrange
        Payment payment = Payment.builder()
                .paymentType(PaymentType.EUR)
                .amount(BigDecimal.valueOf(999.9))
                .currency("EUR")
                .debtorIban("DE89370400440532013000")
                .creditorIban("FR7630006000011234567890189")
                .details("something")
                .build();

        // act
        Payment savedPayment = paymentRepository.save(payment);

        // assert
        Assertions.assertThat(savedPayment).isNotNull();
        Assertions.assertThat(savedPayment.getId()).isGreaterThan(0);
    }

    @Test
    public void PaymentRepository_GetAll_ReturnMoreThanOnePayment(){
        Payment payment = Payment.builder()
                .paymentType(PaymentType.EUR)
                .amount(BigDecimal.valueOf(999.9))
                .currency("EUR")
                .debtorIban("DE89370400440532013000")
                .creditorIban("FR7630006000011234567890189")
                .details("something")
                .status(PaymentStatus.ACTIVE)
                .build();

        Payment payment2 = Payment.builder()
                .paymentType(PaymentType.EUR)
                .amount(BigDecimal.valueOf(999.9))
                .currency("EUR")
                .debtorIban("DE89370400440532013000")
                .creditorIban("FR7630006000011234567890189")
                .details("something")
                .status(PaymentStatus.ACTIVE)
                .build();

        paymentRepository.save(payment);
        paymentRepository.save(payment2);

        List<Payment> paymentList = paymentRepository.findAll();

        Assertions.assertThat(paymentList).isNotNull();
        Assertions.assertThat(paymentList.size()).isEqualTo(2);

    }
}
