#include <stdio.h>

/*
    Kolejka FIFO
*/

/*
    Struktura odnoszaca sie do zmiennych w kolejce.
    Zawiera wartosc bedaca liczba calkowita, oraz
    wskaznik na nastepna strukture.
*/
struct queue_struct{
    int value;
    struct queue_struct * next;
};

/*
    Wskazniki top oraz end wskazujace na pierwszy,
    oraz ostatni element w kolejce.
*/
struct queue_struct * top;
struct queue_struct * end;

/*
    Funkcja dodajaca wartosc 'a' na koniec kolejki.
*/
void push(int a){
    struct queue_struct * e;
    e = malloc(sizeof(struct queue_struct));
    e->value = a;
    e->next = NULL;
    if(end != NULL) //lista nie jest pusta
        end->next = e;
    else //lista jest pusta
        top = e;
    end = e;
}

/*
    Funkcja zdejmujaca wartosc bedaca na top z kolejki.
*/
int pop(){
    if(top == NULL){
        printf("No data.\n");
        return -1;
    }
    struct queue_struct * popped_struct = top->next;
    int a = top->value;
    free(top);
    top = popped_struct;
    if(popped_struct==NULL) end = NULL;
    return a;
}

/*
    Wypisuje menu oraz zwraca wybor uzytkownika.
*/
int write_menu(){
    int choice;
    printf("\n[0] end\n[1] push\n[2] pop\n[3] print queue\n[4] is empty?\nChoice: ");
    scanf("%d",&choice);
    return choice;
}

/*
    Funkcja obslugujaca wybor push w menu.
*/
void check_push(){
    int x;
    printf("Value: ");
    scanf("%d",&x);
    push(x);
    printf("Pushed value %d\n", x);
}

/*
    Funkcja obslugujaca wybor pop w menu.
*/
void check_pop(){
    int x = pop();
    if(x != -1) printf("Popped value %d\n", x);
}

/*
    Funkcja sprawdzajaca czy kolejka jest pusta (return 0)
    czy nie (return 1).
*/
int isEmpty(){
    if(top == NULL) return 0;
    return 1;
}

void checkIsEmpty(){
    if(isEmpty() == 1)
        printf("Queue isn't empty.\n");
    else
        printf("Queue is empty.\n");
}

/*
    Funkcja wypisujaca kolejke od top do end.
*/
void print_queue(){
    if(top == NULL){
        printf("No data.\n");
        return;
    }
    struct queue_struct * str = top;
    while(1){
        printf("%d\n", str->value);
        str = str->next;
        if(str == NULL) break;
    }
}

int main(){
    printf("__QUEUE__\n");
    int choice;
    while(1){
        choice = write_menu();
        switch(choice){
        case 0:
            return 0;
        case 1:     //wybor push
            check_push();
            break;
        case 2:     //wybor pop
            check_pop();
            break;
        case 3:     //wybor wypisania kolejki
            print_queue();
            break;
        case 4:
            checkIsEmpty();
            break;
        default:    //bledna wartosc
            printf("Bad value!\n");
            break;
        }
    }
}
