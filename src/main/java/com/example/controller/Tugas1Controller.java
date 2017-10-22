package com.example.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.BindingResult;

import com.example.model.*;
import com.example.service.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class Tugas1Controller {
    @Autowired
    PendudukService pendudukService;

    @Autowired
    KeluargaService keluargaService;

    @Autowired
    KelurahanService kelurahanService;

    @Autowired
    KecamatanService kecamatanService;

    @Autowired
    KotaService kotaService;

    @RequestMapping("/")
    public String index ()
    {
        return "index";
    }

    @RequestMapping(value = "/penduduk", method = RequestMethod.GET)
    public String viewPenduduk (Model model,
                                @RequestParam(value = "nik") String nik)
    {
        PendudukModel penduduk = pendudukService.selectPenduduk(nik);
        KeluargaModel keluarga = keluargaService.selectKeluargaId(penduduk.getId_keluarga());
        KelurahanModel kelurahan = kelurahanService.selectKelurahanId(keluarga.getId_kelurahan());
        KecamatanModel kecamatan = kecamatanService.selectKecamatanId(kelurahan.getId_kecamatan());
        KotaModel kota = kotaService.selectKotaId(kecamatan.getId_kota());

        if (penduduk != null) {
            model.addAttribute ("penduduk", penduduk);
            model.addAttribute ("keluarga", keluarga);
            model.addAttribute ("kelurahan", kelurahan);
            model.addAttribute ("kecamatan", kecamatan);
            model.addAttribute ("kota", kota);
            return "view-penduduk";
        } else {
            model.addAttribute ("nik", nik);
            return "not-found";
        }
    }

    @RequestMapping(value = "/penduduk/tambah")
    public String addPenduduk (Model model)
    {
        List<KeluargaModel> keluarga = keluargaService.selectAllKeluarga();
        model.addAttribute("penduduk", new PendudukModel());
        model.addAttribute("keluarga", keluarga);
        return "add-penduduk";
    }

    @RequestMapping(value = "/penduduk/tambah/submit")
    public String addPendudukSubmit (Model model, @ModelAttribute PendudukModel penduduk)
    {
        String nik = "";

        KeluargaModel keluarga = keluargaService.selectKeluargaId(penduduk.getId_keluarga());
        KelurahanModel kelurahan = kelurahanService.selectKelurahanId(keluarga.getId_kelurahan());
        KecamatanModel kecamatan = kecamatanService.selectKecamatanId(kelurahan.getId_kecamatan());
        KotaModel kota = kotaService.selectKotaId(kecamatan.getId_kota());

        // Memasukkan kode kecamatan yang sudah mengandung kode kota dan provinsi ke nik
        nik += kecamatan.getKode_kecamatan().substring(0,6);

        if(penduduk.getJenis_kelamin() == 0){
            // Laki-laki

            // Memasukkan tanggal lahir ke nik
            String[] tanggal_lahir = penduduk.getTanggal_lahir().split("-");
            nik += tanggal_lahir[2];
            nik += tanggal_lahir[1];
            nik += tanggal_lahir[0].substring(2);

        } else {
            // Perempuan

            // Memasukkan tanggal lahir + 40 ke nik
            String[] tanggal_lahir = penduduk.getTanggal_lahir().split("-");
            nik += Integer.toString(Integer.parseInt(tanggal_lahir[2]) + 40);
            nik += tanggal_lahir[1];
            nik += tanggal_lahir[0].substring(2);
        }

        List<PendudukModel> pendudukLain = pendudukService.selectSimilarPenduduk(penduduk.getTanggal_lahir(), kelurahan.getId(), kecamatan.getId(), kota.getId());

        // Mengambil nomor urut penduduk baru yaitu jumlah penduduk dgn domisili dan tgl lahir yg sama + 1
        String nomor_urut = Integer.toString(pendudukLain.size() + 1);

        // Memasukkan nomor urut ke nik berdasarkan panjang dari nomor urut
        if(nomor_urut.length() == 1){
            nik += "000";
            nik += nomor_urut;
        } else if(nomor_urut.length() == 2){
            nik += "00";
            nik += nomor_urut;
        } else if(nomor_urut.length() == 3){
            nik += "0";
            nik += nomor_urut;
        } else if(nomor_urut.length() == 4){
            nik += nomor_urut;
        }

        // Memasukkan nik yang sudah dibuat ke model penduduk
        penduduk.setNik(nik);

        // Memasukkan model penduduk baru ke database
        pendudukService.addPenduduk(penduduk);

        model.addAttribute("penduduk", penduduk);
        return "add-penduduk-success";
    }

    @RequestMapping(value = "/penduduk/mati", method = RequestMethod.POST)
    public String diePenduduk (Model model,
                                @RequestParam(value = "nik") String nik)
    {
        PendudukModel penduduk = pendudukService.selectPenduduk(nik);

        if (penduduk != null) {
            pendudukService.setWafatPenduduk(nik);

            // Mengecek keaktifan status keluarga
            KeluargaModel keluarga = keluargaService.selectKeluargaId(penduduk.getId_keluarga());
            int size = keluargaService.keluargaSize(keluarga);
            if(size == 0){
                keluargaService.setKeluargaTidakBerlaku(keluarga);
            }

            model.addAttribute("penduduk", penduduk);
            return "wafat-penduduk";
        } else {
            model.addAttribute ("nik", nik);
            return "not-found";
        }
    }

    @RequestMapping(value = "/penduduk/ubah/{nik}")
    public String updatePenduduk (Model model, @PathVariable(value = "nik") String nik)
    {
        PendudukModel penduduk = pendudukService.selectPenduduk(nik);

        if (penduduk != null) {
            model.addAttribute("penduduk", penduduk);
            return "update-penduduk";
        } else {
            model.addAttribute ("nik", nik);
            return "not-found";
        }
    }

    @RequestMapping(value = "/penduduk/ubah/submit")
    public String updatePenduduk (Model model, @ModelAttribute PendudukModel penduduk)
    {

        String nik_lama = penduduk.getNik();

        // Mengecek perubahan pada NIK
        String nik_baru = "";

        KeluargaModel keluarga = keluargaService.selectKeluargaId(penduduk.getId_keluarga());
        KelurahanModel kelurahan = kelurahanService.selectKelurahanId(keluarga.getId_kelurahan());
        KecamatanModel kecamatan = kecamatanService.selectKecamatanId(kelurahan.getId_kecamatan());
        KotaModel kota = kotaService.selectKotaId(kecamatan.getId_kota());

        // Memasukkan kode kecamatan yang sudah mengandung kode kota dan provinsi ke nik
        nik_baru += kecamatan.getKode_kecamatan().substring(0,6);

        if(penduduk.getJenis_kelamin() == 0){
            // Laki-laki

            // Memasukkan tanggal lahir ke nik
            String[] tanggal_lahir = penduduk.getTanggal_lahir().split("-");
            nik_baru += tanggal_lahir[2];
            nik_baru += tanggal_lahir[1];
            nik_baru += tanggal_lahir[0].substring(2);

        } else {
            // Perempuan

            // Memasukkan tanggal lahir + 40 ke nik
            String[] tanggal_lahir = penduduk.getTanggal_lahir().split("-");
            nik_baru += Integer.toString(Integer.parseInt(tanggal_lahir[2]) + 40);
            nik_baru += tanggal_lahir[1];
            nik_baru += tanggal_lahir[0].substring(2);
        }

        List<PendudukModel> pendudukLain = pendudukService.selectSimilarPenduduk(penduduk.getTanggal_lahir(), kelurahan.getId(), kecamatan.getId(), kota.getId());

        // Mengambil nomor urut penduduk baru yaitu jumlah penduduk dgn domisili dan tgl lahir yg sama + 1
        String nomor_urut = Integer.toString(pendudukLain.size());

        // Memasukkan nomor urut ke nik berdasarkan panjang dari nomor urut
        if(nomor_urut.length() == 1){
            nik_baru += "000";
            nik_baru += nomor_urut;
        } else if(nomor_urut.length() == 2){
            nik_baru += "00";
            nik_baru += nomor_urut;
        } else if(nomor_urut.length() == 3){
            nik_baru += "0";
            nik_baru += nomor_urut;
        } else if(nomor_urut.length() == 4){
            nik_baru += nomor_urut;
        }

        // Memasukkan nik yang sudah dibuat ke model penduduk
        penduduk.setNik(nik_baru);

        pendudukService.updatePenduduk(penduduk, nik_lama);

        model.addAttribute("nik_lama", nik_lama);
        return "update-penduduk-success";
    }

    @RequestMapping(value = "/keluarga", method = RequestMethod.GET)
    public String viewKeluarga (Model model,
                            @RequestParam(value = "nkk") String nkk)
    {
        KeluargaModel keluarga = keluargaService.selectKeluarga(nkk);
        KelurahanModel kelurahan = kelurahanService.selectKelurahanId(keluarga.getId_kelurahan());
        KecamatanModel kecamatan = kecamatanService.selectKecamatanId(kelurahan.getId_kecamatan());
        KotaModel kota = kotaService.selectKotaId(kecamatan.getId_kota());

        List<PendudukModel> anggotaKeluarga = pendudukService.selectPendudukKeluarga(keluarga.getId());

        if (!anggotaKeluarga.isEmpty()) {
            model.addAttribute ("keluarga", keluarga);
            model.addAttribute ("kelurahan", kelurahan);
            model.addAttribute ("kecamatan", kecamatan);
            model.addAttribute ("kota", kota);
            model.addAttribute ("anggotaKeluarga", anggotaKeluarga);
            return "view-keluarga";
        } else {
            model.addAttribute ("nkk", nkk);
            return "not-found";
        }
    }

    @RequestMapping(value = "/keluarga/tambah")
    public String addKeluarga (Model model)
    {
        List<KotaModel> listKota = kotaService.selectAllKota();
        List<KecamatanModel> listKecamatan = kecamatanService.selectAllKecamatan();
        List<KelurahanModel> listKelurahan = kelurahanService.selectAllKelurahan();
        model.addAttribute("keluarga", new KeluargaModel());
        model.addAttribute("listKota", listKota);
        model.addAttribute("listKecamatan", listKecamatan);
        model.addAttribute("listKelurahan", listKelurahan);
        return "add-keluarga";
    }

    @RequestMapping(value = "/keluarga/tambah/submit")
    public String addPendudukSubmit (Model model, @ModelAttribute KeluargaModel keluarga)
    {
        String nkk = "";

        KelurahanModel kelurahan = kelurahanService.selectKelurahanId(keluarga.getId_kelurahan());
        KecamatanModel kecamatan = kecamatanService.selectKecamatanId(kelurahan.getId_kecamatan());

        // Memasukkan kode kecamatan yang sudah mengandung kode kota dan provinsi ke nik
        nkk += kecamatan.getKode_kecamatan().substring(0,6);

        // Mengambil data tanggal pembuatan keluarga
        DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        Date date = new Date();
        nkk += dateFormat.format(date);
        System.out.println("nkk dengan tanggal " + nkk);

        List<KeluargaModel> listKeluarga = keluargaService.selectKeluargaKecamatan(kecamatan.getId());

        int counter = 1;
        for (KeluargaModel klrg : listKeluarga){
            String kel_nkk = klrg.getNomor_kk();
            if(kel_nkk.substring(0,12).equals(nkk)){
                counter++;
            }
        }

        String nomor_urut = Integer.toString(counter);

        // Memasukkan nomor urut ke nkk berdasarkan panjang dari nomor urut
        if(nomor_urut.length() == 1){
            nkk += "000";
            nkk += nomor_urut;
        } else if(nomor_urut.length() == 2){
            nkk += "00";
            nkk += nomor_urut;
        } else if(nomor_urut.length() == 3){
            nkk += "0";
            nkk += nomor_urut;
        } else if(nomor_urut.length() == 4){
            nkk += nomor_urut;
        }

        keluarga.setNomor_kk(nkk);
        keluargaService.addKeluarga(keluarga);
        model.addAttribute("keluarga", keluarga);
        return "add-keluarga-success";
    }
}
