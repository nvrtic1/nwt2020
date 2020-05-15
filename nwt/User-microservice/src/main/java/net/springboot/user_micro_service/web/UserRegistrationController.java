package net.springboot.user_micro_service.web;

import javax.validation.Valid;

import net.springboot.user_micro_service.service.UserEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.springboot.user_micro_service.model.User;
import net.springboot.user_micro_service.service.UserService;
import net.springboot.user_micro_service.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;


    @Autowired
    UserEventHandler userEventHandler;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    /**
     * Returns registration form 
     * @param empty
     * @return registration form
     */
    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

//    @PostMapping
//    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
//                                      BindingResult result){
//        User existing = userService.findByEmail(userDto.getEmail());
//        if (existing != null){
//            result.rejectValue("email", null, "There is already an account registered with that email");
//        }
//
//        if (result.hasErrors()){
//            return "registration";
//        }
//
//        userService.save(userDto);
//        return "redirect:/registration?success";
//    }
    
    /**
     * Register new user who is sent as parameter in method. Returns new registered user 
     * @param user - User who registers
     * @param result - BindingResult for error checking
     * @return ResponseEntitiy - if everything went okay returns user and Http status CREATED, if we had BAD_REQUEST returns "Bad request" and corresponding HttpStatus, if there is already registreted user with provided email reject that user and provide message 
     */
    @ResponseBody
    @PostMapping
    public ResponseEntity<?> registerUserAccount(@RequestBody @Valid User user, BindingResult result){
//    	System.out.println(user);
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
            System.out.println("Duplikat");
            return new ResponseEntity<User>(user,HttpStatus.CONFLICT);
        }

        //if (result.hasErrors()){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request error");
        	//return new ResponseEntity<String>("Bad request",HttpStatus.BAD_REQUEST);
       // }
 
        userService.save(user);
        userEventHandler.handleAfterCreated(user);
//        return user;
        return new ResponseEntity<User>(user,HttpStatus.CREATED);
    }

}
