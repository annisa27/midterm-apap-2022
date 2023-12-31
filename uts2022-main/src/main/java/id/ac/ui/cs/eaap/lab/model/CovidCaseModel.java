package id.ac.ui.cs.eaap.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Setter
@Getter
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value={"LastContactModel"},allowSetters=true)
@Table(name = "covid_case")
public class CovidCaseModel implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "case_id")
    private Long caseId;

    @Column(name = "nik")
    private String nik;
    @Column(name = "nama")
    private String nama;
    @Column(name = "status")
    private String status;
    @Column(name = "tanggalGejalaPertama")
    private Date tanggalGejalaPertama;
    @Column(name = "peran")
    private String peran;
    @Column(name = "fakultas")
    private String fakultas;

    @OneToMany(mappedBy = "covidCaseModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LastContactModel> listLastContactModel;

    // Nomor 6: get jumlah hari, langsung render
    public long getJumlahHariSetelahGejalaPertama() {
        return (System.currentTimeMillis() - tanggalGejalaPertama.getTime()) / (1000 * 60 * 60 * 24) ;
    }

}