#include <iostream>
using namespace std;

class Node
{
private:
    int data;
    Node *nextNode;

public:
    Node(int data)
    {
        this->data = data;
        this->nextNode = nullptr;
    }
    void deleteMax(Node *&head)
    {
        if (head == nullptr)
            return;

        Node *current = head;
        Node *maxNode = head;
        Node *pre = nullptr;
        Node *maxPre = nullptr;
        while (current != nullptr)
        {
            if (current->data > maxNode->data)
            {
                maxNode = current;
                maxPre = pre;
            }
            pre = current;
            current = current->nextNode;
        }

        if (maxNode == head)
        {
            head = head->nextNode;
        }
        else
        {
            maxPre->nextNode = maxNode->nextNode;
        }
        delete maxNode;
    }
};

int main()
{
    Node *head = new Node(10);
    return 0;
}
