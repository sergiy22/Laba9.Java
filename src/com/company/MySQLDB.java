
package com.company;


import java.sql.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.company.DBInstance;




public class MySQLDB {


    /**
     * @param args the command line arguments
     */
    

    static ArrayList<JLabel> newLabels() {
        ArrayList<JLabel> labels = new ArrayList<>();

        JLabel label6 = new JLabel();
        label6.setText("Enter Name :");
        label6.setBounds(10, 180, 100, 100);
        labels.add(label6);

        JLabel label8 = new JLabel();
        label8.setText("Enter Name :");
        label8.setBounds(300, 180, 100, 100);
        labels.add(label8);

        JLabel label12 = new JLabel();
        label12.setText("Enter Name :");
        label12.setBounds(10, 310, 100, 100);
        labels.add(label12);

        JLabel label2 = new JLabel();
        label2.setText("Enter Name :");
        label2.setBounds(10, 50, 100, 100);
        labels.add(label2);

        JLabel label4 = new JLabel();
        label4.setText("Enter Name :");
        label4.setBounds(300, 50, 100, 100);
        labels.add(label4);

        JLabel Singers = new JLabel();
        Singers.setText("Singers");
        Singers.setBounds(109, -20, 100, 100);
        labels.add(Singers);
        JLabel Albums = new JLabel();
        Albums.setText("Albums");
        Albums.setBounds(400, -20, 100, 100);
        labels.add(Albums);

        JLabel Playlists = new JLabel();
        Playlists.setText("Playlists");
        Playlists.setBounds(109, 100, 100, 100);
        labels.add(Playlists);

        JLabel Songs = new JLabel();
        Songs.setText("Songs");
        Songs.setBounds(400, 100, 100, 100);
        labels.add(Songs);

        JLabel Users = new JLabel();
        Users.setText("Users");
        Users.setBounds(109, 230, 100, 100);
        labels.add(Users);

        //empty label which will show event after button clicked
        JLabel label1 = new JLabel();
        label1.setBounds(10, 110, 200, 100);
        labels.add(label1);

        return labels;
    }

    static ArrayList<JTextField> newTextFields() {
        ArrayList<JTextField> textfields = new ArrayList<>();

        JTextField textfield1 = new JTextField();
        textfield1.setBounds(110, 90, 130, 30);
        textfields.add(textfield1);

        JTextField textfield3 = new JTextField();
        textfield3.setBounds(400, 90, 130, 30);
        textfields.add(textfield3);

        JTextField textfield5 = new JTextField();
        textfield5.setBounds(110, 210, 130, 30);
        textfields.add(textfield5);

        JTextField textfield7 = new JTextField();
        textfield7.setBounds(400, 210, 130, 30);
        textfields.add(textfield7);

        JTextField textfield11 = new JTextField();
        textfield11.setBounds(110, 340, 130, 30);
        textfields.add(textfield11);

        return textfields;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        JFrame f = new JFrame("Button Example");

        JButton b = new JButton("Submit");
        b.setBounds(20, 400, 540, 40);
        ArrayList<JLabel> labels = newLabels();
        ArrayList<JTextField> textfields = newTextFields();

        //add to frame
        for (int i = 0; i < labels.size(); ++i) {
            f.add(labels.get(i));
        }

        for (int i = 0; i < textfields.size(); ++i) {
            f.add(textfields.get(i));
        }

        f.add(b);
        f.setSize(600, 500);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//action listener
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                PlaylistFrame newFrame = new PlaylistFrame();
                newFrame.setVisible(true);
                
                

                
                    ArrayList<String> fields = new ArrayList<>();
                    fields.add("NAME");
                    ArrayList<Object> values = new ArrayList<>();
                    values.add(textfields.get(0).getText());
                    Connection connection = DBInstance.getInstance();
                    int singerId = createRecord(connection, "Singers", fields, values); //найдите прикол ))) тут короче нужно добавить методі DBInstance 

                    ArrayList<String> albumFields = new ArrayList<>();
                    albumFields.add("NAME");
                    albumFields.add("SingerId");
                    ArrayList<Object> albumValues = new ArrayList<>();
                    albumValues.add(textfields.get(1).getText());
                    albumValues.add(singerId);
                    int albumId = createRecord(connection, "Albums", albumFields, albumValues);

                    ArrayList<String> playlistsFields = new ArrayList<>();
                    playlistsFields.add("NAME");
                    //playlistsFields.add("PlaylistId");
                    ArrayList<Object> playlistsValues = new ArrayList<>();
                    playlistsValues.add(textfields.get(2).getText());
                    //playlistsValues.add(playlistId); // должно добавляться в songsPlaylists
                    int playlistId = createRecord(connection, "Playlists", playlistsFields, playlistsValues);

                    ArrayList<String> songsFields = new ArrayList<>();
                    songsFields.add("NAME");
                    songsFields.add("AlbumId");
                    ArrayList<Object> songsValues = new ArrayList<>();
                    songsValues.add(textfields.get(3).getText());
                    songsValues.add(albumId);
                    int songId = createRecord(connection, "Songs", songsFields, songsValues);

                    ArrayList<String> usersFields = new ArrayList<>();
                    usersFields.add("NAME");
                    //albumFields.add("SingerId");
                    ArrayList<Object> usersValues = new ArrayList<>();
                    usersValues.add(textfields.get(4).getText());
                    //usersValues.add(singerId);
                    int userId = createRecord(connection, "Users", usersFields, usersValues);

                    ArrayList<String> songsplaylistsFields = new ArrayList<>();
                    songsplaylistsFields.add("PlaylistId");
                    songsplaylistsFields.add("SongId");
                    ArrayList<Object> songsplaylistsFValues = new ArrayList<>();
                    songsplaylistsFValues.add(playlistId);
                    songsplaylistsFValues.add(songId);
                    int songsplaylistsId = createRecord(connection, "SongsPlaylists", songsplaylistsFields, songsplaylistsFValues);

                    ArrayList<String> usersplaylistsFields = new ArrayList<>();
                    usersplaylistsFields.add("PlaylistId");
                    usersplaylistsFields.add("UserId");
                    ArrayList<Object> usersplaylistsValues = new ArrayList<>();
                    usersplaylistsValues.add(playlistId);
                    usersplaylistsValues.add(userId);
                    int usersplaylistsId = createRecord(connection, "UsersPlaylists", usersplaylistsFields, usersplaylistsValues);

                
            }
        });

    }


}