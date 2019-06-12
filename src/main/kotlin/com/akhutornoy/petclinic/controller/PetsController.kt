package com.akhutornoy.petclinic.controller

import com.akhutornoy.petclinic.domain.db.Role
import com.akhutornoy.petclinic.domain.ui.HostForm
import com.akhutornoy.petclinic.domain.ui.PetForm
import com.akhutornoy.petclinic.service.HostsService
import com.akhutornoy.petclinic.service.PetsService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@Controller
class PetsController(
        private val petsService: PetsService,
        private val hostsService: HostsService
) {

    private var hostId: Long = HOST_ID_UNDEFINED

    @GetMapping(path = [END_POINT])
    fun getPets(model: Model,
                @RequestParam(value = PARAM_HOST_ID, required = false) hostIdParam: String?,
                authentication: Authentication
    ): String {
        val hostForm = getHostForm(hostIdParam, authentication)
        this.hostId = hostForm?.id ?: HOST_ID_UNDEFINED

        putModelAttributesFromForm(hostForm, model)
        putModelAttributesFromAuthentication(authentication, model)

        return "pets"
    }

    private fun putModelAttributesFromAuthentication(authentication: Authentication, model: Model) {
        val isAdmin = Role.ADMIN == Role.valueOf(authentication.name.toUpperCase())
        model.addAttribute("isAdmin", isAdmin)
    }

    private fun getHostForm(hostIdParam: String?, authentication: Authentication): HostForm? {
        return if (hostIdParam == null) {
            hostsService.getByUserName(authentication.name)
        } else {
            hostsService.getById(hostIdParam.toLong())
        }
    }

    private fun putModelAttributesFromForm(hostForm: HostForm?, model: Model) {
        val hostName =
                if (hostForm != null)
                    "${hostForm.firstName} ${hostForm.lastName}"
                else
                    "Host Name Not Found"
        model.addAttribute("hostName", hostName)
        model.addAttribute("hostLogin", hostForm?.login)

        val pets = petsService.getPetsByHostId(hostId)
        model.addAttribute("pets", pets)
        model.addAttribute("petForm", PetForm("", "", 0))
    }

    @PostMapping(path =[END_POINT])
    fun addPet(
            @Valid @ModelAttribute("petForm") petForm: PetForm,
               bindingResult: BindingResult): String {

//             TODO: fix validation
        if (bindingResult.hasErrors()) {
            return "$END_POINT?$PARAM_HOST_ID=$hostId"
        }

        petForm.hostId = hostId
        petsService.addPet(petForm)
        return "redirect:$END_POINT?$PARAM_HOST_ID=$hostId"
    }

    @GetMapping(path = [DELETE_PET])
    fun deletePet(model: Model,
                  @RequestParam(value = PARAM_PET_ID, required = true) petId: Long): String {
        petsService.deletePet(petId)
        return "redirect:$END_POINT"
    }

    @ExceptionHandler(Throwable::class)
    fun handleError(error: Throwable, response: HttpServletResponse): String {
        error.printStackTrace()
        return "pets"
    }

    companion object {
        const val PARAM_HOST_ID = "host_id"
        const val PARAM_PET_ID = "pet_id"
        const val END_POINT = "/pets"
        const val ADD_PET = "/addpet"
        const val DELETE_PET = "/deletepet"

        const val HOST_ID_UNDEFINED = 0L;
    }

}