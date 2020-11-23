package in.ac.bitspilani.webapp.registration;

import org.springframework.web.bind.annotation.RequestMapping;

public class SignIn {
    private String username;
    private String password;
    private boolean status;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStatus() {
        if (username.equals("Sahithi")&&password.equals("sahithi")) {
            status = true;
        }
        else
        {
            status=true;
        }
    }
    @RequestMapping
    public String next()
    {
        if(status)
        {
            return "dashboard/dashboard";
        }
        return "/error";
    }
}
