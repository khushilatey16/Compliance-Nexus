package com.nexus.backend.controller;

import com.nexus.backend.entity.Updates;
import com.nexus.backend.entity.User;
import com.nexus.backend.service.UpdatesService;
import com.nexus.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/updates")
public class UpdatesController {

    @Autowired
    private UpdatesService updatesService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Updates> createUpdate(@RequestBody Updates newUpdate, @RequestHeader("Authorization") String jwt,  @RequestParam(value = "pdfFile", required = false) MultipartFile pdfFile) throws Exception {
        User user = userService.findUserProfile(jwt);

        if (user.getIsAdmin() == false){
            return new ResponseEntity<>(newUpdate, HttpStatus.BAD_REQUEST);
        }

        Updates createdUpdate = updatesService.createUpdate(newUpdate, user.getId(), pdfFile);
        return new ResponseEntity<>(createdUpdate, HttpStatus.CREATED);
    }

}
