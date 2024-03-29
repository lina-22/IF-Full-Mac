package com.serverSide.javaSpringBoot.controller;


import com.serverSide.javaSpringBoot.dto.MaterialDto;
import com.serverSide.javaSpringBoot.manager.MaterialManager;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materials")
@AllArgsConstructor
public class MaterialController {
    private final MaterialManager materialManager;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public MaterialDto createMaterial(@RequestBody MaterialDto materialDto){

        return materialManager.createMaterial(materialDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<MaterialDto> getAllMaterial(){
        return materialManager.getAllMaterial();
    }

    @GetMapping(path= "/{material_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public MaterialDto getMaterialById(@PathVariable long material_id){
        return materialManager.getMaterialById(material_id);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public MaterialDto updateMaterialById(@RequestBody MaterialDto materialDto){
        return materialManager.updateMaterial(materialDto);
    }

    @DeleteMapping(path = "/{material_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteMaterialById(@PathVariable long material_id){
        try{
            materialManager.deleteMaterialById(material_id);
            return new ResponseEntity<>("Material with id + " + material_id + " has been deleted successfully.", HttpStatus.OK);
        }catch (Exception excp){
            System.out.println(excp.getMessage());
            return new ResponseEntity<>("Material with " + material_id + " not found", HttpStatus.NOT_FOUND);
        }

    }
}
