package com.ohara.corrida_colosseum.service;
import com.ohara.corrida_colosseum.dto.ReservationRequest;
import com.ohara.corrida_colosseum.models.Reservation;
import com.ohara.corrida_colosseum.models.User;
import org.springframework.stereotype.Service;

import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileService {
    public File createReservationFile(ReservationRequest request, User user) {
        try {
            File myFile = new File("C:\\Users\\happycash\\Desktop\\Dossier projet\\Corrida_Colosseum\\projet-fil-rouge-OharaAO\\uploads\\reserationsFile\\reservation.txt");
            if (myFile.createNewFile()) {
                System.out.println("File created" + myFile.getName());

                FileWriter fw = new FileWriter(myFile);
                fw.write("bonjour");
            }else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occured while creating the file");
            throw new RuntimeException(e);

        }
        return null;
    }
}
