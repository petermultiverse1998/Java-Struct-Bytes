import struct_bytes.StructBytes;

import java.util.Arrays;

public class Main {
    public static void printBytes(byte[] bytes){
        System.out.println("Size: " + bytes.length);
        System.out.print("Bytes: ");
        for(byte b:bytes){
            System.out.printf("0x%02x, ",b);
        }
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) throws IllegalAccessException {
        System.out.println("*************************** BYTES FOR STRUCT OF C ******************************");
        Fruit fruitForPC = new Fruit((byte) 69, 3.14f, "APPLE", (byte) 33, (short) 6366, (short) 3435);
        System.out.println(fruitForPC);
        fruitForPC.getStructure().forEach(System.out::println);
        System.out.println();
        printBytes(fruitForPC.getStructBytes());

        System.out.println("*************************** BYTES FROM STRUCT OF C ******************************");
        byte[] bytes = {0x3f, 0x1b, 0x4e, (byte) 0xde, 0x66, 0x66, 0x09, 0x42, 0x42,
                0x41, 0x4e, 0x41, 0x4e, 0x41, 0x00, 0x00, 0x00, 0x45, (byte) 0xe9,
                0x19, 0x42, 0x00, 0x00, 0x00};
        Fruit apple = new Fruit(bytes);
        System.out.println(apple);
        apple.getStructure().forEach(System.out::println);
        System.out.println();
        printBytes(apple.getStructBytes());
    }


    static class Fruit implements StructBytes{
        private final byte qty;
        private final float price;
        private final char[] name = new char[9];
        private final byte id;
        private final short expiry_date;
        private final short mass;

        public Fruit(){
            this.qty = 0;
            this.price = 0;
            Arrays.fill(this.name,'\0');
            this.id = 0;
            expiry_date = 0;
            this.mass = 0;
        }

        public Fruit(byte[] bytes) throws IllegalAccessException {
            this.qty = 0;
            this.price = 0;
            Arrays.fill(this.name,'\0');
            this.id = 0;
            expiry_date = 0;
            this.mass = 0;
            setStructBytes(bytes);

        }

        public Fruit(byte qty, float price,String name, byte id, short expiryDate, short mass) {
            this.qty = qty;
            this.price = price;
            Arrays.fill(this.name,'\0');
            name.getChars(0,name.length(),this.name,0);
            this.id = id;
            expiry_date = expiryDate;
            this.mass = mass;
        }

        @Override
        public String toString() {
            StringBuilder name = new StringBuilder();
            for (char c : this.name) {
                if (c == '\0')
                    break;
                name.append(c);
            }
            return  "Qty        : "+qty+"\n"+
                    "Price      : "+price+"\n"+
                    "Name       : "+name+"\n"+
                    "Id         : "+id+"\n"+
                    "Expiry date: "+expiry_date+"\n"+
                    "Mass       : "+mass+"\n";
        }
    }

}