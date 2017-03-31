/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;

/**
 *
 * @author aapo
 */
public class FileUserDao implements UserDao {

    private File userfile;

    public FileUserDao(String fileName) {
        userfile = new File(fileName);

    }

    @Override
    public List<User> listAll() {
        try {
            Scanner scanner = new Scanner(userfile);
            List<User> users = new ArrayList<User>();
            while (scanner.hasNextLine()) {

                String rivi = scanner.nextLine();
                if (!rivi.equals("")) {
                    String username = rivi.split(",")[0];
                    String passwd = rivi.split(",")[1];
                    User u = new User(username, passwd);
                    users.add(u);
                }
            }
            scanner.close();
            return (users);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public User findByName(String name) {
        try {
            Scanner scanner = new Scanner(userfile);
            while (scanner.hasNextLine()) {

                String rivi = scanner.nextLine();
                String username = rivi.split(",")[0];
                String passwd = rivi.split(",")[1];
                if (username.equals(name)) {
                    return new User(username, passwd);
                }
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (null);
    }

    @Override
    public void add(User user) {

        try {
            FileWriter writer = new FileWriter(userfile, true);
            writer.write(user.getUsername() + "," + user.getPassword() + "\n");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
