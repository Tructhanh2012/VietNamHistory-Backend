package com.second.version.util;

import com.second.version.auth.AuthenticationService;
import com.second.version.config.JwtService;
import com.second.version.hashtag.HashtagEntity;
import com.second.version.hashtag.HashtagRepo;
import com.second.version.user.Role;
import com.second.version.user.UserEntity;
import com.second.version.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
private final HashtagRepo hashtagRepo;
    @Override
    public void run(String... args) {
        if (userRepository.count() < 1) {
            UserEntity admin = new UserEntity();
            admin.setName("Admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("1"));
            admin.setRole(Role.ADMIN);
            var savedUser = userRepository.save(admin);
            var jwtToken = jwtService.generateToken(admin);
            authenticationService.saveUserToken(savedUser, jwtToken);


            UserEntity member = new UserEntity();
            member.setName("Member");
            member.setEmail("member@gmail.com");
            member.setPassword(passwordEncoder.encode("1"));
            member.setRole(Role.MEMBER);
            var savedMember = userRepository.save(member);
            var jwtTokenMember = jwtService.generateToken(member);
            authenticationService.saveUserToken(savedMember, jwtTokenMember);

            UserEntity editor = new UserEntity();
            editor.setName("Editor");
            editor.setEmail("editor@gmail.com");
            editor.setPassword(passwordEncoder.encode("1"));
            editor.setRole(Role.EDITOR);
            var savedEditor = userRepository.save(editor);
            var jwtTokenEditor = jwtService.generateToken(editor);
            authenticationService.saveUserToken(savedEditor, jwtTokenEditor);

            HashtagEntity hashtag = new HashtagEntity("Hồng Bàng & Văn Lang");
            HashtagEntity hashtag1 = new HashtagEntity("Âu Lạc & Nam Việt");
            HashtagEntity hashtag2 = new HashtagEntity("Bắc thuộc");
            HashtagEntity hashtag3 = new HashtagEntity("Trưng Nữ Vương");
            HashtagEntity hashtag4 = new HashtagEntity("Nhà Lý & nhà Triệu");
            HashtagEntity hashtag5 = new HashtagEntity("Nhà Ngô");
            HashtagEntity hashtag6 = new HashtagEntity("Nhà Đinh");
            HashtagEntity hashtag7 = new HashtagEntity("Nhà Tiền Lê");
            HashtagEntity hashtag8 = new HashtagEntity("Nhà Lý");
            HashtagEntity hashtag9 = new HashtagEntity("Nhà Trần");
            HashtagEntity hashtag10 = new HashtagEntity("Nhà Hồ");
            HashtagEntity hashtag11 = new HashtagEntity("Nhà Hậu Trần");
            HashtagEntity hashtag21 = new HashtagEntity("Nhà Hậu Lê");
            HashtagEntity hashtag31 = new HashtagEntity("Nam Bắc Triều");
            HashtagEntity hashtag41 = new HashtagEntity("Trịnh Nguyễn Phân Tranh");
            HashtagEntity hashtag51 = new HashtagEntity("Nhà Tây Sơn");
            HashtagEntity hashtag61 = new HashtagEntity("Nhà Nguyễn");
            HashtagEntity hashtag71 = new HashtagEntity("Pháp Thuộc");
            HashtagEntity hashtag81= new HashtagEntity("Việt Nam Dân Chủ Cộng Hoà");
            HashtagEntity hashtag91 = new HashtagEntity("Cộng hoà xã hội chủ nghĩa Việt Nam");
            hashtagRepo.save(hashtag);
            hashtagRepo.save(hashtag1);
            hashtagRepo.save(hashtag2);
            hashtagRepo.save(hashtag3);
            hashtagRepo.save(hashtag4);
            hashtagRepo.save(hashtag5);
            hashtagRepo.save(hashtag6);
            hashtagRepo.save(hashtag7);
            hashtagRepo.save(hashtag8);
            hashtagRepo.save(hashtag9);
            hashtagRepo.save(hashtag10);
            hashtagRepo.save(hashtag11);
            hashtagRepo.save(hashtag21);
            hashtagRepo.save(hashtag31);
            hashtagRepo.save(hashtag41);
            hashtagRepo.save(hashtag51);
            hashtagRepo.save(hashtag61);
            hashtagRepo.save(hashtag71);
            hashtagRepo.save(hashtag81);
            hashtagRepo.save(hashtag91);

        }
    }
}
