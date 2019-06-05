package com.akhutornoy.petclinic.controller

import com.akhutornoy.petclinic.controller.PetsController.Companion.END_POINT
import com.akhutornoy.petclinic.domain.ui.PetForm
import com.akhutornoy.petclinic.service.HostsService
import com.akhutornoy.petclinic.service.PetsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@Controller
@RequestMapping(END_POINT)
class PetsController(
        private val petsService: PetsService,
        private val hostsService: HostsService
) {

    private var hostId: Long = 0

    @GetMapping
    fun getPets(model: Model,
                @RequestParam(value = "host-id", required = true) hostIdParam: String
    ): String {
        this.hostId = hostIdParam.toLong()
        val hostForm = hostsService.getById(hostId)

        val hostName =
                if(hostForm != null)
                    "${hostForm.firstName} ${hostForm.lastName}"
                else
                    "Host Name Not Found"
        model.addAttribute("hostName", hostName)

        val pets = petsService.getPetsByHostId(hostId)
        model.addAttribute("pets", pets)
        model.addAttribute("petForm", PetForm("", "", 0))

        return "pets"
    }

    @PostMapping
    fun addPet(
            @Valid @ModelAttribute("petForm") petForm: PetForm,
               bindingResult: BindingResult): String {

//             TODO: fix validation
        if (bindingResult.hasErrors()) {
            return "$END_POINT?host-id=$hostId"
        }

        petForm.hostId = hostId
        petsService.addPet(petForm)
        return "redirect:$END_POINT?host-id=$hostId"
    }

    @ExceptionHandler(Throwable::class)
    fun handleError(error: Throwable, response: HttpServletResponse): String {
        error.printStackTrace()
        return "pets"
    }

    companion object {
        const val END_POINT = "/pets"
        const val ADD_PET = "/addpet"
        const val DELETE_PET = "/deletepet"
    }

}