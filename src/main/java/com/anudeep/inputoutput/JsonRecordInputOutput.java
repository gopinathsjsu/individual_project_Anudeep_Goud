package com.anudeep.inputoutput;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.anudeep.creditcard.CreditCard;
import com.anudeep.creditcard.CreditCardFactory;
import com.anudeep.creditcard.OutputRecord;
import com.anudeep.inputoutput.RecordInputOutput;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonRecordInputOutput implements RecordInputOutput {

    @Override
    public List<CreditCard> readFile(String filename) {
        List<CreditCard> records = new ArrayList<>();
        File file = new File(filename);
        try (Reader reader = new FileReader(file)) {
            MappingIterator<CreditCard> mi = getJsonReader().readValues(reader);
            while (mi.hasNext()) {
                CreditCard current = mi.next();
                records.add(current);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public boolean writeFile(String filename, List<CreditCard> records) {
        File file = new File(filename);
        List<OutputRecord> outputRecordList = getOutputRecords(records);
        try {
            getJsonMapper().writeValue(file, outputRecordList);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private ObjectReader getJsonReader() {
        return getJsonMapper().readerFor(CreditCard.class);
    }

    private JsonMapper getJsonMapper() {
        JsonMapper mapper = new JsonMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }

    private List<OutputRecord> getOutputRecords(List<CreditCard> creditCards) {
        return creditCards
                .stream()
                .map(r -> {
                    String number = r.getCardNumber();
                    try {
                        return new OutputRecord(
                                number,
                                new CreditCardFactory().getCreditCard(number).toString());
                    } catch (UnsupportedOperationException e) {
                        return new OutputRecord(
                                number,
                                e.getMessage()
                        );
                    }
                })
                .collect(Collectors.toList());
    }
}