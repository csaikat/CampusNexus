package edu.in.mckvie.CampusNexus.services;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public interface InvoiceService {
    public byte[] generateAndServeInvoice() throws FileNotFoundException, JRException;
    public String generateInvoice(int userId,int semId) throws IOException, JRException, ParseException;
}
