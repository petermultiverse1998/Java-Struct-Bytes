package struct_bytes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface StructBytes {
    default void setStructBytes(byte[] bytes) throws IllegalAccessException {
        setStructBytes(this,bytes);
    }

    default int getStructSize() throws IllegalAccessException {
        return getStructSize(this);
    }

    default List<DataType> getStructure() throws IllegalAccessException {
        return getStructure(this);
    }

    default byte[] getStructBytes() throws IllegalAccessException {
        return getStructBytes(this);
    }

    static int getStructSize(Object object) throws IllegalAccessException {
        int total_size = 0;
        for (java.lang.reflect.Field field : object.getClass().getDeclaredFields()) {
            String type = field.getType().getTypeName();
            int eachSize = 0;
            int count = 0;
            field.setAccessible(true);
            Object value = field.get(object);
            switch (type) {
                /* Bytes */
                case "byte", "java.lang.Byte" -> {
                    eachSize = Byte.BYTES;
                    count = 1;
                }
                case "byte[]" -> {
                    eachSize = Byte.BYTES;
                    count = ((byte[]) value).length;
                }
                case "java.lang.Byte[]" -> {
                    eachSize = Byte.BYTES;
                    count = ((Byte[]) value).length;
                }

                /* Short */
                case "short", "java.lang.Short" -> {
                    eachSize = Short.BYTES;
                    count = 1;
                }
                case "short[]" -> {
                    eachSize = Short.BYTES;
                    count = ((short[]) value).length;
                }
                case "java.lang.Short[]" -> {
                    eachSize = Short.BYTES;
                    count = ((Short[]) value).length;
                }

                /* Int */
                case "int", "java.lang.Integer" -> {
                    eachSize = Integer.BYTES;
                    count = 1;
                }
                case "int[]" -> {
                    eachSize = Integer.BYTES;
                    count = ((int[]) value).length;
                }
                case "java.lang.Integer[]" -> {
                    eachSize = Integer.BYTES;
                    count = ((Integer[]) value).length;
                }

                /* Long */
                case "long", "java.lang.Long" -> {
                    eachSize = Long.BYTES;
                    count = 1;
                }
                case "long[]" -> {
                    eachSize = Long.BYTES;
                    count = ((long[]) value).length;
                }
                case "java.lang.Long[]" -> {
                    eachSize = Long.BYTES;
                    count = ((Long[]) value).length;
                }

                /* Float */
                case "float", "java.lang.Float" -> {
                    eachSize = Float.BYTES;
                    count = 1;
                }
                case "float[]" -> {
                    eachSize = Float.BYTES;
                    count = ((float[]) value).length;
                }
                case "java.lang.Float[]" -> {
                    eachSize = Float.BYTES;
                    count = ((Float[]) value).length;
                }

                /* Double */
                case "double", "java.lang.Double" -> {
                    eachSize = Double.BYTES;
                    count = 1;
                }
                case "double[]" -> {
                    eachSize = Double.BYTES;
                    count = ((double[]) value).length;
                }
                case "java.lang.Double[]" -> {
                    eachSize = Double.BYTES;
                    count = ((Double[]) value).length;
                }

                /* Char !!! For C compatibility size of char is taken 1 byte*/
                case "char", "java.lang.Character" -> {
                    eachSize = 1;//!!! For C compatibility size of char is taken 1 byte
                    count = 1;
                }
                case "char[]" -> {
                    eachSize = 1;
                    count = ((char[]) value).length;
                }
                case "java.lang.Character[]" -> {
                    eachSize = 1;
                    count = ((Character[]) value).length;
                }

                /* String */
                case "String"->{
                    eachSize = 1;
                    count = ((String) value).length();
                }

            }

            if(total_size!=0 && total_size%eachSize!=0) {
                /*Add Empty Bytes */
                total_size += (eachSize - total_size % eachSize);
            }
            total_size+=eachSize*count;
        }

        if(total_size%4!=0){
            /* Add empty bytes */
            total_size+=(4-total_size%4);
        }
        return total_size;
    }

    static List<DataType> getStructure(Object object) throws IllegalAccessException {
        List<DataType> types = new ArrayList<>();
        int total_size = 0;
        for (java.lang.reflect.Field field : object.getClass().getDeclaredFields()) {
            String type = field.getType().getTypeName();
            int eachSize = 0;
            int count = 0;
            field.setAccessible(true);
            Object value = field.get(object);
            switch (type) {
                /* Bytes */
                case "byte", "java.lang.Byte" -> {
                    eachSize = Byte.BYTES;
                    count = 1;
                }
                case "byte[]" -> {
                    eachSize = Byte.BYTES;
                    count = ((byte[]) value).length;
                }
                case "java.lang.Byte[]" -> {
                    eachSize = Byte.BYTES;
                    count = ((Byte[]) value).length;
                }

                /* Short */
                case "short", "java.lang.Short" -> {
                    eachSize = Short.BYTES;
                    count = 1;
                }
                case "short[]" -> {
                    eachSize = Short.BYTES;
                    count = ((short[]) value).length;
                }
                case "java.lang.Short[]" -> {
                    eachSize = Short.BYTES;
                    count = ((Short[]) value).length;
                }

                /* Int */
                case "int", "java.lang.Integer" -> {
                    eachSize = Integer.BYTES;
                    count = 1;
                }
                case "int[]" -> {
                    eachSize = Integer.BYTES;
                    count = ((int[]) value).length;
                }
                case "java.lang.Integer[]" -> {
                    eachSize = Integer.BYTES;
                    count = ((Integer[]) value).length;
                }

                /* Long */
                case "long", "java.lang.Long" -> {
                    eachSize = Long.BYTES;
                    count = 1;
                }
                case "long[]" -> {
                    eachSize = Long.BYTES;
                    count = ((long[]) value).length;
                }
                case "java.lang.Long[]" -> {
                    eachSize = Long.BYTES;
                    count = ((Long[]) value).length;
                }

                /* Float */
                case "float", "java.lang.Float" -> {
                    eachSize = Float.BYTES;
                    count = 1;
                }
                case "float[]" -> {
                    eachSize = Float.BYTES;
                    count = ((float[]) value).length;
                }
                case "java.lang.Float[]" -> {
                    eachSize = Float.BYTES;
                    count = ((Float[]) value).length;
                }

                /* Double */
                case "double", "java.lang.Double" -> {
                    eachSize = Double.BYTES;
                    count = 1;
                }
                case "double[]" -> {
                    eachSize = Double.BYTES;
                    count = ((double[]) value).length;
                }
                case "java.lang.Double[]" -> {
                    eachSize = Double.BYTES;
                    count = ((Double[]) value).length;
                }

                /* Char !!! For C compatibility size of char is taken 1 byte*/
                case "char", "java.lang.Character" -> {
                    eachSize = 1;//!!! For C compatibility size of char is taken 1 byte
                    count = 1;
                }
                case "char[]" -> {
                    eachSize = 1;
                    count = ((char[]) value).length;
                }
                case "java.lang.Character[]" -> {
                    eachSize = 1;
                    count = ((Character[]) value).length;
                }

                /* String */
                case "String"->{
                    eachSize = 1;
                    count = ((String) value).length();
                }

            }

            if(total_size!=0 && total_size%eachSize!=0) {
                /*Add Empty Bytes */
                types.add(new DataType("empty",(eachSize - total_size % eachSize),1,'\0'));
                total_size += (eachSize - total_size % eachSize);
            }
            types.add(new DataType(type,eachSize,count,value));
            total_size+=eachSize*count;
        }

        if(total_size%4!=0){
            /* Add empty bytes */
            types.add(new DataType("empty",(4 - total_size % 4),1,'\0'));
            total_size+=(4-total_size%4);
        }
        return types;
    }

    static byte[] getStructBytes(Object object) throws IllegalAccessException {
        List<DataType> types = new ArrayList<>();
        int total_size = 0;
        for (java.lang.reflect.Field field : object.getClass().getDeclaredFields()) {
            String type = field.getType().getTypeName();
            int eachSize = 0;
            int count = 0;
            field.setAccessible(true);
            Object value = field.get(object);
            switch (type) {
                /* Bytes */
                case "byte", "java.lang.Byte" -> {
                    eachSize = Byte.BYTES;
                    count = 1;
                }
                case "byte[]" -> {
                    eachSize = Byte.BYTES;
                    count = ((byte[]) value).length;
                }
                case "java.lang.Byte[]" -> {
                    eachSize = Byte.BYTES;
                    count = ((Byte[]) value).length;
                }

                /* Short */
                case "short", "java.lang.Short" -> {
                    eachSize = Short.BYTES;
                    count = 1;
                }
                case "short[]" -> {
                    eachSize = Short.BYTES;
                    count = ((short[]) value).length;
                }
                case "java.lang.Short[]" -> {
                    eachSize = Short.BYTES;
                    count = ((Short[]) value).length;
                }

                /* Int */
                case "int", "java.lang.Integer" -> {
                    eachSize = Integer.BYTES;
                    count = 1;
                }
                case "int[]" -> {
                    eachSize = Integer.BYTES;
                    count = ((int[]) value).length;
                }
                case "java.lang.Integer[]" -> {
                    eachSize = Integer.BYTES;
                    count = ((Integer[]) value).length;
                }

                /* Long */
                case "long", "java.lang.Long" -> {
                    eachSize = Long.BYTES;
                    count = 1;
                }
                case "long[]" -> {
                    eachSize = Long.BYTES;
                    count = ((long[]) value).length;
                }
                case "java.lang.Long[]" -> {
                    eachSize = Long.BYTES;
                    count = ((Long[]) value).length;
                }

                /* Float */
                case "float", "java.lang.Float" -> {
                    eachSize = Float.BYTES;
                    count = 1;
                }
                case "float[]" -> {
                    eachSize = Float.BYTES;
                    count = ((float[]) value).length;
                }
                case "java.lang.Float[]" -> {
                    eachSize = Float.BYTES;
                    count = ((Float[]) value).length;
                }

                /* Double */
                case "double", "java.lang.Double" -> {
                    eachSize = Double.BYTES;
                    count = 1;
                }
                case "double[]" -> {
                    eachSize = Double.BYTES;
                    count = ((double[]) value).length;
                }
                case "java.lang.Double[]" -> {
                    eachSize = Double.BYTES;
                    count = ((Double[]) value).length;
                }

                /* Char !!! For C compatibility size of char is taken 1 byte*/
                case "char", "java.lang.Character" -> {
                    eachSize = 1;//!!! For C compatibility size of char is taken 1 byte
                    count = 1;
                }
                case "char[]" -> {
                    eachSize = 1;
                    count = ((char[]) value).length;
                }
                case "java.lang.Character[]" -> {
                    eachSize = 1;
                    count = ((Character[]) value).length;
                }

                /* String */
                case "String"->{
                    eachSize = 1;
                    count = ((String) value).length();
                }

            }

            if(total_size!=0 && total_size%eachSize!=0) {
                /*Add Empty Bytes */
                types.add(new DataType("empty",(eachSize - total_size % eachSize),1,'\0'));
                total_size += (eachSize - total_size % eachSize);
            }
            types.add(new DataType(type,eachSize,count,value));
            total_size+=eachSize*count;
        }

        if(total_size%4!=0){
            /* Add empty bytes */
            types.add(new DataType("empty",(4 - total_size % 4),1,'\0'));
            total_size+=(4-total_size%4);
        }

        ByteBuffer buffer = ByteBuffer.allocate(total_size).order(ByteOrder.LITTLE_ENDIAN);
        for(DataType dataType:types){
            String type = dataType.getType();
            Object value = dataType.getValue();
            int eachSize = dataType.getEachSize();

            switch (type) {
                /* Bytes */
                case "byte", "java.lang.Byte" -> buffer.put((byte) value);
                case "byte[]" -> buffer.put((byte[]) value);
                case "java.lang.Byte[]" -> {
                    for(byte x:(Byte[])value)
                        buffer.put(x);
                }

                /* Short */
                case "short", "java.lang.Short" -> buffer.putShort((short) value);
                case "short[]" -> {
                    for(short x:(short[])value)
                        buffer.putShort(x);
                }
                case "java.lang.Short[]" -> {
                    for(short x:(Short[])value)
                        buffer.putShort(x);
                }

                /* Int */
                case "int", "java.lang.Integer" -> buffer.putInt((int) value);
                case "int[]" -> {
                    for(int x:(int[])value)
                        buffer.putInt(x);
                }
                case "java.lang.Integer[]" -> {
                    for(int x:(Integer[])value)
                        buffer.putInt(x);
                }

                /* Long */
                case "long", "java.lang.Long" -> buffer.putLong((long) value);
                case "long[]" -> {
                    for(long x:(long[])value)
                        buffer.putLong(x);
                }
                case "java.lang.Long[]" -> {
                    for(long x:(Long[])value)
                        buffer.putLong(x);
                }

                /* Float */
                case "float", "java.lang.Float" -> buffer.putFloat((float) value);
                case "float[]" -> {
                    for(float x:(float[])value)
                        buffer.putFloat(x);
                }
                case "java.lang.Float[]" -> {
                    for(float x:(Float[])value)
                        buffer.putFloat(x);
                }

                /* Double */
                case "double", "java.lang.Double" -> buffer.putDouble((double) value);
                case "double[]" -> {
                    for(double x:(double[])value)
                        buffer.putDouble(x);
                }
                case "java.lang.Double[]" -> {
                    for(double x:(Double[])value)
                        buffer.putDouble(x);
                }

                /* Char !!! For C compatibility size of char is taken 1 byte*/
                case "char", "java.lang.Character" -> buffer.put((byte) value);
                case "char[]" -> {
                    for(char x:(char[])value)
                        buffer.put((byte) x);
                }
                case "java.lang.Character[]" -> {
                    for(char x:(Character[])value)
                        buffer.put((byte) x);
                }

                /* String */
                case "String"-> buffer.put(((String) value).getBytes());

                /* Empty bytes */
                case "empty"->{
                    byte[] b = new byte[eachSize];
                    Arrays.fill(b, (byte) '\0');
                    buffer.put(b);
                }
            }
        }

        return buffer.array();
    }

    static Object setStructBytes(Object object,byte[] bytes) throws IllegalAccessException {
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
        List<DataType> types = getStructure(object);

        int ptr = 0;
        for (java.lang.reflect.Field field : object.getClass().getDeclaredFields()) {
            DataType dataType = types.get(ptr++);
            if(dataType.getType().equals("empty")){
                for (int i = 0; i < dataType.getEachSize(); i++)
                    buffer.get();
                dataType = types.get(ptr++);
            }

            String type = dataType.getType();
            int count = dataType.getCount();
            field.setAccessible(true);
            switch (type) {
                /* Bytes */
                case "byte", "java.lang.Byte" -> {
                    field.set(object, buffer.get());
                }
                case "byte[]" -> {
                    byte[] v = new byte[count];
                    for (int i = 0; i < count; i++)
                        v[i] = buffer.get();
                    field.set(object,v);
                }
                case "java.lang.Byte[]" -> {
                    Byte[] v = new Byte[count];
                    for (int i = 0; i < count; i++)
                        v[i] = buffer.get();
                    field.set(object,v);
                }

                /* Short */
                case "short", "java.lang.Short" -> {
                    field.set(object, buffer.getShort());
                }
                case "short[]" -> {
                    short[] v = new short[count];
                    for (int i = 0; i < count; i++)
                        v[i] = buffer.getShort();
                    field.set(object,v);
                }
                case "java.lang.Short[]" -> {
                    Short[] v = new Short[count];
                    for (int i = 0; i < count; i++)
                        v[i] = buffer.getShort();
                    field.set(object,v);
                }

                /* Int */
                case "int", "java.lang.Integer" -> {
                    field.set(object, buffer.getInt());
                }
                case "int[]" -> {
                    int[] v = new int[count];
                    for (int i = 0; i < count; i++)
                        v[i] = buffer.getInt();
                    field.set(object,v);
                }
                case "java.lang.Integer[]" -> {
                    Integer[] v = new Integer[count];
                    for (int i = 0; i < count; i++)
                        v[i] = buffer.getInt();
                    field.set(object,v);
                }

                /* Long */
                case "long", "java.lang.Long" -> {
                    field.set(object, buffer.getLong());
                }
                case "long[]" -> {
                    long[] v = new long[count];
                    for (int i = 0; i < count; i++)
                        v[i] = buffer.getLong();
                    field.set(object,v);
                }
                case "java.lang.Long[]" -> {
                    Long[] v = new Long[count];
                    for (int i = 0; i < count; i++)
                        v[i] = buffer.getLong();
                    field.set(object,v);
                }

                /* Float */
                case "float", "java.lang.Float" -> {
                    field.set(object, buffer.getFloat());
                }
                case "float[]" -> {
                    float[] v = new float[count];
                    for (int i = 0; i < count; i++)
                        v[i] = buffer.getFloat();
                    field.set(object,v);
                }
                case "java.lang.Float[]" -> {
                    Float[] v = new Float[count];
                    for (int i = 0; i < count; i++)
                        v[i] = buffer.getFloat();
                    field.set(object,v);
                }

                /* Double */
                case "double", "java.lang.Double" -> {
                    field.set(object, buffer.getDouble());
                }
                case "double[]" -> {
                    double[] v = new double[count];
                    for (int i = 0; i < count; i++)
                        v[i] = buffer.getDouble();
                    field.set(object,v);
                }
                case "java.lang.Double[]" -> {
                    Double[] v = new Double[count];
                    for (int i = 0; i < count; i++)
                        v[i] = buffer.getDouble();
                    field.set(object,v);
                }

                /* Char !!! For C compatibility size of char is taken 1 byte*/
                case "char", "java.lang.Character" -> {
                    field.set(object, (char)buffer.get());
                }
                case "char[]" -> {
                    char[] v = new char[count];
                    for (int i = 0; i < count; i++)
                        v[i] = (char) buffer.get();
                    field.set(object,v);
                }
                case "java.lang.Character[]" -> {
                    Character[] v = new Character[count];
                    for (int i = 0; i < count; i++)
                        v[i] = (char) buffer.get();
                    field.set(object,v);
                }

                /* String */
                case "String"->{
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < count; i++)
                        builder.append(buffer.get());
                    field.set(object,builder.toString());
                }

            }
        }

        if(ptr<types.size()){
            DataType dataType = types.get(ptr);
            String type = dataType.getType();
            int eachSize = dataType.getEachSize();
            if(type.equals("empty")){
                for (int i = 0; i < eachSize; i++)
                    buffer.get();
            }
        }

        return object;
    }

}
