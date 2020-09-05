package bankproject.fileutill;

import bankproject.model.Deposit;
import exception.DepositBalanceException;
import exception.DepositTypeException;
import exception.DurationDaysException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

public class XmlReader implements Serializable {


    public void readFile() throws DepositBalanceException, DepositTypeException, DurationDaysException, Exception {

        File file = new File("src\\bankproject\\resources\\deposit.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        NodeList nodeList = doc.getElementsByTagName("deposit");
        List<Deposit> deposits = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Class deposit = null;
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;

                String customerNumber = String.valueOf(Long.parseLong(eElement.getElementsByTagName("customerNumber")
                        .item(0).getChildNodes().item(0).getNodeValue()));

                String depositType = String.valueOf((eElement.getElementsByTagName("depositType").item(0)
                        .getChildNodes().item(0).getNodeValue()));
                if (equals(depositType)) {
                    throw new DepositTypeException();
                }

                BigDecimal depositBalance = BigDecimal.valueOf(Long.parseLong((eElement.getElementsByTagName("depositBalance")
                        .item(0).getChildNodes().item(0).getNodeValue())));
                if (depositBalance.compareTo(BigDecimal.ZERO) < 0) {
                    throw new DepositBalanceException();
                }

                int durationInDays = (int) Long.parseLong(eElement.getElementsByTagName("durationInDays")
                        .item(0).getChildNodes().item(0).getNodeValue());
                if (durationInDays <= 0) {
                    throw new DurationDaysException();
                }

                Object obj = null;
                switch (depositType) {

                    case "LongTerm":
                        deposit = Class.forName("bankproject.model.LongTerm");
                        break;
                    case "ShortTerm":
                        deposit = Class.forName("bankproject.model.ShortTerm");

                        break;
                    case "Qarz":
                        deposit = Class.forName("bankproject.model.QarzolHasane");
                        break;
                }
                obj = deposit.newInstance();

                Field customerNumberField = deposit.getField("customerNumber");
                customerNumberField.set(obj, customerNumber);

                Field depositTypeField = deposit.getField("depositType");
                depositTypeField.set(obj, depositType);

                Field depositBalanceField = deposit.getField("depositBalance");
                depositBalanceField.set(obj, depositBalance);

                Field durationInDaysField = deposit.getField("durationInDays");
                durationInDaysField.set(obj, durationInDays);

                Method method = deposit.getMethod("payedInterest");
                method.invoke(obj);
                System.out.println("mizan sood pardakhti: " + method.invoke(obj) + " #" + customerNumber);

                deposits.add((Deposit) obj);
            }
        }
        for (Deposit de : deposits)
            System.out.println(de);
        new Writer(deposits);
    }
}



