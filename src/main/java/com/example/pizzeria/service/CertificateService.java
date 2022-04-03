package com.example.pizzeria.service;

import com.example.pizzeria.entity.CertificateEntity;
import com.example.pizzeria.entity.UserEntity;
import com.example.pizzeria.repository.CertificateRepo;
import com.example.pizzeria.repository.UserRepo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class CertificateService {
    @Autowired
    private CertificateRepo certificateRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProfileService profileService;

    public CertificateEntity create(UserEntity currentUser, UUID toUserID, CertificateEntity certInfo) throws Exception {
        CertificateEntity certificate = new CertificateEntity();
        UserEntity toUser = userRepo.findById(toUserID).orElseThrow(() -> new Exception("Пользователь не найден"));
        if(currentUser.getCash() < certInfo.getPrice()) {
            throw new Exception("Денег на балансе недостаточно");
        }
        if(certInfo.getPrice() < 0) {
            throw new Exception("Должно быть положительное число");
        }
        certificate.setFromUser(currentUser);
        certificate.setToUser(toUser);
        certificate.setPrice(certInfo.getPrice());
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        String createDate = formatter.format(currentDate);
        certificate.setDateCreate(createDate);
        profileService.removeCash(currentUser, certInfo.getPrice());
        profileService.addCash(toUser, certInfo.getPrice());
        certificateRepo.save(certificate);
        val donated = currentUser.getDonatedCertificates();
        donated.add(0, certificate);
        currentUser.setDonatedCertificates(donated);
        userRepo.save(currentUser);
        return certificate;
    }
}
