package com.anudeep.inputoutput;

import com.anudeep.creditcard.CreditCard;

import java.util.List;

public interface RecordInputOutput {
    List<CreditCard> readFile(String filename);

    boolean writeFile(String filename, List<CreditCard> records);
}