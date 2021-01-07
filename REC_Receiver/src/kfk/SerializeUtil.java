package kfk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            oos.close();
            baos.close();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.toString());
        }
        return null;
    }
 
    @SuppressWarnings("unchecked")
    public static <T> Object deserialize(byte[] bytes,Class<T> className) {
        ByteArrayInputStream bais = null;
        T tmpObject = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            tmpObject = (T)ois.readObject();
            ois.close();
            bais.close();
            return tmpObject;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.toString());
        }
        return null;
    }
}

