package edu.in.mckvie.CampusNexus.utils;

import java.util.Random;

public class OtpGenerator {
    String systemOTP, userOTP;
    static String s = "";
    static int ranNo;

    // Use getOTP( ) method to generate random OTP
    public static String getOTP(int len) {
        // Use for loop to iterate 4 times and generate random OTP
        for (int i = 0; i < len; i++) {
            // Generate random digit within 0-9
            ranNo = new Random().nextInt(9);
            s = s.concat(Integer.toString(ranNo));
        }
        // Return the generated OTP
        return s;
    }
}
