import config.Config;
import config.ObjectFile;
import controlador.ClientController;
import controlador.MainViewController;
import model.ProjectManager;
import views.AuthenticationView;
import views.MainView;

import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * Classe principal del programa cliente (La parte del server no está acabada, por eso está comentada)
 * Created by Marc on 13/3/18
 */
public class Main {

    public static void main(String[] args){


        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                Config data;
                ObjectFile objData = new ObjectFile();

                try {
                    data = objData.readData();

                    AuthenticationView authenticationView = new AuthenticationView();                  //views de autenticación
                    MainView mainView = new MainView();
                    ProjectManager projectManager = new ProjectManager();                              //modelo
                    //ServerCommunication serverCommunication = new ServerCommunication(projectManager); //network
                    //serverCommunication.startConnection();

                    ClientController clientController= new ClientController(authenticationView, projectManager, data);    //controlador
                    MainViewController mainViewController = new MainViewController(mainView);

                    authenticationView.registerController(clientController);    //Relación controlador --> views
                    mainView.registerController(mainViewController);

                    authenticationView.setVisible(false);        //Se hace visible la views de autenticación
                    mainView.setVisible(true);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
