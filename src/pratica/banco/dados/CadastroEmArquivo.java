package pratica.banco.dados;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CadastroEmArquivo<Object> {

    private ObjectInputStream input;
    private ObjectOutputStream output;

    public void opentoRead(String fileName) {
        try {
            FileInputStream fileinputstream = new FileInputStream(fileName);
            input = new ObjectInputStream(fileinputstream);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public void openToWrite(String fileName) {
        try {
            FileOutputStream fileoutputstream = new FileOutputStream(fileName);
            output = new ObjectOutputStream(fileoutputstream);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public void closeAfterRead() {
        try {
            if (input != null) {
                input.close();
                input = null;
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public void closeAfterWrite() {
        try {
            if (output != null) {
                output.close();
                output = null;
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public void writeObjectOnFile (Object object) {
        try {
            if (output != null) {
                output.writeObject(object);
                output.flush();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public Object readObjectFromFile () {
        Object object;
        try {
            if (input != null) {
                object = (Object) input.readObject();
                return object;
            }
        } catch (EOFException ex) {
//            System.err.println("EOF");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        return null;
    }

}
