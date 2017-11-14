package com.devbook.controller;

import com.devbook.model.FriendRequest;
import com.devbook.model.Skill;
import com.devbook.model.User;
import com.devbook.service.AddToFriendsService;
import com.devbook.service.PostService;
import com.devbook.service.SearchUserService;
import com.devbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
public class UserController {

    private SearchUserService searchUserService;
    private AddToFriendsService addToFriendsService;
    private UserService userService;
    private PostService postService;

    //TODO fix searching case sensivity
    //TODO w restowym api jest jeden search controller i jest robione z parametrami metody
    @Autowired
    public UserController(SearchUserService searchUserService, AddToFriendsService addToFriendsService, UserService userService, PostService postService) {
        this.searchUserService = searchUserService;
        this.addToFriendsService = addToFriendsService;
        this.userService = userService;
        this.postService = postService;
    }


    // TODO w adminie wszyscy userzy + opcje filtrowania to co jest teraz to ma byc ponizej w user/id

    @GetMapping("/user")
    public RedirectView homeWhenViewingLoggedUser() {
        String userId = userService.getCurrentlyLoggedUser().get_id();
        return new RedirectView("user/" + userId);
    }

    @GetMapping("/user/{id}")
    public ModelAndView homeWhenViewingOtherPersonProfile(@PathVariable("id") String userId, Model model) {
        User user = searchUserService.findById(userId);
        model.addAttribute("postList", user.getPostList());
        model.addAttribute("user", user);
        model.addAttribute("friendsProfilePicturesImages", userService.getProfileImageUrlsListFromCurrentlyLoggedUserFriends());
        return new ModelAndView("home");
    }

    //TODO addpost to bedzie /user/post z metoda post

    @PostMapping("/user/addPost")
    public RedirectView searchUsers(@RequestParam("postBody") String postBody,
                                    @RequestParam("userId") String userId) {

        postService.addPost(postBody, userId);
        return new RedirectView(userId);
    }

    @GetMapping("/user/friendRequests")
    public ModelAndView userFriendRequests(Model model) {

        List<FriendRequest> friendRequestList = addToFriendsService.getFriendRequestList();


        model.addAttribute("friendRequestsList", friendRequestList);
        return new ModelAndView("userFriendRequests");
    }

    // TODO "/user/friendrequests" z postem

    @PostMapping("user/addToFriends")
    public RedirectView addToFriend(
            @RequestParam("originUserId") String originUserId,
            @RequestParam("targetUserId") String targetUserId) {

        addToFriendsService.sendFriendRequest(originUserId, targetUserId);
        return new RedirectView("/user");
    }

    //TODO POST user/friend akceptowanie friend requestu
    @PostMapping("user/friend")
    public RedirectView acceptFriendRequest(
            @RequestParam("originUserId") String originuserid,
            @RequestParam("targetUserId") String targetuserid) {
        addToFriendsService.acceptFriendRequest(originuserid, targetuserid);
        return new RedirectView("/user");
    }

    @GetMapping("/user/edit")
    public ModelAndView userEditor(Model model) {
        User user = userService.getCurrentlyLoggedUser();
        model.addAttribute("user", user);
        return new ModelAndView("userEditProfile");
    }

    //TODO /user put -> put to znaczy update

    @PostMapping("user/updateProfile")
    public RedirectView updateProfile(@RequestParam("firstName") String firstName,
                                      @RequestParam("lastName") String lastName,
                                      @RequestParam("summary") String summary,
                                      @RequestParam("currentStatus") String currentStatus,
                                      @RequestParam("headerImageUrl") String headerImageUrl,
                                      @RequestParam("profileImageUrl") String profileImageUrl){
// cofnac to co napisalas bo wtedy to co jest nad tym co robisz przestanie dzialac!!!!

        userService.updateCurrentUserProfile(firstName, lastName, summary, currentStatus, headerImageUrl, profileImageUrl);
        return new RedirectView("/user");
    }

    @PostMapping("user/addSkill")
    public RedirectView addSkill(@RequestParam("skillName") String skillName,
                                 @RequestParam("skillDescription") String skillDescription){
        System.out.println(skillName);
        System.out.println(skillDescription);
        Skill skill = new Skill();
        skill.setSkillDescription(skillDescription);
        skill.setSkillName(skillName);
        User user = userService.getCurrentlyLoggedUser();
        userService.addSkill(user, skill);
   //     potem wywolujemy motede w user service ktora ta metoda wywowla z user serwice
        // inna metode ktora juz jest ktora wywola aktualnego uzytkownika z ktorej pobierzemy
        // liste jego skilli i do ktorej dodamy nowy skill po czym na koncu wezwiemy
        // user repository i zpaiszemy usera zeby zachowac zmainy


        return new RedirectView("/user/edit");
    }

//TODO tutaj trzeba dorobic id do skillow zeby edytowac kazdy z osobna
    //tutaj robisz drugi kontroller update skills ktory analogicznie


}
