package cz.nigol.zpravodaj.dev;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.mindrot.jbcrypt.BCrypt;

import cz.nigol.zpravodaj.entities.User;
import cz.nigol.zpravodaj.enums.Role;
import cz.nigol.zpravodaj.services.UserService;

@Named
@RequestScoped
public class PrepareDevData {
    @Inject
    private UserService userService;

    public void createData() {
        User user = new User();
        user.setId("aaa");
        user.setFullName("Rum Bum");
        user.setRole(Role.EDITOR);
        user.setPassword(BCrypt.hashpw("aaa", BCrypt.gensalt()));
        user.setActive(true);
        userService.saveUser(user);
    }
}
