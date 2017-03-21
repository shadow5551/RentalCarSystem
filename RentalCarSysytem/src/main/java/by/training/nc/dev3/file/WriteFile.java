package main.java.by.training.nc.dev3.file;

import main.java.by.training.nc.dev3.interfaces.Writer;
import main.java.by.training.nc.dev3.model.Order;

import java.io.*;
import java.util.List;

/**
 * Created by dima on 16.3.17.
 */
public class WriteFile implements Writer {

    @Override
    public void writeItem(Object object, String nameOfFile) {
        File file = new File (nameOfFile);
        ObjectOutputStream out = null;
        try{
            if (!file.exists()) out = new ObjectOutputStream (new FileOutputStream (nameOfFile));
            else out = new AppendableObjectOutputStream (new FileOutputStream (nameOfFile, true));
            out.writeObject(object);
            out.flush ();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if (out != null) out.close ();
            }catch (Exception e){
                e.printStackTrace ();
            }
        }
    }



    @Override
    public void writeCollection(List<Order> list, String nameOfFile) {
        for (Order order:list){
            writeItem(order,nameOfFile);
        }
    }

    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {}
    }
}
