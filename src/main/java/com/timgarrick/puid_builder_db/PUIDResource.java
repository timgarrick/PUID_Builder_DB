package com.timgarrick.puid_builder_db;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puid")
public class PUIDResource {
    private final PUIDService puidService;

    public PUIDResource(PUIDService puidService) {
        this.puidService = puidService;
    }

    //Create user
    @PostMapping("/create")
    public ResponseEntity<PUID> addPUID(@RequestBody PUID puid) {
        PUID newPUID = puidService.addPUID(puid);
        return new ResponseEntity<>(newPUID, HttpStatus.CREATED);
    }

    //retreive user by id
    @GetMapping("/show/{id}")
    public ResponseEntity<PUID> getUserById(@PathVariable("id") Long id) {
        PUID puid = puidService.findPUIDByID(id);
        return new ResponseEntity<>(puid, HttpStatus.OK);
    }
/*
    //Retrieve user by puidname
    @GetMapping("/show/{puidname}")
    public ResponseEntity<List<PUID>> getUserByPuidName(@PathVariable("puidname") String puidname) {
        List<PUID> puidList = puidService.findPUIDByPUIDName(puidname);
        return new ResponseEntity<>(puidList, HttpStatus.OK);
    }*/

    //Retrieve all users
    @GetMapping("/show")
    public ResponseEntity<List<PUID>> getAllUsers() {
        List<PUID> puidList = puidService.findAllUsers();
        return new ResponseEntity<>(puidList, HttpStatus.OK);
    }

    //Update user
    @PutMapping("/update")
    public ResponseEntity<PUID> updatePUID(@RequestBody PUID puid) {
        PUID newPUID = puidService.updatePUID(puid);
        return new ResponseEntity<>(newPUID, HttpStatus.OK);
    }

    //Update user by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<PUID> updatePUIDByID(@PathVariable("id") Long id) {
        PUID updatedPUID = puidService.updatePUIDByID(id);
        return new ResponseEntity<>(updatedPUID, HttpStatus.OK);
    }

    //Set PUID obsolete
    @PutMapping("/delete")
    public ResponseEntity<PUID> deletePUID(@RequestBody PUID puid) {
        PUID puidToDelete = puidService.deletePUID(puid);
        return new ResponseEntity<>(puidToDelete, HttpStatus.OK);
    }

/*    //Set PUID obsolete by ID
    @PutMapping("/delete/{id}")
    public ResponseEntity<PUID> deletePUIDByID(@PathVariable("id") Long id) {
        PUID obsoletePUID = puidService.deletePUIDByID(id);
        return new ResponseEntity<>(obsoletePUID, HttpStatus.OK);
    }*/


/*    @GetMapping("/list")
    public ResponseEntity<PUID> findAllPUID(@RequestBody PUID puid) {
        List<PUID> = puidService.;
        return new ResponseEntity<>(newPUID, HttpStatus.CREATED);
    }*/
}
