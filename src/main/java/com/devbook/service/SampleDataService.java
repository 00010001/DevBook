package com.devbook.service;

import com.devbook.model.*;
import com.devbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SampleDataService {

    private UserRepository userRepository;

    @Autowired
    public SampleDataService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addSampleDataToDatabase(boolean deleteEverythingFromDatabase){

        if(deleteEverythingFromDatabase){
            userRepository.deleteAll();
        }

        addSampleUsers();

    }

    private void addSampleUsers() {
        User user1 = generateUser1();
        User user2 = generateUser2();
        User user3 = generateUser3();
        User user4 = generateUser4();
        User user5 = generateUser5();
        User user6 = generateUser6();
        User user7 = generateUser7();
        User user8 = generateUser8();
        User user9 = generateUser9();


        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);
        userRepository.save(user8);
        userRepository.save(user9);
    }


    private User generateUser1(){

        User user = new User();

        user.setFirstName("John");
        user.setLastName("Snow");
        user.setEmail("johnsnow@gmail.com");
        user.setPassword("0000");
        user.getRoleSet().add(Role.ROLE_USER);
        user.setSummary("Currently enrolled in Java Coding Bootcamp \n" +
                "at Software Development Academy in Katowice\n" +
                "which I will finish in November 2017. \n" +
                "Expierienced with web development process. \n" +
                "Able to work well independently or as part \n" +
                "of a professional development team.");


        List<Post> postList = new ArrayList<>();
        postList.add(new Post("Nadejszła wiekopomna chwiła"));
        postList.add(new Post("Jeszcze tylko kilka dni..."));
        user.setPostList(postList);


        List<Skill> userSkills = new ArrayList<>();
        userSkills.add(new Skill("Java","Maven, Hibernate, Spring"));
        userSkills.add(new Skill("Frontend","Angular, HTML, CSS"));
        userSkills.add(new Skill("TDD","Junit, Mockito"));


        user.setSkills(userSkills);

        List<FriendRequest> friendRequestList = new ArrayList<>();
        FriendRequest friendRequest = new FriendRequest("0","0");
        friendRequest.setOriginUserProfileImageUrl("http://www.racialjusticenetwork.co.uk/wp-content/uploads/2016/12/default-profile-picture.png");
        user.setFriendRequestsList(friendRequestList);

        return user;
    }

    private User generateUser2(){
        User user = new User();

        user.setFirstName("Aria");
        user.setLastName("Stark");
        user.setEmail("ariastark@gmail.com");
        user.setPassword("0000");
        user.getRoleSet().add(Role.ROLE_USER);
        user.setSummary("Innovator of next-generation solutions, systems and applications giving companies \n" +
                "a competitive edge and producing outstanding results for customers.");

        List<Post> postList = new ArrayList<>();
        postList.add(new Post("Kawa będzie mocniejsza jeżeli zamiast cukru dodasz amfetaminy"));
        postList.add(new Post("Grzyby są różne: jedne nakarmią, inne film wyświetlą..."));
        user.setPostList(postList);

        List<Skill> userSkills = new ArrayList<>();
        userSkills.add(new Skill("Java","Maven, Hibernate, Spring"));
        userSkills.add(new Skill("Frontend","Angular, HTML, CSS"));
        userSkills.add(new Skill("TDD","Junit, Mockito"));
        user.setSkills(userSkills);

        return user;
    }

    private User generateUser3() {
        User user = new User();

        user.setFirstName("Sanca");
        user.setLastName("Stark");
        user.setEmail("sancastark@gmail.com");
        user.setPassword("0000");
        user.getRoleSet().add(Role.ROLE_USER);
        user.setSummary("Nam et ligula tortor. Donec aliquet efficitur volutpat. Curabitur lacus justo, \n" +
                "scelerisque a neque eget, tincidunt efficitur nisi. Curabitur nec eros tellus. \n" +
                "Nunc laoreet ornare est, a elementum nisi tempor eu. Nullam sit amet sapien \n" +
                "eu nibh accumsan ultrices eu ac augue. Etiam eu lorem non ante convallis dictum. \n" +
                "Donec ultricies volutpat pulvinar. Ut et fringilla dui. Nulla tempus scelerisque ante in hendrerit.");

        List<Post> postList = new ArrayList<>();
        postList.add(new Post("Małżeństwo to zakład o połowę majątku, że będziesz z kimś do końca życia"));
        postList.add(new Post("Boję się, że Reni Jusis kiedyś mnie znajdzie..."));
        user.setPostList(postList);

        List<Skill> userSkills = new ArrayList<>();
        userSkills.add(new Skill("Java","Maven, Hibernate, Spring"));
        userSkills.add(new Skill("Frontend","Angular, HTML, CSS"));
        userSkills.add(new Skill("TDD","Junit, Mockito"));
        user.setSkills(userSkills);

        return user;
    }

    private User generateUser4() {
        User user = new User();

        user.setFirstName("Cercei");
        user.setLastName("Lannister");
        user.setEmail("cerceilanister@gmail.com");
        user.setPassword("0000");
        user.getRoleSet().add(Role.ROLE_USER);
        user.setSummary("Sed bibendum scelerisque auctor. Class aptent taciti sociosqu ad litora \n" +
                "torquent per conubia nostra, per inceptos himenaeos. Vestibulum ut justo in nisi malesuada aliquet. \n" +
                "Nam at scelerisque magna, malesuada dictum lectus. Morbi sit amet tellus tellus. \n" +
                "Vivamus ullamcorper velit sed metus lacinia, sed luctus metus efficitur.\n");

        List<Post> postList = new ArrayList<>();
        postList.add(new Post("W wyborach miss stwierdzenie \"dać za wygraną\" nabiera zupełnie innego znaczenia..."));
        postList.add(new Post("Alkoholowi i narkotykom mówimy stanowcze: Czemu nie?!"));
        user.setPostList(postList);

        List<Skill> userSkills = new ArrayList<>();
        userSkills.add(new Skill("Java","Maven, Hibernate, Spring"));
        userSkills.add(new Skill("Frontend","Angular, HTML, CSS"));
        userSkills.add(new Skill("TDD","Junit, Mockito"));
        user.setSkills(userSkills);

        return user;
    }

    private User generateUser5(){

        User user = new User();

        user.setFirstName("Tyrion");
        user.setLastName("Lannister");
        user.setEmail("tyrionlannister@gmail.com");
        user.setPassword("0000");
        user.getRoleSet().add(Role.ROLE_USER);
        user.setSummary("Quisque sit amet justo dignissim, scelerisque eros at, sodales lectus. \n" +
                "Praesent quis tellus quis est egestas aliquet at nec lacus. \n" +
                "Aenean interdum eget elit eget tempor.");


        List<Post> postList = new ArrayList<>();
        postList.add(new Post("Spytałem mojego trenera na siłowni, z którego urządzenia mam najczęściej korzystać, żeby dziewczyny na mnie leciały. Odpowiedział, że z bankomatu..."));
        postList.add(new Post("Spotyka Edyp Syzyfa:\n" +
                "Edyp: - Yo Rolling Stones!\n" +
                "Syzyf: - Yo Motherfucker!"));
        user.setPostList(postList);


        List<Skill> userSkills = new ArrayList<>();
        userSkills.add(new Skill("Java","Maven, Hibernate, Spring"));
        userSkills.add(new Skill("Frontend","Angular, HTML, CSS"));
        userSkills.add(new Skill("TDD","Junit, Mockito"));


        user.setSkills(userSkills);

        List<FriendRequest> friendRequestList = new ArrayList<>();
        friendRequestList.add(new FriendRequest("a","b"));
        friendRequestList.add(new FriendRequest("a2","b2"));
        user.setFriendRequestsList(friendRequestList);

        return user;
    }

    private User generateUser6(){

        User user = new User();

        user.setFirstName("Jamie");
        user.setLastName("Lannister");
        user.setEmail("jamielannister@gmail.com");
        user.setPassword("0000");
        user.getRoleSet().add(Role.ROLE_USER);
        user.setSummary("Curabitur non lectus a est mollis tristique. Proin placerat odio  \n" +
                "vel erat dapibus, suscipit aliquet lectus laoreet. Quisque aliquam  \n" +
                "ut libero in hendrerit.");


        List<Post> postList = new ArrayList<>();
        postList.add(new Post("Wszystko zostaje w rodzinie..."));
        postList.add(new Post("Jak się nazywa najlepszy kurator?\n" +
                "\n" +
                "- PROkurator!"));
        user.setPostList(postList);


        List<Skill> userSkills = new ArrayList<>();
        userSkills.add(new Skill("Java","Maven, Hibernate, Spring"));
        userSkills.add(new Skill("Frontend","Angular, HTML, CSS"));
        userSkills.add(new Skill("TDD","Junit, Mockito"));


        user.setSkills(userSkills);

        List<FriendRequest> friendRequestList = new ArrayList<>();
        friendRequestList.add(new FriendRequest("a","b"));
        friendRequestList.add(new FriendRequest("a2","b2"));
        user.setFriendRequestsList(friendRequestList);

        return user;
    }

    private User generateUser7(){

        User user = new User();

        user.setFirstName("Podric");
        user.setLastName("Payne");
        user.setEmail("podricpayne@gmail.com");
        user.setPassword("0000");
        user.getRoleSet().add(Role.ROLE_USER);
        user.setSummary("Curabitur non lectus a est mollis tristique. Proin placerat odio  \n" +
                "vel erat dapibus, suscipit aliquet lectus laoreet. Quisque aliquam  \n" +
                "ut libero in hendrerit.");


        List<Post> postList = new ArrayList<>();
        postList.add(new Post("Post, post, post"));
        postList.add(new Post("Java, Maven, Hibernate"));
        user.setPostList(postList);


        List<Skill> userSkills = new ArrayList<>();
        userSkills.add(new Skill("Java","Maven, Hibernate, Spring"));
        userSkills.add(new Skill("Frontend","Angular, HTML, CSS"));
        userSkills.add(new Skill("TDD","Junit, Mockito"));


        user.setSkills(userSkills);

        List<FriendRequest> friendRequestList = new ArrayList<>();
        friendRequestList.add(new FriendRequest("a","b"));
        friendRequestList.add(new FriendRequest("a2","b2"));
        user.setFriendRequestsList(friendRequestList);

        return user;
    }

    private User generateUser8(){

        User user = new User();

        user.setFirstName("Brienne");
        user.setLastName("Tarth");
        user.setEmail("briannetarth@gmail.com");
        user.setPassword("0000");
        user.getRoleSet().add(Role.ROLE_USER);
        user.setSummary("roin vehicula ipsum at nisl tincidunt convallis. \n" +
                "Nam et ligula tortor. Donec aliquet efficitur volutpat.  \n" +
                "ut libero in hendrerit.");


        List<Post> postList = new ArrayList<>();
        postList.add(new Post("Chyba ty..."));
        postList.add(new Post("Coś tu się zepsuło"));
        user.setPostList(postList);


        List<Skill> userSkills = new ArrayList<>();
        userSkills.add(new Skill("Java","Maven, Hibernate, Spring"));
        userSkills.add(new Skill("Frontend","Angular, HTML, CSS"));
        userSkills.add(new Skill("TDD","Junit, Mockito"));


        user.setSkills(userSkills);

        List<FriendRequest> friendRequestList = new ArrayList<>();
        friendRequestList.add(new FriendRequest("a","b"));
        friendRequestList.add(new FriendRequest("a2","b2"));
        user.setFriendRequestsList(friendRequestList);

        return user;
    }

    private User generateUser9(){

        User user = new User();

        user.setFirstName("Sheldon");
        user.setLastName("Cooper");
        user.setEmail("sheldoncooper@gmail.com");
        user.setPassword("0000");
        user.getRoleSet().add(Role.ROLE_USER);
        user.setSummary("Currently enrolled in Java Coding Bootcamp \n" +
                "at Software Development Academy in Katowice\n" +
                "which I will finish in November 2017. \n" +
                "Expierienced with web development process. \n" +
                "Able to work well independently or as part \n" +
                "of a professional development team.");


        List<Post> postList = new ArrayList<>();
        postList.add(new Post("Bazinga!"));
        postList.add(new Post("Penny, Penny, Penny"));
        user.setPostList(postList);


        List<Skill> userSkills = new ArrayList<>();
        userSkills.add(new Skill("Java","Maven, Hibernate, Spring"));
        userSkills.add(new Skill("Frontend","Angular, HTML, CSS"));
        userSkills.add(new Skill("TDD","Junit, Mockito"));


        user.setSkills(userSkills);

        List<FriendRequest> friendRequestList = new ArrayList<>();
        friendRequestList.add(new FriendRequest("a","b"));
        friendRequestList.add(new FriendRequest("a2","b2"));
        user.setFriendRequestsList(friendRequestList);

        return user;
    }

}
