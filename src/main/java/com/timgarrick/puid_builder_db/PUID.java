package com.timgarrick.puid_builder_db;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class PUID implements Serializable {
    //@TableGenerator(name="puid", initialValue = 20000000)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "puid")
    @Column(nullable = false, updatable = false)
    private Long puid;
    @Column(nullable = false)
    private String puidname;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false, updatable = false)
    private Date createdDate;
    private Date modifiedDate;
    private Boolean obsolete;

    public PUID(String surname, String firstname) {
        this.puidname = surname.toLowerCase() + firstname.toLowerCase().charAt(0);
        this.surname = surname;
        this.firstname = firstname;
        this.createdDate = new Date();
        this.modifiedDate = null;
        this.obsolete = false;
    }
}
