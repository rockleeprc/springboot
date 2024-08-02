package org.example7.instance;

public class UserController {
    @Autowired(value = "AAA", name = "CCC")
    private UserService userService;

    public String message = "message";

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    private void method1() {
    }
}
