package bankproject.fileutill;

import bankproject.model.Deposit;
import bankproject.model.LongTerm;
import bankproject.model.ShortTerm;

import java.beans.XMLEncoder;

import java.io.*;
import java.util.*;


public class Writer implements Serializable {
    private List<Deposit> deposits;
    private Comparator comparator = Collections.reverseOrder();

    public Writer(List<Deposit> deposits) throws IOException {

        FileWriter fileWriter = new FileWriter("C:\\Download\\project1.txt");

        deposits.sort(comparator);
        for (Deposit str : deposits) {
            fileWriter.write(str.getCustomerNumber() + "#" + str.payedInterest() + System.lineSeparator());
        }

        fileWriter.close();
    }
}

