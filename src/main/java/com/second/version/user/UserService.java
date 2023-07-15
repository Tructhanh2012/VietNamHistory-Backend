package com.second.version.user;

import com.second.version.article.ArticleEntity;
import com.second.version.article.ArticleRepo;
import com.second.version.dto.request.CreateEditorRequest;
import com.second.version.dto.request.UpdateProfileRequest;
import com.second.version.result.ResultEntity;
import com.second.version.result.ResultRepo;
import com.second.version.token.Token;
import com.second.version.token.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ArticleRepo articleRepo;
    private ResultRepo resultRepo;
    private TokenRepository tokenRepository;
    public UserEntity editUser(UpdateProfileRequest updateProfileRequest){
        UserEntity user = userRepository.findById(updateProfileRequest.getId());
        user.setName(updateProfileRequest.getName());
     //   user.setPassword(passwordEncoder.encode(updateProfileRequest.getPassword()));
        return userRepository.save(user);
    }
    public UserEntity createEditor(CreateEditorRequest createEditorRequest){
        UserEntity user = userRepository.findByMail(createEditorRequest.getEmail());
        if(user == null){
            UserEntity editor = new UserEntity();
            editor.setName(createEditorRequest.getName());
            editor.setEmail(createEditorRequest.getEmail());
            editor.setPassword(passwordEncoder.encode(createEditorRequest.getPassword()));
            editor.setRole(Role.EDITOR);
            return userRepository.save(editor);
        }
        return user;
    }
    public void deleteById(long id){
        UserEntity user = userRepository.findById(id);
        if(user.getRole() == Role.EDITOR){
            List<ArticleEntity> articleEntities = articleRepo.findArticleEntitiesByEditor_Id(user.getId());
            for(ArticleEntity article: articleEntities){
                articleRepo.delete(article);
            }

        }else if(user.getRole() == Role.MEMBER){
            List<ResultEntity> resultEntities = resultRepo.findResultEntitiesByMember_Id(user.getId());
            for(ResultEntity resultEntity: resultEntities){
                resultRepo.delete(resultEntity);
            }
        }
        tokenRepository.deleteTokenByUserId(user.getId());
        userRepository.delete(user);
    }
}
