package com.akhutornoy.petclinic.controller

import com.akhutornoy.petclinic.domain.rest.HostDto
import com.akhutornoy.petclinic.domain.rest.PetDto
import com.akhutornoy.petclinic.service.HostsRestService
import com.akhutornoy.petclinic.service.PetsRestService
import org.springframework.util.MimeTypeUtils
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController

@RestController
class RestController(
        private val hostsService: HostsRestService,
        private val petsService: PetsRestService
) {

    @GetMapping(path = [HostsController.END_POINT, HostsController.END_POINT_ROOT],
            consumes = [MimeTypeUtils.APPLICATION_JSON_VALUE])
    fun getHosts(): List<HostDto> {
        return hostsService.getAll()
    }

    @PostMapping(path = [AddHostController.END_POINT], consumes = [MimeTypeUtils.APPLICATION_JSON_VALUE])
    fun addHost(@RequestBody hostDto: HostDto
    ): HostDto {
        print("RestController: addHost(): $hostDto")
        return hostsService.add(hostDto)
    }

    @GetMapping(path = [AddHostController.DELETE_HOST], consumes = [MimeTypeUtils.APPLICATION_JSON_VALUE])
    fun deleteHost(@RequestParam(value = "host_id") hostId: Long) {
        hostsService.delete(hostId)
    }

    @GetMapping(path = [PetsController.END_POINT], consumes = [MimeTypeUtils.APPLICATION_JSON_VALUE])
    fun getPets(
            @RequestParam(value = "host_id") hostId: Long
    ): List<PetDto> {
        return petsService.getPetsByHostId(hostId)
    }

    @PostMapping(path = [PetsController.ADD_PET], consumes = [MimeTypeUtils.APPLICATION_JSON_VALUE])
    fun addPet(@RequestBody petDto: PetDto

    ): PetDto {
        return petsService.addPet(petDto)
    }

    @GetMapping(path = [PetsController.DELETE_PET], consumes = [MimeTypeUtils.APPLICATION_JSON_VALUE])
    fun deletePet(@RequestParam(value = "pet_id") petId: Long) {
        petsService.delete(petId)
    }

}