package ba.smoki.four;

import java.awt.*;

public class Runner {

    public void run(User user) throws UserGreska {
        if(user == null){
            throw new UserGreska("Nema usera koji bi trcao");
        }
        if(user.getName() == null){
            throw new UserGreska("User nema ime i ne može trčat.");
        }
        System.out.println("User sa imenom : " + user.getName()+"  trči ko metak");
    }
}
