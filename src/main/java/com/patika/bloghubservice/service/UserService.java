package com.patika.bloghubservice.service;


import com.patika.bloghubservice.client.email.service.EmailClientService;
import com.patika.bloghubservice.client.email.dto.request.EmailCreateRequest;
import com.patika.bloghubservice.client.email.dto.request.enums.EmailTemplate;

import com.patika.bloghubservice.client.payment.dto.request.PaymentRequest;
import com.patika.bloghubservice.client.payment.service.PaymentClientService;
import com.patika.bloghubservice.converter.UserConverter;
import com.patika.bloghubservice.dto.producer.SendEmailMessage;
import com.patika.bloghubservice.dto.request.UserSafeRequest;
import com.patika.bloghubservice.dto.response.UserResponse;
import com.patika.bloghubservice.exception.BlogHubException;
import com.patika.bloghubservice.exception.ExceptionMessages;
import com.patika.bloghubservice.model.User;
import com.patika.bloghubservice.model.enums.StatusType;
import com.patika.bloghubservice.producer.RabbitMqService;
import com.patika.bloghubservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor

//Loglama İçin
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final EmailClientService emailClientService;
    private final PaymentClientService paymentClientService;
    private final RabbitMqService rabbitMqService;


    private String spliter = ",";


    public UserService(UserRepository userRepository, EmailClientService emailClientService, PaymentClientService paymentClientService, RabbitMqService rabbitMqService) {
        this.userRepository = userRepository;
        this.emailClientService = emailClientService;
        this.paymentClientService = paymentClientService;
        this.rabbitMqService = rabbitMqService;
    }

    public UserResponse saveUser(UserSafeRequest request) {

        if (request.getEmail() == null) {
            log.error("request {}",request +"\n" + ExceptionMessages.EMAIL_IS_REQUIRED);

            throw new BlogHubException(ExceptionMessages.EMAIL_IS_REQUIRED);
        }

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

        if (optionalUser.isPresent()) {
            throw new BlogHubException(ExceptionMessages.USER_ALREADY_EXISTS);
        } else {
            User savedUser = new User(request.getEmail(), request.getPassword());

            System.out.println("User saved: " + request.getEmail() + spliter + request.getPassword());

            userRepository.save(savedUser);

            rabbitMqService.sendEmail(new SendEmailMessage(request.getEmail(), EmailTemplate.CREATE_USER_TEMPLATE));
            //emailClientService.sendEmail(new EmailCreateRequest(request.getEmail(), EmailTemplate.CREATE_USER_TEMPLATE));


            log.info("User saved");

            return UserConverter.toResponse(savedUser);
        }


    }

    public UserResponse getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Bulunamadı."));

        return UserConverter.toResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        return UserConverter.toResponse(users);
    }

    public void changeStatus(String email, StatusType statusType) {

        Optional<User> foundUser = userRepository.findByEmail(email);

        foundUser.get().setStatusType(statusType);

        paymentClientService.createPayment(new PaymentRequest(new BigDecimal(100), email));
        //userRepository.changeStatus(email, foundUser.get());

        //getUserByEmail(email).setStatusType(statusType);
    }

    public void changeStatus(List<String> emailList, StatusType statusType) {
        Map<String, User> allUsersMap = getAllUsersMap();

        emailList.forEach(email -> {
            User user = allUsersMap.get(email);
            user.setStatusType(statusType);
        });

    }

    public void changeStatusBulk(List<String> emailList, StatusType statusType) {
        //Map<String, User> allUsersMap = getAllUsersMap();

        emailList.forEach(email -> changeStatus(email, statusType));

    }

    public Map<String, User> getAllUsersMap() {
//        return getAllUsers().stream()
//                .collect(Collectors.toMap(User::getEmail, Function.identity()));
        return userRepository.findAll()
                .stream()
                .collect(Collectors.toMap(User::getEmail, Function.identity()));

    }
}
