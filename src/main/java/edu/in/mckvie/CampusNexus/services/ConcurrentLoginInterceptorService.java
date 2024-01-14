package edu.in.mckvie.CampusNexus.services;

public interface ConcurrentLoginInterceptorService {
    public void saveData(String key, String value);

    public String getData(String key);

    public boolean contain(String key);

    public void deleteData(String key);
    public void blacklistToken(String key);
}
