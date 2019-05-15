package com.akhutornoy.petclinic.controller

import com.akhutornoy.petclinic.domain.ui.PetForm
import com.akhutornoy.petclinic.service.NamesServiceImpl
import com.akhutornoy.petclinic.service.PetsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@Controller
@RequestMapping("/pets")
class PetsController(
        private val petsService: PetsService,
        private val namesService: NamesServiceImpl
) {

    private var hostId: Long = 0

    @GetMapping
    fun getPets(model: Model,
                @RequestParam(value = "host-id", required = true) hostIdParam: String
    ): String {
        this.hostId = hostIdParam.toLong()
        val userForm = namesService.getUserById(hostId)

        val hostName =
                if(userForm != null)
                    "${userForm.firstName} ${userForm.lastName}"
                else
                    "Name Not Found"
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
            return "pets?host-id=$hostId"
        }

        petForm.hostId = hostId
        petsService.addPet(petForm)
        return "redirect:" + "/pets?host-id=$hostId"
    }

    @ExceptionHandler(Throwable::class)
    fun handleError(error: Throwable, response: HttpServletResponse): String {
        error.printStackTrace()
        return "pets"
    }

}