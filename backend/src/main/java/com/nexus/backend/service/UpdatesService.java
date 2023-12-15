package com.nexus.backend.service;

import com.nexus.backend.entity.Updates;
import com.nexus.backend.entity.User;
import com.nexus.backend.repository.UpdatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UpdatesService {

    @Autowired
    private UpdatesRepository updatesRepository;

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Value("${file.upload.directory}")
    private String uploadDirectory;

    public Updates createUpdate(Updates newUpdate, Integer userId, MultipartFile pdfFile) throws Exception {

        newUpdate.setDate(LocalDateTime.now());
        newUpdate.setUploaderId(userId);

        if (pdfFile != null) {
            String pdfPath = storePdf(pdfFile);
            newUpdate.setPdfPath(pdfPath);
        }

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

    private String storePdf(MultipartFile pdfFile) throws IOException {
        if (pdfFile.isEmpty()) {
            return null;
        }
        String fileName = UUID.randomUUID().toString() + ".pdf";

        Path targetDirectory = Path.of(uploadDirectory);

        Files.createDirectories(targetDirectory);

        Path targetPath = targetDirectory.resolve(fileName);

        Files.copy(pdfFile.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }
}
