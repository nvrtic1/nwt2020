package net.springboot.user_micro_service.web;

import java.util.List;

import javax.validation.Valid;

import net.springboot.user_micro_service.service.UserEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import lombok.RequiredArgsConstructor;
import net.springboot.user_micro_service.model.User;
import net.springboot.user_micro_service.repository.UserRepository;
import net.springboot.user_micro_service.service.UserService;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final UserRepository userRepository;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    
    private String songService="song_microservice";

    @Autowired
    UserEventHandler userEventHandler;


    @GetMapping("/")
    public RedirectView redirect() {
    	        return new RedirectView("/index");
    	    }
	/**
	 * Get login user form
	 * @param empty
	 * @return login page
	 */
    @PostMapping
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    /**
    * Get Index page 
    * @param empty
    * @return index page
    */
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * Get admin home page
     * @param empty
     * @return admin home page
     */
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
    
    /**
     * Get delete users form and delete specified user
     * @param email - String RequestParam email of the user who is being deleted
     * @param model - Model
     * @return redirect to admin/users page
     */
    @RequestMapping(value = "/admin/delete/users", method = RequestMethod.GET)
    public String deleteUserAdmin(@RequestParam (name="userEmail") String email, Model model) {
        userService.deleteByEmail(email);
        return "redirect:/admin/users";
    }
    
    /**
     * Delete user with provided user email and return user which is being deleted
     * @param email - String RequestParam email of the user who is being deleted
     * @param model - Model
     * @return user - User who is deleted
     */
    @ResponseBody
    @RequestMapping(value = "/delete/users", method = RequestMethod.DELETE)
    public User deleteUser(@RequestParam (name="userEmail") String email, Model model) {
    	User user = userService.findByEmail(email);
        userService.deleteByEmail(email);

        userEventHandler.handleAfterDelete(user);

        return user;
    }
    
    /**
     * Get edit user information page
     * @param model - Model
     * @return update user page
     */
    @GetMapping("/edit")
    public String showUpdateForm(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        User user = userService.findByEmail(username);
        model.addAttribute("user", user);
//        model.addAttribute("roles1", user.getRoles());
        return "updateUser";
    }
    
//    @PostMapping("/update")
//    public String updateUser(@Valid User user, 
//      BindingResult result, Model model) {
//        if (result.hasErrors()) {
////            user.setId(id);
//            return "updateUser";
//        }
//        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
//        String username = loggedInUser.getName();
//        User existing = userService.findByEmail(username);
//        user.setRoles(existing.getRoles());
//        userRepository.save(user);
////        model.addAttribute("users", userRepository.findAll());
//        SecurityContextHolder.clearContext();
//        return "redirect:/index";
//    }
    
    /**
     * Update user with provided user id and return updated user 
     * @param id - Long RequestParam id of the user who is being updated
     * @param user - User with desired attributes
     * @return ResponseEntity with OK status and updates user
     */
    @ResponseBody
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestParam (name="id") Long id,@RequestBody @Valid User user) {
        User existing = userService.findById(id);
        existing.setFirstName(user.getFirstName());
        existing.setLastName(user.getLastName());
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword());
        existing.setRoles(user.getRoles());

        final User updatedUser = userRepository.save(existing);
        return ResponseEntity.ok(updatedUser);
    }
    
   
    /**
     * Get all users page for admin
     * @param model - Model
     * @return return adminUsers page with all registered users
     */
    @GetMapping("/admin/users")
    public String adminUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "adminUsers";
    }
    
    /**
     * Get all users without any authentication in json format
     * @param empty
     * @return users - List of all registered users
     */
    @ResponseBody
    @GetMapping("/users")
    public List<User> adminUsers() {
        List<User> users = userService.findAll();
        return users;
    }
    
    /**
     * Get single user details for provided user id
     * @param id - Long RequestParam id of user which details we are looking for
     * @return ResponseEntity - if user is found return that user and status OK, if user is not found return status NOT FOUND and message
     */
    @ResponseBody
    @GetMapping("/users/user")
    public ResponseEntity<?> getUserDetails(@RequestParam (name="id") Long id) {
        User user = userService.findById(id);
        if(user==null)
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
    
    /**
     * Get user home page
     * @param empty
     * @return user home page
     */
    @GetMapping("/userhome")
    public String user(/*@RequestParam(name="name", required=false, defaultValue="user")String name,Model model*/) {
		/*
		 * Authentication authentication =
		 * SecurityContextHolder.getContext().getAuthentication(); String
		 * currentPrincipalName = authentication.getName();
		 * System.out.println(currentPrincipalName); model.addAttribute("name",name);
		 */
        return "userhome";
    }
    
    /**
     * Get all song by calling song microservice
     * @param empty
     * @return result - List of Objects in this case List of songs provided by song microservice
     */
    @ResponseBody
    @RequestMapping("/songs")
    public List<Object> findAllSongs() {
        Application application = eurekaClient.getApplication(songService);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        System.out.println(instanceInfo);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "songs";
        System.out.println("URL" + url);

        List<Object> result = restTemplate.getForObject(url, List.class);
        return result;
    }
}
