import repository.OfferDBRepo;
import repository.OfferRepo;
import repository.SetRepo;
import repository.UserDBRepo;
import service.Service;
import ui.Ui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {

        String url="jdbc:mysql://localhost:3306/car_dealership";
        Connection conn = DriverManager.getConnection(url, "root", "Topeleven123");

        UserDBRepo userrepo = new UserDBRepo(conn);
        OfferDBRepo offerrepo = new OfferDBRepo(conn,userrepo);

        Service service = new Service(userrepo,offerrepo);
        Ui ui = new Ui(service);
        ui.meniu();

    }
}
