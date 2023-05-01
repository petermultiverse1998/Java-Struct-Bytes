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