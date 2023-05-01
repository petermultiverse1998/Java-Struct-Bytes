# Java-Struct-Bytes
Conversion of class to struct is most necessary for the communication between some microcontroller and Java application. It helps in reducing the size of file in communication as structure takes less memory in C. All the processing is shift to PC or application side.

Below library supports only primitive data types.

Mapping of Java datatype to C:
→ Byte/byte         : [1B] :  uint8_t/int8_t  
→ Short/short       : [2B] :  uint16_t/int16_t  
→ Integer/int       : [4B] :  uint32_t/int32_t/int/long  
→ Long/long         : [8B] :  uint64_t/int64_t/long long  
→ Float/float       : [4B] :  float  
→ Double/double     : [8B] :  double  
→ Character/char    : [1B] :  char  

# Java Code
```rb
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
```
# C code
```rb
//
// Created by peter on 4/2/2023.
//
#include <stdio.h>
#include <stdint.h>

typedef struct {
    uint8_t qty;
    float price;
    char name[9];
    uint8_t id;
    uint16_t expiry_date;
    uint16_t mass;
}Fruit;

void printFruit(Fruit fruit){
    printf("Qty        : %d\n",fruit.qty);
    printf("Price      : %f\n",fruit.price);
    printf("Name       : %s\n",fruit.name);
    printf("Id         : %d\n",fruit.id);
    printf("Expiry date: %d\n",fruit.expiry_date);
    printf("Mass       : %d\n",fruit.mass);
}


void printBytes(Fruit fruit){
    int size = sizeof(Fruit);
    printf("Size: %d\n", size);
    uint8_t bytes[size];
    for (int i = 0; i < size; i++)
        bytes[i] = *((uint8_t *) &fruit + i);

    printf("Bytes: ");
    for (int i = 0; i < size; i++)
        printf("0x%02x, ", bytes[i]);
    printf("\n");
}

Fruit getFruit(const uint8_t* bytes){
    int size = sizeof(Fruit);
    Fruit fruit = {0};
    for (int i = 0; i < size; i++)
        *((uint8_t *) &fruit + i) = bytes[i];
    return fruit;
}

int main() {
    /* BYTES FROM JAVA */
    printf("******************* Bytes of class from Java ***************\n");
    uint8_t bytes[]={0x45, 0x00, 0x00, 0x00, 0xc3, 0xf5, 0x48, 0x40, 0x41, 0x50, 0x50, 0x4c, 0x45, 0x00, 0x00, 0x00, 0x00, 0x21, 0xde, 0x18, 0x6b, 0x0d, 0x00, 0x00};
    Fruit fruit = getFruit(bytes);
    printFruit(fruit);
    printf("\n");
    printBytes(fruit);

    /* BYTES FOR JAVA */
    printf("******************* Bytes for class of Java ***************\n");
    Fruit apple = {63,34.35f,"BANANA",69,6633,66};
    printFruit(apple);
    printf("\n");
    printBytes(apple);






    return 0;
}
```
# JAVA output
```
Java Output:
"C:\Program Files\Java\jdk-14.0.2\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2022.2.3\lib\idea_rt.jar=54272:C:\Program Files\JetBrains\IntelliJ IDEA 2022.2.3\bin" -Dfile.encoding=UTF-8 -classpath C:\Users\peter\OneDrive\Desktop\Github\Java-Struct-Bytes\java\Struct-Bytes\out\production\Struct-Bytes Main
*************************** BYTES FOR STRUCT OF C ******************************
Qty        : 69
Price      : 3.14
Name       : APPLE
Id         : 33
Expiry date: 6366
Mass       : 3435

  1 x [1B] byte 
  1 x [3B] empty 
  1 x [4B] float 
  9 x [1B] char[] 
  1 x [1B] byte 
  1 x [2B] short 
  1 x [2B] short 
  1 x [2B] empty 

Size: 24
Bytes: 0x45, 0x00, 0x00, 0x00, 0xc3, 0xf5, 0x48, 0x40, 0x41, 
0x50, 0x50, 0x4c, 0x45, 0x00, 0x00, 0x00, 0x00, 0x21, 0xde, 
0x18, 0x6b, 0x0d, 0x00, 0x00, 

*************************** BYTES FROM STRUCT OF C ******************************
Qty        : 63
Price      : 34.35
Name       : BANANA
Id         : 69
Expiry date: 6633
Mass       : 66

  1 x [1B] byte 
  1 x [3B] empty 
  1 x [4B] float 
  9 x [1B] char[] 
  1 x [1B] byte 
  1 x [2B] short 
  1 x [2B] short 
  1 x [2B] empty 

Size: 24
Bytes: 0x3f, 0x00, 0x00, 0x00, 0x66, 0x66, 0x09, 0x42, 0x42, 
0x41, 0x4e, 0x41, 0x4e, 0x41, 0x00, 0x00, 0x00, 0x45, 0xe9, 
0x19, 0x42, 0x00, 0x00, 0x00, 


Process finished with exit code 0

```
# C output
```
C Output:
C:\Users\peter\OneDrive\Desktop\Github\Java-Struct-Bytes\C\struct_test\cmake-build-debug\struct_test.exe
******************* Bytes of class from Java ***************
Qty        : 69
Price      : 3.140000
Name       : APPLE
Id         : 33
Expiry date: 6366
Mass       : 3435

Size: 24
Bytes: 0x45, 0x00, 0x00, 0x00, 0xc3, 0xf5, 0x48, 0x40, 0x41, 
0x50, 0x50, 0x4c, 0x45, 0x00, 0x00, 0x00, 0x00, 0x21, 0xde, 
0x18, 0x6b, 0x0d, 0x00, 0x00,
******************* Bytes for class of Java ***************
Qty        : 63
Price      : 34.349998
Name       : BANANA
Id         : 69  
Expiry date: 6633
Mass       : 66  

Size: 24
Bytes: 0x3f, 0x1b, 0x4e, 0xde, 0x66, 0x66, 0x09, 0x42, 0x42, 
0x41, 0x4e, 0x41, 0x4e, 0x41, 0x00, 0x00, 0x00, 0x45, 0xe9, 
0x19, 0x42, 0x00, 0x00, 0x00,

Process finished with exit code 0
```
