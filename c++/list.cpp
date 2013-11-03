// Vi behöver klassdeklarationerna...
#include "list.h"
// ...och standardfunktioner för I/O med mera
#include <iostream>
#include <algorithm>

// Vi behöver använda saker från namnrymden std
using namespace std;

List::List() :
    first_node(new End_Node()), length(0)
{}

void List::insert_sorted(const int value)
{
    first_node = first_node->insert_sorted(value);
    length++;
}

List_Node::List_Node() {}


End_Node::End_Node() {}

Int_Node* End_Node::insert_sorted(const int value)
{
    // Vi har nått slutet på listan.  Elementet vi skulle
    // lägga till var större än alla existerande element i
    // listan.  Den nya noden ska vara omedelbart före denna
    // End_Node.

    // Om allokering misslyckas backar vi bara ur rekursionen,
    // som i Int_Node ovan.
    return new Int_Node(value, this);
}

Int_Node::Int_Node(const int value, List_Node* next) :
    node_value(value), next_node(next)
{}

Int_Node* Int_Node::insert_sorted(const int value)
{
    if (value < node_value) {
        return new Int_Node(value, this);
    } else {
        next_node = next_node->insert_sorted(value);
        return this;
    }
}

List_Node::~List_Node() {}

Int_Node::~Int_Node() { delete next_node; }

List::~List()
{
    delete first_node;
}

Int_Node* Int_Node::clone() const
{
    List_Node* p = next_node->clone();

    try {
        return new Int_Node(node_value, p);
    } catch (...) {
        delete p;
        throw;
    }
}

End_Node* End_Node::clone() const
{
    return new End_Node();
}

List::List(const List& other) :
    first_node(other.first_node->clone()), length(other.length)
{}

void List::swap(List& other)
{
    List_Node* temp = other.first_node;
    other.first_node = first_node;
    first_node = temp;
    //std::swap(first_node, other.first_node);
    int tempInt = other.length;
    other.length = length;
    length = tempInt;
    //std::swap(length, other.length);
}

List& List::operator=(List& rhs)
{
    List temp(rhs);
    swap(temp);
    return *this;
}

int List::get(const int pos) const
{
    if (pos < 0 || pos >= this->length){
        return -1;
    }
    return first_node->get(pos);
}

int Int_Node::get(const int pos) const
{
    if (pos == 0){
        return node_value;
    }
    else {
        return next_node->get(pos-1);
    }
}

int List::len() const
{
    return length;
}

int End_Node::get(const int) const
{
    return -1;
}
