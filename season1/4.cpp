#include <iostream>
using namespace std;
void toBinary(int a){
    if (a<=1)
        cout<<a;
    else 
        {
        toBinary(a/2);
        cout<<a%2;
        }
}
int main(){
    int a;
    cout<<"please enter a decimal namber:";
    cin>>a;
    toBinary(a);
}