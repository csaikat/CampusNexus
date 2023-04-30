package edu.in.mckvie.CampusNexus.services;

public interface JwtBlacklistService {


        void blacklistToken(String token,String username);

        boolean isTokenBlacklisted(String token,String username);
        public void add(String token,String username);
        public boolean contains(String token,String username);



}
