package ba.smoki.four;

public class Demo {
    public static void main(String[] args) {
        try {
            User user = new User();
            user.setName("Kemal");
            Runner runner = new Runner();
            runner.run(user);
        }catch (UserGreska e){
            System.err.println(e.getMessage());
        }
    }
}
