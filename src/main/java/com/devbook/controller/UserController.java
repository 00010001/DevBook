package com.devbook.controller;

import com.devbook.model.FriendRequest;
import com.devbook.model.Message;
import com.devbook.model.Skill;
import com.devbook.model.User;
import com.devbook.service.*;
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
    private MessageService messageService;

    //TODO fix searching case sensivity
    //TODO w restowym api jest jeden search controller i jest robione z parametrami metody

    @Autowired
    public UserController(SearchUserService searchUserService, AddToFriendsService addToFriendsService, UserService userService, PostService postService, MessageService messageService) {
        this.searchUserService = searchUserService;
        this.addToFriendsService = addToFriendsService;
        this.userService = userService;
        this.postService = postService;
        this.messageService = messageService;
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

    @GetMapping("/user/friendrequests")
    public ModelAndView userFriendRequests(Model model) {

        List<FriendRequest> friendRequestList = addToFriendsService.getFriendRequestList();

        model.addAttribute("friendRequestsList", friendRequestList);
        return new ModelAndView("userFriendRequests");
    }

    //---Messages Start


    //SLUSZY DO WYSYLANIA WIADOMOSCI BEZPOSREDNIO ZE STRONY OBCEGO UZYTKOWNIKA
    @GetMapping("/user/sendmessage")
    public ModelAndView sendMessagePage(@RequestParam("user") String userId, Model model) {
        model.addAttribute("targetUser", userService.getUserById(userId));
        model.addAttribute("targetUserId", userId);
        return new ModelAndView("usersendmessage");
    }

    //SLUZY DO WYSYLANIA WIADOMOSCI ZE STRONY WIADOMOSCI USERA
    @PostMapping("/user/sendmessage")
    public ModelAndView sendMessagePage2(
            @RequestParam("targetUserId") String targetUserId,
            @RequestParam("message") String message,
            Model model) {
        model.addAttribute("targetUser", userService.getUserById(targetUserId));
        model.addAttribute("targetUserId", targetUserId);
        model.addAttribute("messageBody", message);
        return new ModelAndView("usersendmessage");
    }

    //WYSYLA FIZYCZNIE WIADOMOSC
    @PostMapping("/user/message")
    public RedirectView sendMessage(
            @RequestParam("targetUserId") String targetUserId,
            @RequestParam("message") String message) {

        Message myMessage = messageService.createMessage(targetUserId, message);
        messageService.addMessageToSenderUserAndSave(myMessage);
        messageService.addMessageToReceiverUserAndSave(myMessage);

        return new RedirectView("/user");
    }


    //WYSWIETLA STRONE Z WIADOMOSCIAMI UZYTKOWNIKA
    @GetMapping("/user/messages")
    public ModelAndView userMessages(Model model) {
        model.addAttribute("messages", messageService.getCurrentUserMessages());
        model.addAttribute("currentUserId", userService.getCurrentlyLoggedUserId());
        return new ModelAndView("usermessages");
    }

    //---Messages End


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
            @RequestParam("originuserid") String originuserid,
            @RequestParam("targetuserid") String targetuserid) {
        addToFriendsService.acceptFriendRequest(originuserid, targetuserid);
        return new RedirectView("/user");
    }

    @GetMapping("/user/edit")
    public ModelAndView userEditor(Model model) {
        User user = userService.getUserById(userService.getCurrentlyLoggedUserId());
        model.addAttribute("user", user);
        return new ModelAndView("userEditProfile");
    }

    //TODO /user put -> put to znaczy update
    @PostMapping("user/updateprofile")
    public RedirectView updateProfile(@RequestParam("firstname") String firstName,
                                      @RequestParam("lastname") String lastName,
                                      @RequestParam("summary") String summary,
                                      @RequestParam("currentstatus") String currentStatus,
                                      @RequestParam("headerimageurl") String headerImageUrl,
                                      @RequestParam("profileimageurl") String profileImageUrl) {

        userService.updateCurrentUserProfile(firstName, lastName, summary, currentStatus, headerImageUrl, profileImageUrl);
        return new RedirectView("/user/edit");
    }

    @PostMapping("user/addSkill")
    public RedirectView addSkill(@RequestParam("skillName") String skillName,
                                 @RequestParam("skillDescription") String skillDescription) {

        Skill skill = new Skill();
        skill.setSkillDescription(skillDescription);
        skill.setSkillName(skillName);
        User user = userService.getCurrentlyLoggedUser();
        userService.addSkill(user, skill);

        return new RedirectView("/user/edit");
    }

}
