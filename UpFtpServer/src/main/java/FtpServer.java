import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FtpServer {


    public static void main(String[] args) throws IOException, ClassNotFoundException, FtpException {
        FtpServerFactory serverFactory = new FtpServerFactory();

        ListenerFactory factory = new ListenerFactory();
        factory.setPort(2121); // Устанавливаем порт прослушивания

        serverFactory.addListener("default", factory.createListener());// делаем прослушиватель по умолчанию

        BaseUser user = new BaseUser();
        user.setName("admin"); // имя пользователя для Ftp-сервера

        user.setPassword("123456"); // пароль
        // домашний каталог пользователя, должен указать тот каталог с json-ом студентов
        user.setHomeDirectory("C:\\Users\\maew0\\IdeaProjects\\UpFtpServer\\src\\main\\resources");

        List<Authority> authorities = new ArrayList<Authority>();
        // увеличиваем разрешение на запись
        authorities.add(new WritePermission());
        user.setAuthorities(authorities);

        // Добавить пользователя и сохраняем
        serverFactory.getUserManager().save(user);

        }
}
