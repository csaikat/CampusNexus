package edu.in.mckvie.CampusNexus.utils;

import java.util.Random;

public class OtpGenerator {
//    String systemOTP, userOTP;


    // Use getOTP( ) method to generate random OTP
    public static String getOTP(int len) {
         String s = "";
        int ranNo;
        // Use for loop to iterate 4 times and generate random OTP
        for (int i = 0; i < 6; i++) {
            // Generate random digit within 0-9
            ranNo = new Random().nextInt(9);
            s = s.concat(Integer.toString(ranNo));
        }
        // Return the generated OTP
        return s;
    }
}
