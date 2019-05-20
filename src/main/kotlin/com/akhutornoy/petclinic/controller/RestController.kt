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

    @GetMapping(path = [PetsController.END_POINT], consumes = [MimeTypeUtils.APPLICATION_JSON_VALUE])
    fun getPets(
            @RequestParam(value = "host_id") hostId: String
    ): List<PetDto> {
        return petsService.getPetsByHostId(hostId.toLong())
    }

    @PostMapping(path = [PetsController.END_POINT], consumes = [MimeTypeUtils.APPLICATION_JSON_VALUE])
    fun addPet(
            @RequestParam(value = "name", required = true) name: String,
            @RequestParam(value = "breed", required = true) breed: String,
            @RequestParam(value = "host_id", required = true) hostId: Long

    ) {
        petsService.addPet(name, breed, hostId)
    }

}