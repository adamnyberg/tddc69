#include "list.cpp"
// ...och standardfunktioner för I/O med mera
#include <iostream>
#include <algorithm>

// Vi behöver använda saker från namnrymden std
using namespace std;
void print(const List& list) {
    for (int i = 0; i < list.len(); i++){
        cout << list.get(i) << " ";
    }
    cout << endl;
}

void printcopy(const List list) {
    print(list);
}

int main() {
    List list1;
    srand(time(NULL));
    for (int i = 0; i < 10; i++){
        list1.insert_sorted(rand() % 100);
    }
    print(list1);

    cout << endl;
    List list2;
    for (int i = 0; i < 15; i++){
        list2.insert_sorted(rand() % 100);
    }
    print(list2);
    cout << endl << list1.len() << " " << list2.len() << endl;

    cout << endl;
    printcopy(list1);
    cout << endl;
    printcopy(list2);
    cout << endl << list1.len() << " " << list2.len() << endl;
    list2 = list1;
    cout << endl;
    print(list1);
    cout << endl;
    print(list2);
    cout << endl << list1.len() << " " << list2.len() << endl;
    return 0;
}

