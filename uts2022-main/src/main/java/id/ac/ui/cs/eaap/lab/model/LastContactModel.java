package id.ac.ui.cs.eaap.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "case_contact")
public class LastContactModel implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "case_contact_id")
    private long caseContactId;

    @Column(name = "nama")
    private String nama;
    
    @Column(name = "keterangan")
    private String keterangan;

    // TODO: Relasi dengan CovidCaseModel
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "case_id", referencedColumnName = "case_id")
    private CovidCaseModel covidCaseModel;

}