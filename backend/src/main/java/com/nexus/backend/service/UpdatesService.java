package com.nexus.backend.service;

import com.nexus.backend.entity.Updates;
import com.nexus.backend.entity.User;
import com.nexus.backend.repository.UpdatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UpdatesService {

    @Autowired
    private UpdatesRepository updatesRepository;

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Autowired
    private EmailSenderService emailSenderService;

    public Updates createUpdate(Updates newUpdate, Integer userId) {

        newUpdate.setDate(LocalDateTime.now());
        newUpdate.setUploaderId(userId);
        sendMailsToUsers(newUpdate);

        return updatesRepository.save(newUpdate);
    }

    public void sendMailsToUsers(Updates newUpdate){

        List<User> usersWithPreferences = userPreferenceService.getUsersWithPreferences(
                newUpdate.getMinistry().getId(),
                newUpdate.getIndustry().getId(),
                newUpdate.getCategory().getId(),
                newUpdate.getState().getId()
        );

        String mailBody = newUpdate.getTitle() + "\n\n" + newUpdate.getDescription();

        for (User user : usersWithPreferences) {
            System.out.println(user.getEmail().trim());
            if (user.getEmail() != null && !user.getEmail().isEmpty())
                emailSenderService.sendMail(user.getEmail().trim(), "New Update From Compliance Nexus", mailBody);
        }

    }
}
