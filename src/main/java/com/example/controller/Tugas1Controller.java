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

    @RequestMapping(value = "/penduduk/mati", method = RequestMethod.POST)
    public String diePenduduk (Model model,
                                @RequestParam(value = "nik") String nik)
    {
        PendudukModel penduduk = pendudukService.selectPenduduk(nik);

        if (penduduk != null) {
            pendudukService.setWafatPenduduk(nik);
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

        pendudukService.updatePenduduk(penduduk);
        model.addAttribute("penduduk", penduduk);
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
}
