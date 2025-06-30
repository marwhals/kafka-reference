package specific;

import com.example.Customer; //Imported from target folder. Maven generated avro java file
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

/**
 * Step 1: create a specific records
 * Step 2: Write to file
 * Step 3: Read from file
 * Step 4: Interpret
 */

public class SpecificRecordExamples {

    public static void main(String[] args) {

        /**
         * Step 1: Create specific record
         */
        // we can now build a customer in a "safe" way
        Customer.Builder customerBuilder = Customer.newBuilder();
        customerBuilder.setAge(30);
        customerBuilder.setFirstName("Mark");
        customerBuilder.setLastName("Simpson");
        customerBuilder.setAutomatedEmail(true);
        customerBuilder.setHeight(180f);
        customerBuilder.setWeight(90f);

        Customer customer = customerBuilder.build();
        System.out.println(customer.toString());


        /**
         * Step 2: Write to a file
         */
        // write it out to a file
        final DatumWriter<Customer> datumWriter = new SpecificDatumWriter<>(Customer.class);

        try (DataFileWriter<Customer> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(customer.getSchema(), new File("customer-specific.avro"));
            dataFileWriter.append(customer);
            System.out.println("successfully wrote customer-specific.avro");
        } catch (IOException e){
            e.printStackTrace();
        }


        /**
         * Step 3: Read from a file
         */
        // read it from a file
        final File file = new File("customer-specific.avro");
        final DatumReader<Customer> datumReader = new SpecificDatumReader<>(Customer.class);
        final DataFileReader<Customer> dataFileReader;
        try {
            System.out.println("Reading our specific record");
            dataFileReader = new DataFileReader<>(file, datumReader);
            while (dataFileReader.hasNext()) {
                Customer readCustomer = dataFileReader.next();
                // step 4: interpret
                System.out.println(readCustomer.toString());
                System.out.println("First name: " + readCustomer.getFirstName());
                Float height = readCustomer.getHeight(); // Could use a primitive type for gainz XD
                System.out.println(Float.toString(height)); // Can be auto refactored
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // note, we can read our other customer generated using the generic method
        // end of the day, no matter the method, Avro is Avro
        final File fileGeneric = new File("customer-generic.avro");
        final DatumReader<Customer> datumReaderGeneric = new SpecificDatumReader<>(Customer.class);
        final DataFileReader<Customer> dataFileReaderGeneric;
        try {
            System.out.println("Reading our specific record");
            dataFileReaderGeneric = new DataFileReader<>(fileGeneric, datumReaderGeneric);
            while (dataFileReaderGeneric.hasNext()) {
                Customer readCustomer = dataFileReaderGeneric.next();
                System.out.println(readCustomer.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}